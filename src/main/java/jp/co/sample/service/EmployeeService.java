package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員情報一覧を全件検索する業務処理を行うメソッド
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
	
	
	

}
