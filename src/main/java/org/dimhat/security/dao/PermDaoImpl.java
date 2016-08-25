package org.dimhat.security.dao;

import org.dimhat.security.entity.Perm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2016/8/17.
 */
@Repository
public class PermDaoImpl implements PermDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Perm save(final Perm perm) {
        final String sql="insert into sys_perm(permission,description,is_deleted,is_menu,parent_id) values(?,?,?,?,?)";
        GeneratedKeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
                int index=1;
                ps.setString(index++,perm.getPermission());
                ps.setString(index++,perm.getDescription());
                ps.setBoolean(index++,perm.getDeleted());
                ps.setBoolean(index++,perm.getMenu());
                ps.setLong(index++,perm.getParentId());
                return ps;
            }
        }, keyHolder);
        perm.setId(keyHolder.getKey().longValue());
        return perm;
    }

    @Override
    public int delete(Long id) {
        String sql="delete from sys_perm where id = ?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int update(Perm perm) {
        String sql="update sys_perm set permission=?,description=?,is_deleted=?,is_menu=?,parent_id=? where id=?";
        return jdbcTemplate.update(sql,perm.getPermission(),perm.getDescription(),perm.getDeleted(),perm.getMenu(),perm.getParentId(),perm.getId());
    }

    @Override
    public Perm findById(Long id) {
        String sql="select * from sys_perm where id = ?";
        Perm perm = jdbcTemplate.queryForObject(sql, new PermRowMapper(), id);//if not find will throw exception
        return perm;
    }

    @Override
    public Perm findByIdForUpdate(Long id) {
        String sql="select * from sys_perm where id = ? for update";
        Perm perm = jdbcTemplate.queryForObject(sql, new PermRowMapper(), id);//if not find will throw exception
        return perm;
    }

    @Override
    public List<Perm> findBySQL(String sql) {
        return jdbcTemplate.query(sql,new PermRowMapper());
    }

    @Override
    public void executeSQL(String sql) {
        jdbcTemplate.execute(sql);
    }

    @Override
    public List<Perm> query(Perm perm) {
        StringBuilder sql=new StringBuilder("select * from sys_perm where 1=1");
        List<Object> values = new ArrayList<>();
        if(perm.getMenu()!=null){
            sql.append(" and is_menu = ?");
            values.add(perm.getMenu());
        }
        if(perm.getPermission()!=null){
            sql.append(" and permission = ?");
            values.add(perm.getPermission());
        }
        if(perm.getDeleted()!=null){
            sql.append(" and is_deleted = ?");
            values.add(perm.getDeleted());
        }
        if(perm.getParentId()!=null){
            sql.append(" and parent_id = ?");
            values.add(perm.getParentId());
        }

        return jdbcTemplate.query(sql.toString(),values.toArray(),new PermRowMapper());
    }

    @Override
    public List<Perm> findAll() {
        String sql="select * from sys_perm";
        List<Perm> list = jdbcTemplate.query(sql, new PermRowMapper());
        return list;
    }

    @Override
    public Perm findByPerm(String s) {
        String sql="select * from sys_perm where permission = ?";
        Perm perm =  jdbcTemplate.queryForObject(sql,new PermRowMapper(),s);
        return perm;
    }
}
