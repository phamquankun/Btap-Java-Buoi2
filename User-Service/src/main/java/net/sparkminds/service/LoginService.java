package net.sparkminds.service;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sparkminds.common.APIResponse;
import net.sparkminds.dto.LoginRequestDTO;
import net.sparkminds.dto.SignUpRequestDTO;
import net.sparkminds.entity.User;
import net.sparkminds.repo.UserRepo;
import net.sparkminds.utils.JwtUtils;

@Service
public class LoginService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtUtils jwtUtils; 

	public APIResponse signup(SignUpRequestDTO signUpRequestDTO) {
		
		APIResponse apiResponse = new APIResponse();
		
		User userEntity = new User();

		userEntity.setLastname(signUpRequestDTO.getLastname());
		userEntity.setEmailId(signUpRequestDTO.getEmailId());
		userEntity.setFirstname(signUpRequestDTO.getFirstname());
		userEntity.setPassword(signUpRequestDTO.getPassword());
		userEntity.setAddress(signUpRequestDTO.getAddress());
		userEntity.setPhoneNumber(signUpRequestDTO.getPhoneNumber());

		userEntity = userRepo.save(userEntity);

		String token = jwtUtils.generateJwt(userEntity);
		
		Map<String, Object> data = new HashMap<>();
		
		data.put("accessToken", token);
		
		apiResponse.setData(data);
		
		return apiResponse;
	}

	public APIResponse login(@Valid LoginRequestDTO loginRequestDTO) {
		
		APIResponse apiResponse = new APIResponse();
		
		User user = userRepo.findOneByEmailIdIgnoreCaseAndPassword(loginRequestDTO.getEmailId(),
				loginRequestDTO.getPassword());
		
		if (user == null) {
			apiResponse.setData("Login failed");
			return apiResponse;
		}
		
		String token = jwtUtils.generateJwt(user);
		
		Map<String, Object> data = new HashMap<>();
		
		data.put("accessToken", token);
		
		apiResponse.setData(data);
		
		return apiResponse;
	}

}
