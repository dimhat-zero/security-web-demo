package org.dimhat.security.dao;

import org.dimhat.security.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by think on 2016/8/17.
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Role save(final Role role) {
        final String sql="insert into sys_role(role_name,description,is_deleted) values(?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql,new String[]{"id"});
                int index=1;
                ps.setString(index++,role.getRoleName());
                ps.setString(index++,role.getDescription());
                ps.setBoolean(index++,role.getDeleted());
                return ps;
            }
        },keyHolder);
        role.setId(keyHolder.getKey().longValue());
        return role;
    }

    @Override
    public int update(Role role) {
        String sql="update sys_role set role_name=?,description=?,is_deleted=? where id =?";
        return jdbcTemplate.update(sql,role.getRoleName(),role.getDescription(),role.getDeleted(),role.getId());
    }

    @Override
    public int delete(Long id) {
        String sql="delete from sys_role where id=?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public Role findById(Long id) {
        String sql="select * from sys_role where id=?";
        Role role = jdbcTemplate.queryForObject(sql, new RoleRowMapper(), id);
        return role;
    }

    @Override
    public List<Role> findAll() {
        String sql="select * from sys_role";
        List<Role> list = jdbcTemplate.query(sql, new RoleRowMapper());
        return list;
    }

    @Override
    public Role findByRole(String s) {
        String sql="select * from sys_role where role_name=?";
        Role role = jdbcTemplate.queryForObject(sql, new RoleRowMapper(), s);
        return role;
    }
}
