package com.schen.projectdevice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService myUserDetailsService;

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/home").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/main").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/projectHome").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/DSHome").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/datasheet/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/file**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')").antMatchers("/email**")
				.access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')").antMatchers("/version")
				.access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')").antMatchers("/download**")
				.access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')").antMatchers("/preview**")
				.access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')").antMatchers("/register/admin**")
				.access("hasRole('ROLE_ADMIN')").antMatchers("/edit**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/add**").access("hasRole('ROLE_ADMIN')").antMatchers("/import**")
				.access("hasRole('ROLE_ADMIN')").antMatchers("/delete**").access("hasRole('ROLE_ADMIN')").and()
				.formLogin().loginPage("/access").loginProcessingUrl("/login").usernameParameter("username")
				.passwordParameter("password")
				// .and()
				// .logout()
				// .logoutUrl("/logout")
				// .logoutSuccessUrl("/access?logout")
				.and().csrf().and().exceptionHandling().accessDeniedPage("/error");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
