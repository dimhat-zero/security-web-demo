package org.dimhat.security.dao;

import org.dimhat.security.entity.Perm;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by think on 2016/8/17.
 */
public class PermRowMapper  implements RowMapper<Perm> {
    @Override
    public Perm mapRow(ResultSet rs, int rowNum) throws SQLException {
        Perm perm = Perm.createPerm();
        perm.setId(rs.getLong("id"));
        perm.setDeleted(rs.getBoolean("is_deleted"));
        perm.setDescription(rs.getString("description"));
        perm.setMenu(rs.getBoolean("is_menu"));
        perm.setParentId(rs.getLong("parent_id"));
        perm.setPermission(rs.getString("permission"));
        return perm;
    }
}
