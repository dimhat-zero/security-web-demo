package org.dimhat.security.dao;

import org.dimhat.security.entity.Role;
import org.dimhat.security.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by think on 2016/8/17.
 */
@Repository
public class UserRoleDaoImpl implements UserRoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addUserRole(Long userId, Long roleId) {
        String sql="insert into sys_user_role(user_id,role_id) values(?,?)";
        return jdbcTemplate.update(sql,userId,roleId);

    }

    @Override
    public int deleteUserRole(Long id) {
        String sql="delete from sys_user_role where id = ?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        String sql="select * from sys_role r,sys_user_role ur where r.id=ur.role_id and ur.user_id = ?";
        List<Role> list = jdbcTemplate.query(sql, new RoleRowMapper(), userId);
        return list;
    }

    @Override
    public int deleteUserRolesByUserId(Long userId) {
        String sql="delete from sys_user_role where user_id = ?";
        return jdbcTemplate.update(sql,userId);
    }

    @Override
    public UserRole findById(Long id) {
        String sql="select * from sys_user_role where id=?";
        UserRole userRole = jdbcTemplate.queryForObject(sql, new RowMapper<UserRole>() {
            @Override
            public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserRole userRole = new UserRole();
                userRole.setId(rs.getLong("id"));
                userRole.setRoleId(rs.getLong("role_id"));
                userRole.setUserId(rs.getLong("user_id"));
                return userRole;
            }
        }, id);
        return userRole;
    }
}
