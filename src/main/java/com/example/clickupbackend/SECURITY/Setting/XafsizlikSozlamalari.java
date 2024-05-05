package com.example.clickupbackend.SECURITY.Setting;

import com.example.clickupbackend.SECURITY.Token.Filtr;
import com.example.clickupbackend.SERVISE.UsersServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Properties;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class XafsizlikSozlamalari extends WebSecurityConfigurerAdapter {

    @Autowired
    UsersServis usersServis;

    @Autowired
    Filtr filtr;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf() //postmappingni ishlatib beradi
                .disable()
                .authorizeRequests() //huquqlarni tekshirib ko'radi
                .antMatchers("/usersAPI/usersAdd").permitAll()
                .antMatchers("/usersAPI/xodimlogin").permitAll()
                .antMatchers("/workspace/workspaceAdd").permitAll()
                .anyRequest()//har qanday foydalanuvchini tekshirib kuradi
                .authenticated() // login paroli tugri bolish kerak
                .and()
                .httpBasic() //yullarni ochmasak brozerga api ni tashasak login parol ter deb aytadi
                .disable();
        http
                .addFilterBefore(filtr, UsernamePasswordAuthenticationFilter.class);
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("hondamirkobilov02@gmail.com");
        mailSender.setPassword("vwyapenimplnwxiy");
        Properties properties=mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol","smtp");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.debug","true");
        return mailSender;

    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usersServis)
                .passwordEncoder(passwordEncoder());

    }
}
