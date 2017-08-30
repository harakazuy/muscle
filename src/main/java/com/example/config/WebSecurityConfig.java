package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/bootstrap/**", "/js/**", "/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/loginForm", "/header", "/footer", "/login", "/loginPerform").permitAll()
                .anyRequest().authenticated()
                .antMatchers("/**").hasRole("USER")
                .and()
            .formLogin()
        		.loginProcessingUrl("/login")
                .loginPage("/login").failureUrl("/login?error")
                .defaultSuccessUrl("/loginPerform", true)
                .usernameParameter("username").passwordParameter("password")
                .permitAll()
                .and()
            .logout()
        		.logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
        		.logoutUrl("/logout")
        		.deleteCookies("JSESSIONID")
        		.invalidateHttpSession(true).permitAll()
        		.and()
        	.csrf()
        		.disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }
}