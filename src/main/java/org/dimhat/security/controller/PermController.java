package org.dimhat.security.controller;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.dimhat.security.entity.Perm;
import org.dimhat.security.model.PermUpdateForm;
import org.dimhat.security.model.base.JsonResult;
import org.dimhat.security.model.base.TreeNode;
import org.dimhat.security.service.PermService;
import org.dimhat.security.web.annotation.RequireRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("perm")
@RequireRole("admin")
public class PermController {
    private static final Logger logger=Logger.getLogger(PermController.class);
    @Autowired
    private PermService permService;

    //list
    @RequestMapping(value="",method = RequestMethod.GET)
    public String perm(Model model){
        List<Perm> perms = permService.findAll();
        model.addAttribute("perms",perms);
        return "rbac/perm";
    }

    @RequestMapping(value="dir-path",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult dir_path(Long dir_id){//curr path
        JsonResult result = new JsonResult();
        List<Perm> permList = null;
        if(dir_id==0L){
            permList = new ArrayList<>();
            permList.add(Perm.getRoot());
        }else{
            permList = permService.findPathById(dir_id);
        }
        result.setData(permList);
        return result;
    }

    @RequestMapping(value = "dir-tree",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult dir_tree(){
        JsonResult result = new JsonResult();
        List<Perm> menus = permService.findMenus();
        List<TreeNode> nodes = new ArrayList<>();
        for(Perm menu : menus){
            TreeNode node = new TreeNode(menu.getId(),menu.getParentId(),menu.getDescription());
            if(menu.isRoot()){
                node.setOpen(true);//根节点打开
            }
            node.setIconSkin("dir");
            nodes.add(node);
        }
        result.setData(nodes);
        return result;
    }
    @RequestMapping(value="query",method=RequestMethod.GET)
    @ResponseBody
    public JsonResult query(Perm perm,@RequestParam(value="withParent",required = false,defaultValue = "false") Boolean withParent){
        JsonResult result = new JsonResult();
        List<Perm> child = permService.query(perm);
        if(withParent){
            Perm parent = permService.findPermissionById(perm.getParentId());
            List<Perm> list = new ArrayList<>();
            list.add(parent);
            list.addAll(child);
            result.setData(list);
        }else{
            result.setData(child);
        }
        return result;
    }

    //add
    @RequestMapping(value="/{id}/addChild",method=RequestMethod.GET)
    public String addChild(@PathVariable("id")Long parentId,Model model){
        logger.debug("权限增加子节点页面请求："+parentId);
        Perm parent = permService.findPermissionById(parentId);
        logger.debug("找到父权限："+JSON.toJSONString(parent));

        PermUpdateForm perm=new PermUpdateForm();
        perm.setParentId(parentId);
        perm.setParentDescription(parent.getDescription());
        model.addAttribute("perm",perm);
        return "rbac/perm_edit";
    }

    @RequestMapping(value="/{id}/addChild",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addChild(@PathVariable("id")Long parentId,PermUpdateForm form){
        logger.debug("增加子节点开始："+ JSON.toJSONString(form));
        permService.add(form);
        return JsonResult.OK;
    }

    //update
    @RequestMapping(value = "/{id}/update",method = RequestMethod.GET)
    public String update(@PathVariable("id")Long id,Model model){
        logger.debug("权限更新页面请求："+id);
        Perm perm = permService.findPermissionById(id);
        logger.debug("找到权限："+JSON.toJSONString(perm));
        Perm parent = permService.findPermissionById(perm.getParentId());

        PermUpdateForm form = new PermUpdateForm(perm);
        form.setParentDescription(parent.getDescription());
        model.addAttribute("perm",form);
        return "rbac/perm_edit";
    }

    @RequestMapping(value = "/{id}/update",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult doUpdate(@PathVariable("id")Long id,PermUpdateForm  form){
        permService.update(form);
        return JsonResult.OK;
    }

    //delete
    @RequestMapping(value="/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult doDelete(@PathVariable("id")Long id){
        permService.delete(id);
        return JsonResult.OK;
    }


}
