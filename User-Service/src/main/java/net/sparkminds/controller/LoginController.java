package net.sparkminds.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.sparkminds.common.APIResponse;
import net.sparkminds.dto.LoginRequestDTO;
import net.sparkminds.dto.SignUpRequestDTO;
import net.sparkminds.service.LoginService;

@RestController
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/signup")
	public ResponseEntity<APIResponse> signUp(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
		APIResponse apiResponse = loginService.signup(signUpRequestDTO);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	@PostMapping("/login")
	public ResponseEntity<APIResponse> logIn(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
		APIResponse apiResponse = loginService.login(loginRequestDTO);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
}
