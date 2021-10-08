package com.abc.shop.security;

import com.abc.shop.filter.CustomAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new
                CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/user/login");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
//        http.formLogin().usernameParameter("email");
//        http.formLogin().passwordParameter("password");
        http.authorizeRequests().antMatchers("/api/login/**").permitAll();
        http.authorizeRequests().anyRequest().permitAll();
//        http.authorizeRequests().anyRequest().authenticated();
//        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilter(customAuthenticationFilter);


//        http
//                .authorizeRequests()
//
//                .mvcMatchers("/login").anonymous()
//                .mvcMatchers("/user/**").hasRole("USER")
//
//                .and()
//                .csrf().disable()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/")
//                .failureUrl("/login?error=true")
//                .usernameParameter("email")
//                .passwordParameter("password")
//
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/403")
//
//                .and()
//                .logout()
//                .permitAll()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID");

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager();
    }
}
