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
import org.springframework.stereotype.Repository;

/**
 * 用户实现
 * @author dimhat
 * @date 2016年8月13日 下午2:52:49
 * @version 1.0
 */
@Repository
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
				return ps;
			}
		}, keyHolder);
		user.setId(keyHolder.getKey().longValue());
		return user;
	}

	/** 
	 * @see org.dimhat.security.dao.UserDao#update(org.dimhat.security.entity.User)
	 */
	@Override
	public int update(User user) {
		String sql = "update sys_user set username=?,password=?,nick_name=?,salt=?,locked=?,last_login=? where id =?";
		return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getNickName(), user.getSalt(),
				user.getLocked(), user.getLastLogin(), user.getId());
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
	 * @see org.dimhat.security.dao.UserDao#findById(java.lang.Long)
	 */
	@Override
	public User findById(Long id) {
		String sql = "select * from sys_user where id = ?";
		User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
		return user;
	}

	/** 
	 * @see org.dimhat.security.dao.UserDao#findByUsername(java.lang.String)
	 */
	@Override
	public User findByUsername(String username) {
		String sql = "select * from sys_user where username=?";
		List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), username);
		if (users.size() == 0)
			return null;
		return users.get(0);
	}

	@Override
	public List<User> findAll() {
		String sql="select * from sys_user";
		List<User> list = jdbcTemplate.query(sql, new UserRowMapper());
		return list;
	}
}
