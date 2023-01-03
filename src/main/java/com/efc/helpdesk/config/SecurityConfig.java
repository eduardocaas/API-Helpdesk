package com.efc.helpdesk.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	private static final String[] PUBLIC_MATCHERS = {"/h2-console/**"}; // Libera acesso ao H2
	
	@Autowired
	private Environment env;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			
			http.headers().frameOptions().disable();
			
		}
		
		http.cors().and() // Habilita Cors
			.csrf().disable() // Desabilita proteção ataque CSRF pois o sistema não armazena sessão de usuário 
			.authorizeHttpRequests((authz) -> authz.anyRequest().permitAll()).securityMatcher(PUBLIC_MATCHERS) // Permite URL definida na váriavel
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Não permite criação de sessão para usuários 
				
		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues(); // Libera métodos padrão (GET, HEAD, POST)
		configuration.setAllowedMethods(Arrays.asList("POST", "GET" , "PUT" , "DELETE" , "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); // Configuração de Cors
		source.registerCorsConfiguration("/**", configuration); 
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bEncoder() { // Decodificação de senhas no DB
		
		return new BCryptPasswordEncoder();
		
	}
}
