/**
 * 
 */
package jp.co.axa.apidemo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.axa.apidemo.security.JwtUtils;
import jp.co.axa.apidemo.security.UserDetailsImpl;
import jp.co.axa.apidemo.vo.LoginResponseVo;
import jp.co.axa.apidemo.vo.LoginVo;

/**
 * Handle the user Auth informations
 * @author Peter
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class LoginController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginVo loginVo) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginVo.getUsername(), loginVo.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(
				new LoginResponseVo(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout() {
		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("Successfully signed out!!!");
	}

}
