package org.dimhat.security.controller;

import org.apache.log4j.Logger;
import org.dimhat.security.entity.Perm;
import org.dimhat.security.entity.Role;
import org.dimhat.security.model.RoleModel;
import org.dimhat.security.model.RoleUpdateForm;
import org.dimhat.security.model.base.JsonResult;
import org.dimhat.security.model.base.TreeNode;
import org.dimhat.security.service.PermService;
import org.dimhat.security.service.RoleService;
import org.dimhat.security.web.annotation.RequireRole;
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

/**
 * Created by think on 2016/8/24.
 */
@RequestMapping("role")
@RequireRole("admin")
@Controller
public class RoleController {

    private static final Logger logger= Logger.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermService permService;

    //list
    @RequestMapping(value="",method = RequestMethod.GET)
    public String role(Model model){
        List<Role> roles = roleService.findAll();
        //trans to role model
        List<RoleModel> roleModels  = new ArrayList<>();
        for(Role role : roles){
            RoleModel roleModel = roleService.findDetailById(role.getId());
            roleModels.add(roleModel);
        }
        model.addAttribute("roles",roleModels);
        return "rbac/role";
    }

    //add
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("role",new RoleModel());
        return "rbac/role_edit";
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult doAdd(RoleUpdateForm form){
        roleService.add(form);
        return JsonResult.OK;
    }

    //update
    @RequestMapping(value="/{id}/update",method = RequestMethod.GET)
    public String update(@PathVariable("id")Long id, Model model){
        RoleModel role = roleService.findDetailById(id);
        model.addAttribute("role",role);
        return "rbac/role_edit";
    }

    @RequestMapping(value="/{id}/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult doUpdate(RoleUpdateForm form){
        roleService.update(form);
        return JsonResult.OK;
    }

    //delete
    @RequestMapping(value="/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult doDelete(@PathVariable("id")Long id){
        roleService.delete(id);
        return  JsonResult.OK;
    }

    //lock
    @RequestMapping(value="/{id}/lock",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult lock(@PathVariable("id")Long id){
        roleService.lock(id);
        return  JsonResult.OK;
    }

    @RequestMapping(value="/{id}/unlock",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult unlock(@PathVariable("id")Long id){
        roleService.unlock(id);
        return  JsonResult.OK;
    }

    //find role select perm node
    @RequestMapping(value="/permNode",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getRolePermZnode(Long id){
        JsonResult result = new JsonResult();
        //find exist perm ids
        Set<Long> existPermIds = findExistPermIds(id);

        //find all perm
        List<Perm> allPerms = permService.findAll();
        //build zNodes mark all perm if exist
        List<TreeNode> zNodes =  new ArrayList<>();
        for(Perm perm : allPerms){
            if(perm.isRoot()) continue;
            TreeNode zNode=new TreeNode(perm.getId(),perm.getParentId(),perm.getDescription());
            zNode.setChecked(existPermIds.contains(perm.getId()));
            zNodes.add(zNode);
        }
        result.setData(zNodes);
        return result;
    }

    private Set<Long> findExistPermIds(Long roleId){
        Set<Long> existPermIds = null;
        if(roleId==null){
            existPermIds =  new HashSet<>();
        }else{
            RoleModel roleModel = roleService.findDetailById(roleId);
            //build exist perm id set
            existPermIds = new HashSet<>(roleModel.getPermList().size());
            for(Perm perm : roleModel.getPermList()){
                existPermIds.add(perm.getId());
            }
        }
        return existPermIds;
    }
}
