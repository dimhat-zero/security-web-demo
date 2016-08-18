package org.dimhat.security.web.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.dimhat.security.Constant;
import org.dimhat.security.authz.AuthorizingRealm;
import org.dimhat.security.exception.NotSupportLogicalTypeException;
import org.dimhat.security.exception.UnauthorizeException;
import org.dimhat.security.exception.UserNotLoginException;
import org.dimhat.security.model.UserInfoModel;
import org.dimhat.security.web.annotation.Logical;
import org.dimhat.security.web.annotation.RequirePerm;
import org.dimhat.security.web.annotation.RequireRole;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * 权限拦截器
 * 必须和注解同时使用，和业务逻辑耦合，缺点是不能动态配置
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = Logger.getLogger(AuthInterceptor.class);

    /**
     * 解决角色权限校验问题
     * 拦截请求，返回true继续处理，返回false返回响应
     * 如果为true，原请求jsp不存在则出现空白页面
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            //需要的角色
            RequireRole requireRole = method.getAnnotation(RequireRole.class);
            //需要的权限
            RequirePerm requirePerm = method.getAnnotation(RequirePerm.class);

            StringBuffer requestURL = request.getRequestURL();//全路径
            final String requestURI = request.getRequestURI();//请求路径，不包括域名
            logger.debug("拦截路径["+requestURI+"]进行权限校验");

            if(requireRole==null && requirePerm==null){
                logger.debug("路径["+requestURI+"]无需权限");
                return super.preHandle(request, response, handler);
            }

            logger.debug("路径["+requestURI+"]进行登录状态检查");
            HttpSession session = request.getSession(true);
            //null对象是一个引用，可以强转，可调用静态方法
            UserInfoModel userInfo = (UserInfoModel) session.getAttribute(Constant.userInfo);
            if(userInfo==null){
                logger.info("用户没有登录，请求["+requestURI+"]失败，重定向到登录页面");
                String redirectUrl = request.getContextPath()+"/login?redirect_url="+requestURL.toString();
                throw new UserNotLoginException(redirectUrl);
                //response.sendRedirect();
                //return false;
            }

            //验证角色
            if(requireRole!=null){
                validateRole(requestURI,userInfo,requireRole);
                logger.debug("路径["+requestURI+"]验证role通过");
            }
            //验证权限（通配）
            if(requirePerm!=null){
                //validatePerm(requestURI,userInfo,requirePerm);
                validateWildcardPerm(requestURI,userInfo,requirePerm);
                logger.debug("路径["+requestURI+"]验证perm通过");
            }
            logger.debug("路径["+requestURI+"]验证授权通过");
        }
        return super.preHandle(request, response, handler);
    }

    //验证角色
    private void validateRole(String requestURI,UserInfoModel userInfo,RequireRole requireRole) {
        String[] needRoles = requireRole.value();
        Logical logical = requireRole.logical();
        logger.debug("需要角色"+JSON.toJSONString(needRoles)+"，逻辑运算："+JSON.toJSONString(logical));
        Set<String> roles = userInfo.getRoles();
        logger.debug("用户已有角色"+JSON.toJSONString(roles));
        if(logical == Logical.AND){
            for(String needRole : needRoles){
                if(!roles.contains(needRole)){//不相等就返回false，全相等返回true
                    throw new UnauthorizeException("未授权的访问，请求路径["+requestURI+"]需要角色["+needRole+"]");
                }
            }
            return;
        }else if(logical == Logical.OR){
            for(String needRole : needRoles){
                if(roles.contains(needRole)){//相等就返回true，全不等返回false
                    return;
                }
            }
            throw new UnauthorizeException("未授权的访问，请求路径["+requestURI+"]需要任一角色"+JSON.toJSONString(needRoles)+"");
        }
        throw new NotSupportLogicalTypeException("不支持的逻辑类型");
    }


    //验证权限
    private void validatePerm(String requestURI,UserInfoModel userInfo,RequirePerm requirePerm) {
        String[] needPerms = requirePerm.value();
        Logical logical = requirePerm.logical();
        logger.debug("需要权限"+JSON.toJSONString(needPerms)+"，逻辑运算："+JSON.toJSONString(logical));
        Set<String> perms = userInfo.getPerms();
        logger.debug("用户已有权限"+JSON.toJSONString(perms));
        if(logical==Logical.AND){
            for(String needPerm : needPerms){
                if(!perms.contains(needPerm)){
                    throw new UnauthorizeException("未授权的访问，请求路径["+requestURI+"]需要权限["+needPerm+"]");
                }
            }
            return;
        }else if(logical==Logical.OR){
            for(String needPerm : needPerms){
                if(perms.contains(needPerm)) return;
            }
            throw new UnauthorizeException("未授权的访问，请求路径["+requestURI+"]需要任一权限"+JSON.toJSONString(needPerms)+"");
        }
        throw new NotSupportLogicalTypeException("不支持的逻辑类型");
    }

    //授权验证实体
    private AuthorizingRealm authorizingRealm = new AuthorizingRealm();
    //验证通配权限
    private void validateWildcardPerm(String requestURI,UserInfoModel userInfo,RequirePerm requirePerm) {
        String[] needPerms = requirePerm.value();
        Logical logical = requirePerm.logical();
        logger.debug("需要权限"+JSON.toJSONString(needPerms)+"，逻辑运算："+JSON.toJSONString(logical));
        Set<String> perms = userInfo.getPerms();
        logger.debug("用户已有权限"+JSON.toJSONString(perms));
        if(logical==Logical.AND){
            for(String needPerm : needPerms){
                if(!authorizingRealm.isPermitted(needPerm,userInfo)){
                    throw new UnauthorizeException("未授权的访问，请求路径["+requestURI+"]需要权限["+needPerm+"]");
                }
            }
            return;
        }else if(logical==Logical.OR){
            for(String needPerm : needPerms){
                if(perms.contains(needPerm)) return;
            }
            throw new UnauthorizeException("未授权的访问，请求路径["+requestURI+"]需要任一权限"+JSON.toJSONString(needPerms)+"");
        }
        throw new NotSupportLogicalTypeException("不支持的逻辑类型");
    }

}
