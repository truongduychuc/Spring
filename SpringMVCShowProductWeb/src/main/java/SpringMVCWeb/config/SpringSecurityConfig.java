package SpringMVCWeb.config;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



import SpringMVCWeb.authentication.MyDBAuthenticationService;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	MyDBAuthenticationService myDBAuthenticationService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myDBAuthenticationService);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/accountInfo","/product").access("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')");
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		
		//for login form
		http.authorizeRequests().and().formLogin().loginProcessingUrl("/j_spring_security_check")
		.loginPage("/login").defaultSuccessUrl("/accountInfo").failureUrl("/login?error=true")
		.usernameParameter("userName").passwordParameter("password")
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/"); //log out processing
		
	}
	
	@Bean("passwordEncoder") //password encoder,spring security 5 don't define default password encoder bean, so it must be define manually
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
