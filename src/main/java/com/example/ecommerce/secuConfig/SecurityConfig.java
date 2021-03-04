package com.example.ecommerce.secuConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource);
                /*
                .withUser(User.withUsername("user")
                        .password(passwordEncoder().encode("pass"))
                        .roles("USER"));
                 */

        //BCryptPasswordEncoder bcpe= getBCPE();//
        //pour definir la maniere dont on va chercher les utilisateurs
       /* auth.inMemoryAuthentication().withUser("Arbia").password(bcpe.encode("1234")).roles("USER");
        auth.inMemoryAuthentication() .withUser("User").password(bcpe.encode("1234")).roles( "USER");
        auth.inMemoryAuthentication().withUser("Admin").password(bcpe.encode("1234")).roles( "USER","ADMIN");
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // pour definir les strategies de securités,, les regles
        //on demande a Spring qu'on a besoin de passer par une authentification basée par un formulaire
        http.formLogin().loginPage("/login");

        //securiser les ressources de l'appli
        http.authorizeRequests()
                .antMatchers("/deleteProduit").hasRole("admin")
                .antMatchers("/AjoutPanier").hasRole("USER")
                .antMatchers("/deleteProduitPanier").hasRole("USER");


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
