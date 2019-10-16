package com.example.demo;


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
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SSUserDetailsService userDetailsService;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception{
        return new SSUserDetailsService(userRepository);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/", "/h2-console/**", "/register", "/css/**").permitAll()

                .antMatchers("/admin", "/carform", "/categoryform", "/base")
                .access("hasAuthority('ADMIN')")

                .antMatchers("/categorylist", "/carlist", "/showcar", "/index")
                .access("hasAuthority('USER')")

                .anyRequest().authenticated()
                .and()

                .formLogin()
                .loginPage("/login").permitAll()  //.formLogin() - show login form. loginPage("page name").permitAll() - all can see the page
                .and()

                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll()
                .and()

                .httpBasic();
        http.csrf().disable();
        http.headers().frameOptions().disable();

    }

    @Override       //include this method or SpringBoot will give a random password to 'user'
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsServiceBean())
                .passwordEncoder(passwordEncoder());

    }


}
