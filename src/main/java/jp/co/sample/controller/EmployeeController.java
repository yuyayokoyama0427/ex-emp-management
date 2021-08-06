package jp.co.sample.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報を検索する処理のコントローラー.
 * 
 * @author yuyayokoyama
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private EmployeeService employeeService;
	
	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		UpdateEmployeeForm updateEmployeeForm = new UpdateEmployeeForm();
		return updateEmployeeForm;
	}
	
	/**
	 * 従業員一覧を出力する.
	 * 
	 * @param model モデル
	 * @return　従業員一覧画面
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		return  "employee/list.html";
	}
	
	/**
	 * 従業員情報を受け取る.
	 * @param id ID
	 * @param model モデル
	 * @return 従業員詳細画面
	 */
	@RequestMapping("/showDetails")
	public String showDetails(String id, Model model) {
		int intId = Integer.parseInt(id); //String型をint型に変換する
		Employee employee = employeeService.showDetails(intId); //int型のIdを戻す
		
		model.addAttribute("employee", employee);
		return "employee/detail.html";
	}
	
	/**
	 * 扶養人数の更新.
	 * @param form フォーム
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		int intId = Integer.parseInt(form.getId()); //String型をint型に変換する
		Employee employee = employeeService.showDetails(intId);
		employeeService.Update(employee);
		return "/employee/showList";
	}
	
	/**
	 * ログアウトをする.
	 * @return ログイン画面
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "/";
	}

}
