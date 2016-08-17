package org.dimhat.security.service;

/**
 * Created by think on 2016/8/17.
 */
public class PermServiceImpl {
    private static PermServiceImpl ourInstance = new PermServiceImpl();

    public static PermServiceImpl getInstance() {
        return ourInstance;
    }

    private PermServiceImpl() {
    }
}
