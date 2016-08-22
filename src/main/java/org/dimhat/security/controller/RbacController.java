package org.dimhat.security.controller;

import org.dimhat.security.entity.Perm;
import org.dimhat.security.service.AuthorizeService;
import org.dimhat.security.service.PermService;
import org.dimhat.security.service.RoleService;
import org.dimhat.security.service.UserService;
import org.dimhat.security.web.annotation.Logical;
import org.dimhat.security.web.annotation.RequirePerm;
import org.dimhat.security.web.annotation.RequireRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @RequireRole 需要角色，默认or
 * @RequirePerm 需要权限，默认or
 */
@RequestMapping("rbac")
@Controller
@RequireRole("admin")
public class RbacController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermService permService;
    @Autowired
    private AuthorizeService authorizeService;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    @RequireRole(value={"admin","guest"})//default is or
    public String rbac(){
        return "rbac/index";
    }

    @RequestMapping(value="role",method = RequestMethod.GET)
    public String role(){
        return "rbac/role";
    }

    @RequestMapping(value="perm",method = RequestMethod.GET)
    public String perm(Model model){
        List<Perm> perms = permService.findAll();
        model.addAttribute("perms",perms);
        return "rbac/perm";
    }

}
