package org.dimhat.security.web.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.dimhat.security.Constant;
import org.dimhat.security.exception.NotSupportLogicalTypeException;
import org.dimhat.security.model.UserInfoModel;
import org.dimhat.security.web.annotation.Logical;
import org.dimhat.security.web.annotation.RequirePerm;
import org.dimhat.security.web.annotation.RequireRole;
import org.dimhat.security.web.annotation.UserInfo;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by think on 2016/8/17.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = Logger.getLogger(AuthInterceptor.class);

    /**
     * 解决角色权限校验问题
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();


            //validate role
            RequireRole requireRole = method.getAnnotation(RequireRole.class);
            //validate permission
            RequirePerm requirePerm = method.getAnnotation(RequirePerm.class);

            StringBuffer requestURL = request.getRequestURL();//全路径
            final String requestURI = request.getRequestURI();//请求路径，不包括域名
            logger.debug("拦截路径["+requestURI+"]进行权限校验");

            //需要权限验证
            if(requireRole!=null || requirePerm!=null){
                logger.debug("校验路径["+requestURI+"]检查登录状态");
                HttpSession session = request.getSession(true);
                //null对象是一个引用，可以强转，可调用静态方法
                UserInfoModel userInfo = (UserInfoModel) session.getAttribute(Constant.userInfo);
                if(userInfo==null){
                    logger.info("用户没有登录，请求["+requestURI+"]失败，重定向到登录页面");
                    response.sendRedirect(request.getContextPath()+"/login?redirect_url="+requestURL.toString());
                    return super.preHandle(request, response, handler);
                }

                //验证角色
                if(requireRole!=null){
                    boolean success=validateRole(userInfo,requireRole);
                    logger.info("验证角色返回["+success+"]");
                    if(!success){//授权未通过
                        response.sendRedirect(request.getContextPath()+"/unpassauth");
                    }
                }
                //验证权限
                if(requirePerm!=null){
                    validatePerm(userInfo,requirePerm);
                }
                logger.debug("验证通过["+requestURI+"]");
            }
            else{
                logger.debug("无需权限["+requestURI+"]");
            }
        }
        return super.preHandle(request, response, handler);
    }

    //验证角色
    private Boolean validateRole(UserInfoModel userInfo,RequireRole requireRole) {
        String[] needRoles = requireRole.value();
        Logical logical = requireRole.logical();
        logger.debug("Need roles "+JSON.toJSONString(needRoles)+" and logical is "+JSON.toJSONString(logical));
        Set<String> roles = userInfo.getRoles();
        logger.debug("User have roles "+JSON.toJSONString(roles));
        if(logical == Logical.AND){
            for(String needRole : needRoles){
                if(!roles.contains(needRole)){//不相等就返回false，全相等返回true
                    return false;
                }
            }
            return true;
        }else if(logical == Logical.OR){
            for(String needRole : needRoles){
                if(roles.contains(needRole)){//相等就返回true，全不等返回false
                    return true;
                }
            }
            return false;
        }
        throw new NotSupportLogicalTypeException("不支持的逻辑类型");
    }

    //验证权限
    private void validatePerm(UserInfoModel userInfo,RequirePerm requirePerm) {

    }
}
