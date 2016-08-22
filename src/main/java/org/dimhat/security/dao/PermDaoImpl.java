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
        final String sql="insert into sys_perm(permission,description,is_deleted,is_menu,parent_id,rank,sub_rank_seq) values(?,?,?,?,?,?,?)";
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
                ps.setInt(index++,perm.getRank());
                ps.setInt(index++,perm.getSubRankSeq());
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
        String sql="update sys_perm set permission=?,description=?,is_deleted=?,is_menu=?,parent_id=?,rank=?,sub_rank_seq=? where id=?";
        return jdbcTemplate.update(sql,perm.getPermission(),perm.getDescription(),perm.getDeleted(),perm.getMenu(),perm.getParentId(),perm.getRank(),perm.getSubRankSeq(),perm.getId());
    }

    @Override
    public Perm findById(Long id) {
        String sql="select * from sys_perm where id = ?";
        Perm perm = jdbcTemplate.queryForObject(sql, new PermRowMapper(), id);//if not find will throw exception
        return perm;
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
