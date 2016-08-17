package org.dimhat.security.dao;

import org.dimhat.security.entity.Permission;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by think on 2016/8/17.
 */
public class PermRowMapper  implements RowMapper<Permission> {
    @Override
    public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
        Permission perm = new Permission();
        perm.setId(rs.getLong("id"));
        perm.setDeleted(rs.getBoolean("is_deleted"));
        perm.setDescription(rs.getString("description"));
        perm.setMenu(rs.getBoolean("is_menu"));
        perm.setParentId(rs.getLong("parent_id"));
        perm.setPermission(rs.getString("permission"));
        perm.setRank(rs.getInt("rank"));
        perm.setSubRankSeq(rs.getInt("sub_rank_seq"));
        return perm;
    }
}
