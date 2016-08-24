package org.dimhat.security.dao;

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
public class RolePermDaoImpl implements  RolePermDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int deleteRolePermByRoleId(Long roleId) {
        String sql="delete from sys_role_perm where role_id= ?";
        return jdbcTemplate.update(sql,roleId);
    }

    @Override
    public int addRolePerm(Long roleId, Long permId) {
        String sql="insert into sys_role_perm(role_id,perm_id) values(?,?)";
        return jdbcTemplate.update(sql,roleId,permId);
    }

    @Override
    public List<Long> findPermIdsByRoleId(Long roleId) {
        String sql="select * from sys_role_perm where role_id = ?";
        return jdbcTemplate.query(sql, new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong("perm_id");
            }
        },roleId);
    }
}
