package com.apap.farmasi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/medical-supplies/**").hasAnyAuthority("ADMIN_FARMASI")
			.antMatchers("/medical-supplies/**").hasAnyAuthority("STAF_APOTEKER")
			.antMatchers("/medical-supplies/tambah").hasAnyAuthority("ADMIN_FARMASI")
			.antMatchers("/medical-supplies/ubah/**").hasAnyAuthority("ADMIN_FARMASI")
			.antMatchers("/medical-supplies/perencanaan/tambah").hasAnyAuthority("ADMIN_FARMASI")
			.antMatchers("/medical-supplies/perencanaan").hasAnyAuthority("ADMIN_FARMASI")
			.antMatchers("/medical-supplies/perencanaan").hasAnyAuthority("STAF_APOTEKER")
			.antMatchers("/medical-supplies/permintaan/**").hasAnyAuthority("ADMIN_FARMASI")
			.antMatchers("/medical-supplies/permintaan/**").hasAnyAuthority("STAF_APOTEKER")
			.antMatchers("/medical-supplies/permintaan/ubah/**").hasAnyAuthority("ADMIN_FARMASI")
			.antMatchers("/medical-supplies/jadwal-staf/**").hasAnyAuthority("ADMIN_FARMASI")
			.antMatchers("/medical-supplies/jadwal-staf/tambah").hasAnyAuthority("ADMIN_FARMASI")
			.antMatchers("/medical-supplies/jadwal-staf/**").hasAnyAuthority("ADMIN_FARMASI")
			.antMatchers("/api/daftar-medical-service/**").hasAnyAuthority("ADMIN_IGD")
			.antMatchers("/api/medical-supplies/**").hasAnyAuthority("ADMIN_RAWAT_INAP")
			.antMatchers("/user/changePassword").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
			.permitAll();
	}
	
	
	/*@Autowired
	public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.passwordEncoder(encoder())
			.withUser("cokicoki").password(encoder().encode("enaksekali"))
			.roles("USER");
	}*/
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}
}