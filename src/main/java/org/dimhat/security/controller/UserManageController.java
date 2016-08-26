package org.dimhat.security.controller;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.dimhat.security.entity.Perm;
import org.dimhat.security.entity.Role;
import org.dimhat.security.entity.User;
import org.dimhat.security.model.RoleModel;
import org.dimhat.security.model.UserInfoModel;
import org.dimhat.security.model.UserModel;
import org.dimhat.security.model.UserUpdateForm;
import org.dimhat.security.model.base.JsonResult;
import org.dimhat.security.model.base.TreeNode;
import org.dimhat.security.service.AuthorizeService;
import org.dimhat.security.service.RoleService;
import org.dimhat.security.service.UserService;
import org.dimhat.security.web.annotation.RequireRole;
import org.dimhat.security.web.annotation.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("user")
@RequireRole("admin")
public class UserManageController {

    private static final Logger logger = Logger.getLogger(UserManageController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthorizeService authorizeService;

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home(@UserInfo() UserInfoModel userInfo, Model model) {
        logger.debug(JSON.toJSONString(userInfo));
        return "user/home";
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    @RequireRole("admin")
    public String user(Model model){
        List<User> users = userService.findAll();
        List<UserModel> list = new ArrayList<>();
        for(User user  : users){
            UserModel userModel = new UserModel();
            BeanUtils.copyProperties(user,userModel);
            userModel.setRoles(userService.findRolesByUserId(userModel.getId()));
            list.add(userModel);
        }
        model.addAttribute("users",list);
        return "rbac/user";
    }

    /**
     * 管理员赋权
     */
    @RequestMapping(value="/{id}/update",method = RequestMethod.GET)
    public String update(@PathVariable("id")Long id,Model model){
        User user = userService.getUserById(id);
        List<Role> roles = userService.findRolesByUserId(id);
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user,userModel);
        userModel.setRoles(roles);

        model.addAttribute("user",userModel);
        return "rbac/user_edit";
    }

    @RequestMapping(value="/{id}/update",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult doUpdate(@PathVariable("id")Long id, UserUpdateForm form){
        logger.debug("修改用户信息：" + JSON.toJSONString(form));
        userService.update(form);
        if(form.getRoleIds()!=null){
            authorizeService.updateUserRoles(form.getId(),form.getRoleIds());
        }
        return JsonResult.OK;
    }

    //find role select perm node
    @RequestMapping(value="/roleNode",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getUserRoleNode(Long id){
        JsonResult result = new JsonResult();
        //find exist role ids
        Set<Long> existPermIds = findExistRoleIds(id);

        //find all role
        List<Role>  allRoles  = roleService.findAll();
        //build zNodes mark all role if exist
        List<TreeNode> zNodes =  new ArrayList<>();
        for(Role role : allRoles){
            TreeNode zNode=new TreeNode(role.getId(),0L,role.getDescription());
            zNode.setChecked(existPermIds.contains(role.getId()));
            zNodes.add(zNode);
        }
        result.setData(zNodes);
        return result;
    }

    private Set<Long> findExistRoleIds(Long userId){
        Set<Long> existRoleIds = null;
        if(userId==null){
            existRoleIds =  new HashSet<>();
        }else{
            List<Role> roles = userService.findRolesByUserId(userId);
            //build exist perm id set
            existRoleIds = new HashSet<>(roles.size());
            for(Role role : roles){
                existRoleIds.add(role.getId());
            }
        }
        return existRoleIds;
    }


}
