/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jacyirice.pw2.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author compo
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UsuarioDetailsConfig usuarioDetailsConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests() //define com as requisições HTTP devem ser tratadas com relação à segurança.
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/venda/list").hasAnyRole("ADMIN")
                .antMatchers("/venda/detail/**").hasAnyRole("ADMIN")
                .antMatchers("/clientePF/**").hasAnyRole("ADMIN")
                .anyRequest() //define que a configuração é válida para qualquer requisição.
                .authenticated() //define que o usuário precisa estar autenticado.
                .and()
                .formLogin() //define que a autenticação pode ser feita via formulário de login.
                .loginPage("/login") // passamos como parâmetro a URL para acesso à página de login que criamos
                .permitAll(); //define que essa página pode ser acessada por todos, independentemente do usuário estar autenticado ou não.
    }

    @Autowired
    public void configureUserDetails(AuthenticationManagerBuilder builder)
            throws Exception {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
        builder.userDetailsService(usuarioDetailsConfig).passwordEncoder(new BCryptPasswordEncoder());
//                .inMemoryAuthentication()
//                .withUser("teste").password(new BCryptPasswordEncoder().encode("123")).roles("EDITOR", "ADMIN");
    }

    /**
     * Com o método, instanciamos uma instância do encoder BCrypt e deixando o
     * controle dessa instância como responsabilidade do Spring. Agora, sempre
     * que o Spring Security necessitar disso, ele já terá o que precisa
     * configurado.
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
