package com.example.clickup.service;

import com.example.clickup.entitiy.Users;
import com.example.clickup.entitiy.enums.PlatformaLavozimlari;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.LoginDto;
import com.example.clickup.payload.TasdiqlashDto;
import com.example.clickup.payload.UsersDto;
import com.example.clickup.repository.UsersRepository;
import com.example.clickup.security.TokenGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TokenGenerate tokenGenerate;
    public ApiResponse addUsers(UsersDto usersDto) {
        boolean b = usersRepository.existsByUsername(usersDto.getUsername());
        if (b){
            return new ApiResponse("Bunday email oldin ro'yxatdan o'tgan.", false);
        }
        if (!usersDto.getFish().equals(null) && !usersDto.getUsername().equals(null) && !usersDto.getPassword().equals(null)){
            String kod= String.valueOf(new Random().nextInt(9999));
            Users users=new Users(
                    usersDto.getFish(),
                    usersDto.getUsername(),
                    passwordEncoder.encode(usersDto.getPassword()),
                    kod,
                    PlatformaLavozimlari.SYSTEMUSER
            );
            if (XabarYuborish(usersDto.getUsername(), kod)){
                usersRepository.save(users);
                return new ApiResponse("Ma'lumotlar saqlandi va email pochtaga xabar tasdiqlash kodi yuborildi.", true);
            }
            return new ApiResponse("Email adressda xatolik mavjud", false);
        }
        return new ApiResponse("Maydonlarning birini to'ldirmagansiz. Tekshirib qayta saqlang!", false);
    }
    public boolean XabarYuborish(String email, String kod){
        try {
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setTo(email);
            simpleMailMessage.setFrom("hondamirkobilov02@gmail.com");
            simpleMailMessage.setSubject("Tasdiqlash kodi: ");
            simpleMailMessage.setText(kod);
            javaMailSender.send(simpleMailMessage);
            return true;
        }catch (Exception e){
            e.getStackTrace();
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> byUsername = usersRepository.findByUsername(username);
        if (byUsername.isPresent()){
            return byUsername.get();
        }
        throw new UsernameNotFoundException("Bunday foydalanuvchi mavjud emas");
    }

    public ApiResponse usersLogin(LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        if (authenticate.isAuthenticated()){
            Optional<Users> byUsernameAndEmailKod = usersRepository.findByUsernameAndKod(loginDto.getUsername(), null);
            if (byUsernameAndEmailKod.isPresent()){
                Users principal = (Users) authenticate.getPrincipal();
                return new ApiResponse("Profilga xush kelibsiz "+byUsernameAndEmailKod.get().getFish()+"!\n"+tokenGenerate.TokenOlish(principal.getUsername()),true);
            }
            return new ApiResponse("Accountingiz faollashtirilmagan", false);
        }
        return new ApiResponse("Login yoki parol xato", false);
    }

    public ApiResponse Tasdiqlash(TasdiqlashDto tasdiqlashDto) {
        boolean b = usersRepository.existsByUsername(tasdiqlashDto.getUsername());
        if (b){
            Optional<Users> byUsernameAndKod = usersRepository.findByUsernameAndKod(tasdiqlashDto.getUsername(), null);
            if (byUsernameAndKod.isPresent()){
                return new ApiResponse("Bu account oldin faollashtirilgan!", false);
            }
            Optional<Users> byUsernameAndKod1 = usersRepository.findByUsernameAndKod(tasdiqlashDto.getUsername(), tasdiqlashDto.getKod());
            if (byUsernameAndKod1.isPresent()){
                Users users = byUsernameAndKod1.get();
                users.setEnabled(true);
                users.setKod(null);
                usersRepository.save(users);
                return new ApiResponse("Account faollashtirildi!", true);
            }
            return new ApiResponse("Xatolik mavjud", false);
        }
        return new ApiResponse("Budnay foydalanavchi mavjud emas.", false);
    }
}
