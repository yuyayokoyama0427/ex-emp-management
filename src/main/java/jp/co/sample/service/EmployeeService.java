package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員情報を操作するサービス.
 * 
 * @author yuyayokoyama
 *
 */
@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 従業員情報を全件取得する.
	 * @return 従業員情報全件
	 */
	public List<Employee> showList(){
		return employeeRepository.findAll();	
	}
	
	/**
	 * 従業員情報を取得する.
	 * @param id ID
	 * @return 従業員情報
	 */
	public Employee showDetails(Integer id) {
		return employeeRepository.load(id);
		
	}
	/**
	 * 従業員情報を更新する.
	 * 
	 * @param employee 従業員情報
	 */
	public void Update(Employee employee) {
		employeeRepository.update(employee);
	}
	
	
	

}
