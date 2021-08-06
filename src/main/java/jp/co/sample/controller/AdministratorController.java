package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者情報を操作するコントローラ.
 * 
 * @author yuyayokoyama
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
	@Autowired
	private HttpSession session;

	@Autowired
	private AdministratorService administratorService;
	
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		InsertAdministratorForm insertAdministratorForm = new InsertAdministratorForm();
		return insertAdministratorForm;
	}
	
	@ModelAttribute
	public LoginForm setUpForm(){
		LoginForm loginForm = new LoginForm();
		return loginForm;
	}
	/**
	 * 管理者登録画面にフォワードする.
	 * 
	 * @return 管理者登録画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	/**
	 * 従業員を登録する.
	 * 
	 * @param form 入力情報
	 * @return ログイン画面
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator admin = new Administrator();
		BeanUtils.copyProperties(form, admin);
		administratorService.insert(admin);
		return "redirect:/";
	}
	
	/**
	 * ログイン画面をフォワード.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "/administrator/login";
	}
	
	
	/**
	 * ログインをする処理.
	 * 
	 * @param form フォーム
	 * @param model モデル
	 * @return ログイン後の一覧画面
	 */
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
		
		if(administrator == null) {
			model.addAttribute("failure", "メールアドレスまたはパスワードが不正です。");
			return "administrator/login";
		}
		session.setAttribute("administratorName", administrator);
		return "forward:/employee/showList";
	}
	
	/**
	 * ログアウトをする.
	 * @return ログイン画面
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
}
