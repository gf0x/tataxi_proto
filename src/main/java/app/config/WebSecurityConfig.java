package app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


/**
 * Created by Alex_Frankiv on 20.03.2017.
 */
@Configuration
@ComponentScan(basePackages = "app.config")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Md5PasswordEncoder passwordEncoder;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(passwordEncoder).dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT login, pswd, enabled from \"user\" WHERE login=?"
                )
                .authoritiesByUsernameQuery(
                        "SELECT login, auth_role FROM \"user\" WHERE login=?"
                );
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers(/*"/admin/**"*/"/").hasAnyRole("ADMIN", "CLIENT", "DISPATCHER", "DRIVER")
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("lgn")
                .passwordParameter("pswd")
                .defaultSuccessUrl("/", false)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")
                .and()
                .rememberMe()
                .key("rem-me-key")
                .rememberMeParameter("ttx-remember-me")
                .rememberMeCookieName("ttx-remember-me")
                .tokenValiditySeconds(86400)
                .and()
                .csrf().disable();
    }
}
