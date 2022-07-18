/**
 * 
 */
package jp.co.axa.apidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Spring boot - Application main class
 * 
 * @author Peter
 *
 */
@SpringBootApplication
@EnableCaching
public class ApiDemoApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiDemoApplication.class, args);

	}

}
