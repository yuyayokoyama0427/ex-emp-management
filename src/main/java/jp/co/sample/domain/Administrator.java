package jp.co.sample.domain;

/**
 * 管理者権限情報を表すドメイン
 * 
 * @author yuyayokoyama
 */
public class Administrator {
	/** ID */
	private Integer id;
	/** 名前 */
	private String name;
	/** メールアドレス */
	private String mailAddres;
	/** パスワード */
	private String password;
	
	// 引数なしコンストラクタ
	public Administrator() {
	}
	
	// コンストラクタ
	public Administrator(Integer id, String name, String mailAddres, String password) {
		this.id = id;
		this.name = name;
		this.mailAddres = mailAddres;
		this.password = password;
	}

	// getter,setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddres() {
		return mailAddres;
	}

	public void setMailAddres(String mailAddres) {
		this.mailAddres = mailAddres;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// toString
	@Override
	public String toString() {
		return "Administrator [id=" + id + ", name=" + name + ", mailAddres=" + mailAddres + ", password=" + password
				+ "]";
	}
	
	

}
