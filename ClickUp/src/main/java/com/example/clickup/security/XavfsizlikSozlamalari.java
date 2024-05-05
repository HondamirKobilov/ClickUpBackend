package com.example.clickup.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;

@Configuration
@EnableWebSecurity
public class XavfsizlikSozlamalari extends WebSecurityConfigurerAdapter {
    @Autowired
    Filter filter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/users/addUser","/users/userslogin","/users/tasdiqlash").permitAll()
                .antMatchers("/workspace/addWorkSpace").permitAll()
                .antMatchers("/workspace/AddEditRemove/{workspaceId}/{lavozimId}").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .disable();
    }

    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("hondamirkobilov02@gmail.com");
        mailSender.setPassword("vwyapenimplnwxiy");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol","smtp");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.debug","true");
        return mailSender;
    }
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

/*
                .csrf() - Post mappinglarni ishlashiga yo'l ochib beradi. Aks holda postdan boshqa barchsi ishlaydi.
                .disable() - .csrf ni o'chirib qo'yganimiz uchun post qilish yoqiladi.
                .authorizeRequests() - lavozimlarni qaysi huquqlari bu apiga kirishga ruxsati borligini tekshiradi.
                .antMatchers("/users/?").permitAll() - url manzilni hech qanday avtorizatsiyalarsiz ochib qo'yadi.
                .anyRequest() - har qanday apilarni mavjudligini tekshiradi.
                .authenticated() - login va parol orqali tasdiqlab tizmga kirgan bo'lishi kerak.
                .and()
                .httpBasic() - biror bir apiga kirmoqchi bo'lganimizda server tomonidan berilgan login va parol
                orqali kirishimiz kerak boladi. Undan keyin esa api larga yo'l ochiladi.
                .disable(); - bu buyruq orqali esa shu login parol terish oynasi yo'qotiladi.
 */