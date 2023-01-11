//package br.com.gerenciador.biblioteca;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.SecurityBuilder;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.*;
//
//    @EnableWebSecurity
//    public class SecurityConfig extends WebSecurityConfigurer {
//
//        //@Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .authorizeRequests()
//                   // .antMatchers("/css/**", "/index").permitAll()
//                    //.antMatchers("/user/**").hasRole("USER")
//                    .and()
//                    .formLogin()
//                    .loginPage("/login").failureUrl("/login-error");
//        }
//
//        @Autowired
//        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//            auth
//                    .inMemoryAuthentication();
//                    //.withUser("user").password("password").roles("USER");
//        }
//
//        @Override
//        public void init(SecurityBuilder builder) throws Exception {
//
//        }
//
//        @Override
//        public void configure(SecurityBuilder builder) throws Exception {
//
//        }
//    }
//
