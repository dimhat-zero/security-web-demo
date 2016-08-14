package org.dimhat.security.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.dimhat.security.entity.User;
import org.springframework.jdbc.core.RowMapper;

/**
 * TODO
 * @author dimhat
 * @date 2016年8月13日 下午5:11:12
 * @version 1.0
 */
public class UserRowMapper implements RowMapper<User> {

    /** 
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setNickName(rs.getString("nick_name"));
        user.setSalt(rs.getString("salt"));
        user.setLocked(rs.getBoolean("locked"));
        user.setLastLogin(rs.getTimestamp("last_login"));
        return user;
    }

}
