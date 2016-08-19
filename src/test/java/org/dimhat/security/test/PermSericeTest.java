package org.dimhat.security.test;

import org.dimhat.security.entity.Perm;
import org.dimhat.security.model.PermUpdateForm;
import org.dimhat.security.service.PermService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by think on 2016/8/17.
 */
public class PermSericeTest extends TestBase{
    @Autowired
    private PermService permService;

    @Test
    public void test(){
        PermUpdateForm perm=new PermUpdateForm();
        permService.add(perm);
    }
}
