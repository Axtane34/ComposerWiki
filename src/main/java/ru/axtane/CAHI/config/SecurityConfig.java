package ru.axtane.CAHI.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.axtane.CAHI.services.PersonDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //конфигурирует spring security
        //конфигурирует авторизацию
        http.authorizeRequests()
                .antMatchers("/CAHI/login", "/CAHI/registration", "/error", "/CAHI/loginAsGuest", "/assets/**").permitAll()
                .antMatchers("/CAHI/adminPanel").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/CAHI/guest", "/composer/**",
                        "/arrangement/**", "/chants/**", "/chorus/**",
                        "/folkProcessing/**", "/opusDps/**").hasAnyRole("FREELANCER", "PROXY_USER", "MODERATOR", "ADMIN")
                .antMatchers(HttpMethod.PATCH, "/CAHI/guest", "/composer/**",
                        "/arrangement/**", "/chants/**", "/chorus/**",
                        "/folkProcessing/**", "/opusDps/**").hasAnyRole("FREELANCER", "PROXY_USER", "MODERATOR", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/CAHI/guest", "/composer/**",
                        "/arrangement/**", "/chants/**", "/chorus/**",
                        "/folkProcessing/**", "/opusDps/**").hasAnyRole("FREELANCER", "PROXY_USER", "MODERATOR", "ADMIN")
                .anyRequest().hasAnyRole("GUEST", "FREELANCER", "PROXY_USER", "MODERATOR", "ADMIN")
                .and().exceptionHandling().accessDeniedPage("/error")
                .and()
                .formLogin().loginPage("/CAHI/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/CAHI/account", true)
                .failureUrl("/CAHI/login?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/CAHI/login");
    }
    //настраивает аутентификацию
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
