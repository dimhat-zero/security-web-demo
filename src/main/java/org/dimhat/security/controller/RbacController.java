package org.dimhat.security.controller;

import org.dimhat.security.service.AuthorizeService;
import org.dimhat.security.service.PermService;
import org.dimhat.security.service.RoleService;
import org.dimhat.security.service.UserService;
import org.dimhat.security.web.annotation.Logical;
import org.dimhat.security.web.annotation.RequirePerm;
import org.dimhat.security.web.annotation.RequireRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by think on 2016/8/17.
 */
@RequestMapping("rbac")
@Controller
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
    @RequireRole(value={"admin","guest"},logical = Logical.AND)
    public String role(){
        return "rbac/role";
    }

    @RequestMapping(value="perm",method = RequestMethod.GET)
    @RequireRole(value={"admin","guest"},logical = Logical.OR)
    @RequirePerm(value={"role:update","role:create"},logical = Logical.AND)
    public String perm(){
        return "rbac/perm";
    }
}
