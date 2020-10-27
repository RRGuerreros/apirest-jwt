package com.spring.security.login.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.security.login.config.service.JWTService;
import com.spring.security.login.entity.PasswordResetToken;
import com.spring.security.login.entity.Usuario;
import com.spring.security.login.repository.IUsuarioRepository;
import com.spring.security.login.repository.PasswordResetTokenRepository;
import com.spring.security.login.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	@Autowired
	private JWTService jwtService;
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	@Autowired
    private JavaMailSender primarySender;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
    
	@Override
	public boolean requestPasswordReset(String email) {
	
		boolean returnValue = false;
		
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		if( usuario == null ) {
			return returnValue;
		}
		
		String token = jwtService.generatePasswordResetToken(usuario.getUsername());
		
		PasswordResetToken passwordResetToken = new PasswordResetToken();
		
		passwordResetToken.setToken(token);
		passwordResetToken.setUsuario(usuario);
		passwordResetTokenRepository.save(passwordResetToken);
		
		log.info("Token generado -> " + token );
		
		
		// Email
		MimeMessage mimeMessage;
		
		mimeMessage = primarySender.createMimeMessage();
        
		String messageBody = "<p>Someone has requested to reset your password with our project. If it were not you, please ignore it.</p>"+
				"otherwise please click on the link below to set a new password: "+
				"<a href='http://localhost:4200/newpassword-reset?token=$token'>Click this link to Reset Password</a><br><br>Thank you!";
        
		messageBody = messageBody.replace("$token", token);
		
		log.info("Mensaje generado -> " + messageBody );
		
		MimeMessageHelper helper = new MimeMessageHelper( mimeMessage );
    	
        try {
        	
        	helper.setTo( email );
            helper.setSubject("Hi, "+usuario.getUsername());        	
			helper.setText(
			     "<div style='padding: 10px 30px 30px 30px;'>" +
			    	  "<p>Password change request : </p>" +
			    	  "<br>" + messageBody + "</div>", true );
			
		} catch (MessagingException e) {
			
			e.printStackTrace();
		
			return returnValue;
		}

        primarySender.send( mimeMessage );
		
		return true; 
	}

	@Override
	public boolean passwordReset(String token, String password) {
		
		if( jwtService.hasTokenExpired(token) ) {
			
			log.info("El token ah expirado, solicite otro token para poder cambiar la contraseña");
			
			return false;
		}
		
		
		PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
		
		if( passwordResetToken == null ) {
			
			log.info("TOKEN INVALIDO!, no exite en la base de datos");
			
			return false;
		}
		
		String encodePassword = bCryptPasswordEncoder.encode(password);
		
		
		Usuario usuario = passwordResetToken.getUsuario();
		usuario.setPassword(encodePassword);
		
		usuarioRepository.save(usuario);
		
		passwordResetTokenRepository.delete(passwordResetToken);
		
		return true;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
