package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * employeesテーブルを操作するリポジトリ.
 * 
 * @author yuyayokoyama
 *
 */

@Repository
public class EmployeeRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	private static final String TABLE_NAME = "employees";
	
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		
		return employee;
	};
	
	/**
	 * 従業員一覧情報を取得する.
	 * 
	 * @return 従業員一覧情報 入社日の降順で並び替え
	 */
	public List<Employee> findAll(){
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM "
				+ TABLE_NAME + " ORDER BY hire_date DESC";
		
		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
		if(employeeList.size() == 0) {
			return null;
		}
		return employeeList;
		
	}
	
	/**
	 *主キー検索から従業員情報を取得する.
	 * @param id ID
	 * @return 従業員情報
	 */
	public Employee load(Integer id) {
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM "
				+ TABLE_NAME + " WHERE id=:id";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		return employee;
	}
	
	/**
	 * 従業員情報を変更する.
	 * @param employee 従業員情報
	 */
	public void update(Employee employee) {
		String updateSql = "UPDATE " + TABLE_NAME + " SET(name=:name,image=:image,gender=:gender,hire_date=:hire_date,mail_address=:mail_address,"
				+ " zip_code=:zip_code,address=:address,telephone=:telephone,salary=:salary,characteristics=:characteristics,dependents_count=:dependents_count"
				+ " WHERE id=:id";
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		template.update(updateSql, param);
	}
	

}
