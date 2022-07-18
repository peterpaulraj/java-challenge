/**
 * 
 */
package jp.co.axa.apidemo.vo;

import javax.validation.constraints.NotBlank;

/**
 * @author Peter
 *
 */
public class LoginVo extends CommonVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -81980904361783939L;

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
