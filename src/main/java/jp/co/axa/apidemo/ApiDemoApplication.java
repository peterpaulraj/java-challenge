/**
 * 
 */
package jp.co.axa.apidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
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
