package com.example.clickup.service;

import com.example.clickup.entitiy.*;
import com.example.clickup.entitiy.enums.BuyruqTuri;
import com.example.clickup.entitiy.enums.WorkSpaceHuquqlarNomi;
import com.example.clickup.entitiy.enums.WorkSpaceLavozimNomlari;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.WorkspaceUserDto;
import com.example.clickup.payload.WorkSpaceDto;
import com.example.clickup.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkSpaceServiceImpliments implements WorkSpaceService{
    @Autowired
    WorkSpaceRepository workSpaceRepository;
    @Autowired
    IshchilarSoniRepository ishchilarSoniRepository;
    @Autowired
    ReklamaRepository reklamaRepository;
    @Autowired
    WorkSpaceLavozimlariRepository workSpaceLavozimlariRepository;
    @Autowired
    WorkSpaceUsersRepository workSpaceUsersRepository;
    @Autowired
    WorkSpaceHuquqlariRepository workSpaceHuquqlariRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    RezervUserRepository rezervUserRepository;
    @Override
    public ApiResponse addWorkSpace(WorkSpaceDto workSpaceDto) {
        Users users = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (workSpaceRepository.existsByNomiAndUsersId(workSpaceDto.getNomi(), users.getId())){
            return new ApiResponse("Kechirasiz siz oldin ham ushbu nom ostida WorkSpace yaratgansiz! Boshqa nom tanlang.", false);
        }
        Optional<IshchilarSoni> byIshchilarSoni = ishchilarSoniRepository.findByIshchilarSoni(workSpaceDto.getIshchilarSoni());
        if (!byIshchilarSoni.isPresent()){
            return new ApiResponse("Bunday ishchilar soni mavjud emas!", false);
        }
        Optional<Reklama> byReklamaNomi = reklamaRepository.findByNomi(workSpaceDto.getReklamaNomi());
        if (!byReklamaNomi.isPresent()){
            return new ApiResponse("Bunday reklama nomi topilmadi!", false);
        }
        WorkSpace workSpace=new WorkSpace(
                workSpaceDto.getNomi(),
                workSpaceDto.getProfilRangi(),
                workSpaceDto.getIshXonaRangi(),
                byIshchilarSoni.get(),
                users,
                byReklamaNomi.get()
        );
        workSpaceRepository.save(workSpace);
        WorkSpaceLavozimlari owner = workSpaceLavozimlariRepository.save(new WorkSpaceLavozimlari(workSpace, WorkSpaceLavozimNomlari.OWNER.name()));
        WorkSpaceLavozimlari admin = workSpaceLavozimlariRepository.save(new WorkSpaceLavozimlari(workSpace, WorkSpaceLavozimNomlari.ADMIN.name()));
        WorkSpaceLavozimlari member = workSpaceLavozimlariRepository.save(new WorkSpaceLavozimlari(workSpace, WorkSpaceLavozimNomlari.MEMBER.name()));
        WorkSpaceLavozimlari guest = workSpaceLavozimlariRepository.save(new WorkSpaceLavozimlari(workSpace, WorkSpaceLavozimNomlari.GUEST.name()));
        WorkSpaceHuquqlarNomi[] values = WorkSpaceHuquqlarNomi.values();
        List<WorkSpaceHuquqlari> workSpaceHuquqlariList=new ArrayList<>();
        for (WorkSpaceHuquqlarNomi value : values) {
            WorkSpaceHuquqlari workSpaceHuquqlari=new WorkSpaceHuquqlari(
                    owner,
                    value
            );
            workSpaceHuquqlariList.add(workSpaceHuquqlari);
            if (value.getWorkSpaceLavozimNomlariList().equals(WorkSpaceLavozimNomlari.ADMIN)){
                WorkSpaceHuquqlari workSpaceHuquqlari1=new WorkSpaceHuquqlari(
                        admin,
                        value
                );
                workSpaceHuquqlariList.add(workSpaceHuquqlari1);
            }
            if (value.getWorkSpaceLavozimNomlariList().equals(WorkSpaceLavozimNomlari.MEMBER)){
                WorkSpaceHuquqlari workSpaceHuquqlari1=new WorkSpaceHuquqlari(
                        member,
                        value
                );
                workSpaceHuquqlariList.add(workSpaceHuquqlari1);
            }
            if (value.getWorkSpaceLavozimNomlariList().equals(WorkSpaceLavozimNomlari.GUEST)){
                WorkSpaceHuquqlari workSpaceHuquqlari1=new WorkSpaceHuquqlari(
                        guest,
                        value
                );
                workSpaceHuquqlariList.add(workSpaceHuquqlari1);
            }
        }
        workSpaceHuquqlariRepository.saveAll(workSpaceHuquqlariList);

        workSpaceUsersRepository.save(new WorkSpaceUsers(
                users,
                workSpace,
                owner,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        ));
        return new ApiResponse("Yangi WorkSpace yaratildi.", true);
    }


    @Override
    public ApiResponse addeditremove(WorkspaceUserDto workspaceUserDto, Long workspaceId, Long lavozimId) {
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean b = workSpaceUsersRepository.existsByUsersIdAndWorkSpaceId(user.getId(), workspaceId);

        if (b && !user.getPlatformaLavozimlari().equals(WorkSpaceLavozimNomlari.GUEST)){
            if (workspaceUserDto.getBuyruqTuri().equals(BuyruqTuri.QOSHISH)){
                Optional<Users> byUsername = usersRepository.findByUsername(workspaceUserDto.getUsername());
                Optional<WorkSpace> byId = workSpaceRepository.findById(workspaceId);
                if (!byId.isPresent()) return new ApiResponse("Worksapce mavjud emas",false);
                Optional<WorkSpaceLavozimlari> byId1 = workSpaceLavozimlariRepository.findById(lavozimId);
                if (!byId1.isPresent()) return new ApiResponse("Worksapce Lavozimi mavjud emas",false);
                if (byUsername.isPresent()){
                    WorkSpaceUsers workSpaceUsers = new WorkSpaceUsers(
                            byUsername.get(),
                            byId.get(),
                            byId1.get(),
                            new Timestamp(System.currentTimeMillis()),
                            null
                    );
                    if (XabarYuborish(workspaceUserDto.getUsername(),workspaceId)){
                        workSpaceUsersRepository.save(workSpaceUsers);
                        return new ApiResponse("Taklif habari elektron pochtaga yuborildi",true);
                    }
                    return  new ApiResponse("Taklif ishchiga yuborilmadi",false);
                }
                RezervUser rezervUser = new RezervUser(
                        workspaceUserDto.getUsername(),
                        byId.get()
                );
                if (XabarYuborish(workspaceUserDto.getUsername(),workspaceId)){
                    rezervUserRepository.save(rezervUser);
                    return new ApiResponse("Taklif habari elektron pochtaga yuborildi",true);
                }
                return  new ApiResponse("Taklif ishchiga yuborilmadi",false);
            }
        }
        return new ApiResponse("Sizning lavozimingiz to'g'ri kelmaydi!!!!",false);
    }

    public boolean XabarYuborish(String email, Long workspaceId){
        try {
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setTo(email);
            simpleMailMessage.setFrom("hondamirkobilov02@gmail.com");
            simpleMailMessage.setSubject("Tasdiqlash kodi: ");
            simpleMailMessage.setText("<a href='http://localhost:8080/Users/tasdiqlash?email="+email+"&emailkod="+workspaceId+"'>Emailni tasdiqlash</a>");
            javaMailSender.send(simpleMailMessage);
            return true;
        }
        catch (Exception e){
            e.getStackTrace();
            return false;
        }
    }
}
