package org.dimhat.security.controller;

import org.apache.log4j.Logger;
import org.dimhat.security.model.base.JsonResult;
import org.dimhat.security.service.PermService;
import org.dimhat.security.web.annotation.RequireRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("perm")
@RequireRole("admin")
public class PermController {
    private static final Logger logger=Logger.getLogger(PermController.class);
    @Autowired
    private PermService permService;

    @RequestMapping(value="delete",method = RequestMethod.POST)
    public JsonResult doDelete(@PathVariable("id")Long id){
        permService.fakeDelete(id);
        return JsonResult.OK;
    }
    @RequestMapping(value="shiftup",method=RequestMethod.POST)
    public JsonResult doShiftUp(@PathVariable("id")Long id){
        try {
            permService.shiftup(id);
            return JsonResult.OK;
        }catch (Exception e){
            logger.error("perm shif up failed",e);
            return JsonResult.ERR;
        }
    }

    @RequestMapping(value="shifdown",method=RequestMethod.POST)
    public JsonResult doShiftDown(@PathVariable("id")Long id){
        permService.shiftdown(id);
        return JsonResult.OK;
    }


}
