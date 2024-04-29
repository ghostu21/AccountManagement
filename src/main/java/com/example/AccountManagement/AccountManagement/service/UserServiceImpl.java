package com.example.AccountManagement.AccountManagement.service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.example.AccountManagement.AccountManagement.constant.UserRole;
//import com.example.AccountManagement.AccountManagement.constant.UserRole;
import com.example.AccountManagement.AccountManagement.dto.AuthResponseTO;
import com.example.AccountManagement.AccountManagement.dto.CreateUserRequestTO;
import com.example.AccountManagement.AccountManagement.dto.LoginRequestTO;
import com.example.AccountManagement.AccountManagement.exception.EmailExistsException;
import com.example.AccountManagement.AccountManagement.exception.InvalidExecption;
import com.example.AccountManagement.AccountManagement.exception.MobileExistsException;
import com.example.AccountManagement.AccountManagement.exception.NotFoundExecption;
import com.example.AccountManagement.AccountManagement.model.Account;
import com.example.AccountManagement.AccountManagement.model.User;
import com.example.AccountManagement.AccountManagement.repo.AccountRepository;
import com.example.AccountManagement.AccountManagement.repo.UserRepository;
import com.example.AccountManagement.AccountManagement.security.JwtProvider;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 

	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private CustomerUserDetailsServcie customerUserDetailsServcie;

	@Override
	public AuthResponseTO registerUser(CreateUserRequestTO userDTO) {
		
		
		if (userRepository.existsByEmail(userDTO.getEmail())) {
			throw new EmailExistsException("Email already exists");
		}

		// Check if a user with the provided mobile number already exists
		if (userRepository.existsByMobile(userDTO.getMobile())) {

			throw new MobileExistsException("Mobile number already exists");
		}

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		User user = new User();
		
		user.setEmail(userDTO.getEmail());
		// Encode password securely
		String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
		user.setPassword(encodedPassword);
		user.setMobile(userDTO.getMobile());

		user.setName(userDTO.getName());
		try {
			user.setDob(formatter.parse(userDTO.getDob()));
		} catch (ParseException e) {
			
			throw new InvalidExecption("Dob is invalid");
		}
		user.setCreatedTime(new Date());
		user.setLastModifiedTime(new Date());

		user.setRole(userDTO.getRole().name());

		user=userRepository.save(user);

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDTO.getEmail(),
				userDTO.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwts = jwtProvider.generateToken(authentication);
		
		
		AuthResponseTO authResponse = new AuthResponseTO();
		authResponse.setJwt(jwts);
		authResponse.setRole(user.getRole());

		authResponse.setMessage("Register success");
		
		
		if(userDTO.getRole()!=UserRole.ROLE_ADMIN) {
			Account account=new Account();
			
			account.setAccountName(user.getName());
			account.setUser(user);
//			Long time=new Date().getTime();
			
			String accountNumber=UUID.randomUUID().toString();
//					user.getName().substring(0,3).concat(time.toString().substring(0,4));
			
			account.setId(accountNumber);
			
			account=accountRepository.save(account);
			
			authResponse.setAccountId(account.getId());
			
		}
		
		return authResponse;
		
		
	}
	
	
	
	@Override
	public AuthResponseTO userSignIn(LoginRequestTO req) {

		String usernanme = req.getEmail();
		String password = req.getPassword();
		System.out.println("131");
		Authentication authentication = authenticate(usernanme, password);
		String jwts = jwtProvider.generateToken(authentication);

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

		AuthResponseTO authResponse = new AuthResponseTO();
		authResponse.setJwt(jwts);
		authResponse.setRole(role);

		authResponse.setMessage("Login success");

		return authResponse;

	}
	
	@Override
	public User getUserByEmail(String email) {
		if (email == null) {
			throw new InvalidExecption("Invalid Email");
		}
		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new NotFoundExecption("User Not Exist");
		}
		return user;

	}
	
	private Authentication authenticate(String username, String password) {

		UserDetails userDetails = customerUserDetailsServcie.loadUserByUsername(username);
		if (userDetails == null) {
			throw new BadCredentialsException("Invalid username…");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("invialid password…");
		}

		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
