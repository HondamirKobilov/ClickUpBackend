package com.example.clickupbackend.SERVISE;

import com.example.clickupbackend.DTO.Apiresponsive;
import com.example.clickupbackend.DTO.LoginDto;
import com.example.clickupbackend.DTO.TasdiqlashDto;
import com.example.clickupbackend.DTO.UsersDto;
import com.example.clickupbackend.ENTITIY.Enums.PlatformaLavozimlari;
import com.example.clickupbackend.ENTITIY.Users;
import com.example.clickupbackend.REPOSITORIY.UsersRepositoriy;
import com.example.clickupbackend.SECURITY.Token.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UsersServis implements UserDetailsService {
    @Autowired
    UsersRepositoriy usersRepositoriy;
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenGenerator tokenGenerator;
    public Apiresponsive userAdd(UsersDto usersDto) {
        Optional<Users> byUsername = usersRepositoriy.findByUsername(usersDto.getUsername());
        if (!byUsername.isPresent()){
            if (usersDto.getPassword().equals(usersDto.getRepassword())){
                Users users = new Users(
                        usersDto.getFish(),
                        usersDto.getUsername(),
                        usersDto.getPassword(),
                        PlatformaLavozimlari.SYSTEMUSER
                );
                String parol = String.valueOf(new Random().nextInt(9999));
                if (XabarYuborish(usersDto.getUsername(), parol)){
                    usersRepositoriy.save(users);
                    return new Apiresponsive(" Ro'yxatdan o'tdingiz electron pochtangizga tasdiqlash kodi yuborildi!!!",true);
                }
                return new Apiresponsive("Xabar yuborilmadi!!!",false);
            }
            return new Apiresponsive("Parollar mos emas!!!",false);
        }
        return new Apiresponsive("Bunday foydalanuvchi oldin ruyxatdan o'tgan!!!",false);
    }
    public boolean XabarYuborish(String email, String parol){
        try {
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setTo(email);
            simpleMailMessage.setFrom("hondamirkobilov02@gmail.com");
            simpleMailMessage.setSubject("Tasdiqlash kodi: ");
            simpleMailMessage.setText(String.valueOf(parol));
            javaMailSender.send(simpleMailMessage);
            return true;
        }
        catch (Exception e){
            e.getStackTrace();
            return false;
        }
    }

    public Apiresponsive faollashtirish(TasdiqlashDto tasdiqlashDto) {
        boolean b = usersRepositoriy.existsByUsername(tasdiqlashDto.getUsername());
        if (b){
            Optional<Users> byUsernameAndKod = usersRepositoriy.findByUsernameAndEmailKod(tasdiqlashDto.getUsername(), null);
            if (byUsernameAndKod.isPresent()){
                return new Apiresponsive("Bu account oldin faollashtirilgan!", false);
            }
            Optional<Users> byUsernameAndKod1 = usersRepositoriy.findByUsernameAndEmailKod(tasdiqlashDto.getUsername(), tasdiqlashDto.getKod());
            if (byUsernameAndKod1.isPresent()){
                Users users = byUsernameAndKod1.get();
                users.setEnabled(true);
                users.setEmailKod(null);
                usersRepositoriy.save(users);
                return new Apiresponsive("Account faollashtirildi!", true);
            }
            return new Apiresponsive("Xatolik mavjud", false);
        }
        return new Apiresponsive("Budnay foydalanavchi mavjud emas.", false);
    }

    public UserDetails loadUserByUsername(String username) {
        Optional<Users> byUsername = usersRepositoriy.findByUsername(username);
        if (byUsername.isPresent()){
            return byUsername.get();
        }
        throw new UsernameNotFoundException("Bunday foydalanuvchi mavjud emas");
    }
    public Apiresponsive UserLogin(LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        if (authenticate.isAuthenticated()){
            Optional<Users> byUsernameAndEmailKod = usersRepositoriy.findByUsernameAndEmailKod(loginDto.getUsername(), null);
            if (byUsernameAndEmailKod.isPresent()){
                Users principal = (Users) authenticate.getPrincipal();
                return new Apiresponsive("Profilga xush kelibsiz "+byUsernameAndEmailKod.get().getFish()+"!\n"+tokenGenerator.TokenOlish(principal.getUsername()),true);
            }
            return new Apiresponsive("Accountingiz faollashtirilmagan", false);
        }
        return new Apiresponsive("Login yoki parol xato", false);
    }
}
