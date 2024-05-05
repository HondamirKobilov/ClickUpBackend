package com.example.clickupbackend.SERVISE;

import com.example.clickupbackend.DTO.Apiresponsive;
import com.example.clickupbackend.DTO.WorkspaceDto;
import com.example.clickupbackend.ENTITIY.*;
import com.example.clickupbackend.ENTITIY.Enums.WorkSpaceHuquqlarNomi;
import com.example.clickupbackend.ENTITIY.Enums.WorkspaceLavozimlarNomi;
import com.example.clickupbackend.REPOSITORIY.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkspaceServiseImp implements WorkspaceService {
    @Autowired
    WorkspaceRepositoriy workspaceRepositoriy;

    @Autowired
    IshchilarSoniRepositoriy ishchilarSoniRepositoriy;

    @Autowired
    ReklamaRepositoriy reklamaRepositoriy;

    @Autowired
    WorkspaceLAvozimlariRepositoriy workspaceLAvozimlariRepositoriy;

    @Autowired
    WorkspaceHuquqlarRepositoriy workspaceHuquqlarRepositoriy;

    @Autowired
    WorkspaceUsersRepositoriy workspaceUsersRepositoriy;

    @Override
    public Apiresponsive workspaceADD(WorkspaceDto workspaceDto) {
        Users users = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean b = workspaceRepositoriy.existsByNomiAndUsersId(workspaceDto.getNomi(), users.getId());
        if (b) {
            return new Apiresponsive("Bunday workspace yaratgansiz!!!", false);
        }
        Optional<IshchilarSoni> byIshchilarSoni = ishchilarSoniRepositoriy.findByIshchilarSoni(workspaceDto.getIshchilarSoni());
        if (!byIshchilarSoni.isPresent()){
            return new Apiresponsive("Bunday ishchilar soni mavjud emas!", false);
        }
        Optional<Reklama> byNomi = reklamaRepositoriy.findByNomi(workspaceDto.getReklamaNomi());
        if (!byNomi.isPresent()){
            return new Apiresponsive("Bunday reklama nomi topilmadi!", false);
        }
        Workspace workspace = new Workspace(
                workspaceDto.getNomi(),
                workspaceDto.getProfilRangi(),
                workspaceDto.getWorkspacePanellarRangi(),
                users,
                byIshchilarSoni.get(),
                byNomi.get()
        );
        workspaceRepositoriy.save(workspace);
        WorkSpaceLavozimlari owner = workspaceLAvozimlariRepositoriy.save(new WorkSpaceLavozimlari(
                workspace,
                WorkspaceLavozimlarNomi.OWNER.name()
        ));
        WorkSpaceLavozimlari admin = workspaceLAvozimlariRepositoriy.save(new WorkSpaceLavozimlari(
                workspace,
                WorkspaceLavozimlarNomi.ADMIN.name()
        ));
        WorkSpaceLavozimlari member = workspaceLAvozimlariRepositoriy.save(new WorkSpaceLavozimlari(
                workspace,
                WorkspaceLavozimlarNomi.MEMBER.name()
        ));
        WorkSpaceLavozimlari guest = workspaceLAvozimlariRepositoriy.save(new WorkSpaceLavozimlari(
                workspace,
                WorkspaceLavozimlarNomi.GUEST.name()
        ));
        WorkSpaceHuquqlarNomi[] values = WorkSpaceHuquqlarNomi.values();
        List<WorkSpaceHuquqlari> huquqlariList = new ArrayList<>();
        for (WorkSpaceHuquqlarNomi value : values) {
            WorkSpaceHuquqlari workSpaceHuquqlari = new WorkSpaceHuquqlari(
                    owner,
                    value
            );
            huquqlariList.add(workSpaceHuquqlari);
            if (value.getWorkspaceLavozimlarNomiList().equals(WorkspaceLavozimlarNomi.ADMIN)) {
                WorkSpaceHuquqlari workSpaceHuquqlari1 = new WorkSpaceHuquqlari(
                        admin,
                        value
                );
                huquqlariList.add(workSpaceHuquqlari1);
            }
            if (value.getWorkspaceLavozimlarNomiList().equals(WorkspaceLavozimlarNomi.MEMBER)) {
                WorkSpaceHuquqlari workSpaceHuquqlari1 = new WorkSpaceHuquqlari(
                        member,
                        value
                );
                huquqlariList.add(workSpaceHuquqlari1);
            }
            if (value.getWorkspaceLavozimlarNomiList().equals(WorkspaceLavozimlarNomi.GUEST)) {
                WorkSpaceHuquqlari workSpaceHuquqlari1 = new WorkSpaceHuquqlari(
                        guest,
                        value
                );
                huquqlariList.add(workSpaceHuquqlari1);
            }
        }
        workspaceHuquqlarRepositoriy.saveAll(huquqlariList);
        workspaceUsersRepositoriy.save(new WorkSpaceUsers(
                users,
                workspace,
                owner,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        ));
        return new Apiresponsive("Workspace yaratildi!!!", true);
    }
}
