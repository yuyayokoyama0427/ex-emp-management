package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * administratorsテーブルを操作するリポジトリ.
 * 
 * @author yuyayokoyama
 *
 */
@Repository
public class AdministratorRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	private static final String TABLE_NAME = "administrators";
	
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		
		Administrator admin = new Administrator();
		admin.setId(rs.getInt("id"));
		admin.setName(rs.getString("name"));
		admin.setMailAddress(rs.getString("mailAddress"));
		admin.setPassword(rs.getString("password"));
		
		return admin;
	};
	
	/**
	 * 管理者情報を挿入する.
	 * 
	 * @param admin 管理者情報
	 */
	public void insert(Administrator admin) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(admin);
		String insertSql = "INSERT INTO " + TABLE_NAME + "(name, mail_address, password) VALUES(:name, :mailAddress, :password)";
		
		template.update(insertSql, param);
	}
	
	/**
	 * メールアドレスとパスワードから管理者情報を取得する.
	 * 
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @return 管理者情報
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String sql = "SELECT id, name, mailAddress, password FROM " + TABLE_NAME + " WERER mailAddress=:mailAddress AND password=:password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress",mailAddress).addValue("password", password);
		
		List<Administrator> adminList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
		if (adminList.size() == 0) {
			return null;
		}
		return adminList.get(0) ;
	}
	
	

}
