package com.Springmvc.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.Springmvc.Service.ImplUserDertailsService;


@Configuration
@EnableWebSecurity
public class WebConfigSecurity  {
	
     @Autowired
	 private ImplUserDertailsService service;
	
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http 
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	                .requestMatchers(HttpMethod.GET ,"/").permitAll()
	                .anyRequest().authenticated()
	            )
	        .formLogin(formLogin ->
	         formLogin.permitAll()	           
	            
	    );
	        
	        http.logout(lOut -> {
	            lOut.invalidateHttpSession(true)
	                .clearAuthentication(true)	                
	                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))	                
	                .logoutSuccessUrl("/login?logout")	
	                .permitAll();
	          });
	         
	          return http.build();
  
	 }
       
	  protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		  
		  auth.userDetailsService(service)
		  .passwordEncoder(passwordEncoder());
	  }
	 
	  @Bean
		public PasswordEncoder passwordEncoder() {
		        return new BCryptPasswordEncoder();    
		}
    }	 
	
 