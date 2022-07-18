/**
 * 
 */
package jp.co.axa.apidemo.constants;

/**
 * Security Constant
 * 
 * @author Peter
 */
public class SecurityConstant {

	public static final String H2_CONSOLE = "/h2-console/**";

	public static final String[] SWAGGER_UI = {
			// Swagger UI v2
			"/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
			"/configuration/security", "/swagger-ui.html", "/webjars/**",
			// -- Swagger UI v3 (OpenAPI)
			"/v3/api-docs/**", "/swagger-ui/**" };

	/**
	 * This class only static method. It's private constructor, can't be initialize
	 * the object
	 */
	private SecurityConstant() {

	}

}
