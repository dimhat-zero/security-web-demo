package org.dimhat.security.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.dimhat.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

/**
 * 用户实现
 * @author dimhat
 * @date 2016年8月13日 下午2:52:49
 * @version 1.0
 */
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /** 
     * @see org.dimhat.security.dao.UserDao#save(org.dimhat.security.entity.User)
     */
    @Override
    public User save(final User user) {
        final String sql = "insert into sys_user(username,password,salt,locked,last_login) values(?,?,?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, new String[] { "id" });
                int index = 1;
                ps.setString(index++, user.getUsername());
                ps.setString(index++, user.getPassword());
                ps.setString(index++, user.getSalt());
                ps.setBoolean(index++, user.getLocked());
                ps.setTimestamp(index++, user.getLastLogin());
                return null;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return null;
    }

    /** 
     * @see org.dimhat.security.dao.UserDao#update(org.dimhat.security.entity.User)
     */
    @Override
    public User update(User user) {
        String sql = "update sys_user set username=?,password=?,salt=?,locked=?,last_login=? where id =?";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getSalt(), user.getLocked(),
            user.getLastLogin(), user.getId());
        return null;
    }

    /** 
     * @see org.dimhat.security.dao.UserDao#delete(java.lang.Long)
     */
    @Override
    public void delete(Long id) {
        String sql = "delete from sys_user where id = ?";
        jdbcTemplate.update(sql, id);
    }

    /** 
     * @see org.dimhat.security.dao.UserDao#findUserById(java.lang.Long)
     */
    @Override
    public User findUserById(Long id) {
        String sql = "select * from sys_user where id = ?";
        User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
        return user;
    }

    /** 
     * @see org.dimhat.security.dao.UserDao#findUserByUsername(java.lang.String)
     */
    @Override
    public User findUserByUsername(String username) {
        String sql = "select * from sys_user where username=?";
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), username);
        if (users.size() == 0)
            return null;
        return users.get(0);
    }

}
