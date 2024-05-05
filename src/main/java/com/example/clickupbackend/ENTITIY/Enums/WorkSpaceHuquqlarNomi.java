package com.example.clickupbackend.ENTITIY.Enums;

import java.util.Arrays;
import java.util.List;

public enum WorkSpaceHuquqlarNomi {
    ADD_REMOVE_MEMBER("Add/Remove Member","Workspace ga member qo'shish yoki o'chirish", Arrays.asList(WorkspaceLavozimlarNomi.ADMIN,WorkspaceLavozimlarNomi.MEMBER)),
    GIT("Git","Userlarga tasklarni Github,bitbacket,Gitlab larda ochib ko'rish",Arrays.asList(WorkspaceLavozimlarNomi.ADMIN,WorkspaceLavozimlarNomi.MEMBER)),
    EDIT_STATUS("Edit Status","Statuslarni tahrirlash",Arrays.asList(WorkspaceLavozimlarNomi.ADMIN,WorkspaceLavozimlarNomi.MEMBER));

    String nomi;
    String izoh;
    List<WorkspaceLavozimlarNomi> workspaceLavozimlarNomiList;

    WorkSpaceHuquqlarNomi(String nomi, String izoh, List<WorkspaceLavozimlarNomi> workspaceLavozimlarNomiList) {
        this.nomi = nomi;
        this.izoh = izoh;
        this.workspaceLavozimlarNomiList = workspaceLavozimlarNomiList;
    }

    public String getNomi() {
        return nomi;
    }

    public String getIzoh() {
        return izoh;
    }

    public List<WorkspaceLavozimlarNomi> getWorkspaceLavozimlarNomiList() {
        return workspaceLavozimlarNomiList;
    }
}
