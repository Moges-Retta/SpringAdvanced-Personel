package be.vdab.personeel.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource datasource;

    public SecurityConfig(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(datasource)
                .usersByUsernameQuery("select email as username, paswoord as password,true as enabled" +
                        " from werknemers where email = ?")
                .authoritiesByUsernameQuery("select ?, 'user'");
    }
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .mvcMatchers("/images/**")
                .mvcMatchers("/css/**")
                .mvcMatchers("/js/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin(login->login.loginPage("/login"));
        http.logout(logout->logout.logoutSuccessUrl("/"));
        http.authorizeRequests(requests -> requests
                .mvcMatchers("/Werknemershierarchie/**").hasAnyAuthority("user")
                .mvcMatchers("/", "/login").permitAll());
    }
}
