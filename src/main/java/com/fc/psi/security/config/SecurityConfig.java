package com.fc.psi.security.config;

import com.fc.psi.security.service.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityUserService securityUserService;
    @Autowired
    private SecurityYml securityYml;
    @Autowired
    private SecurityPasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.securityUserService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf() //跨站
                .disable() //关闭跨站检测
                .authorizeRequests()//验证策略策略链
                .antMatchers("/elementui/**","/vue/**").permitAll()
                .antMatchers("/home.action").permitAll()//放行登录
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/home.action")
                .loginProcessingUrl("/login.action")
                .defaultSuccessUrl("/index.action");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
