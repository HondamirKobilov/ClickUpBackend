package com.example.clickup.entitiy.enums;

import java.util.Arrays;
import java.util.List;

public enum WorkSpaceHuquqlarNomi {
    ADD_REMOVE_MEMBER("Add/Remove Members", "Workspacega memberslarni qo'shadi yoki o'chiradi", Arrays.asList(WorkSpaceLavozimNomlari.OWNER, WorkSpaceLavozimNomlari.ADMIN, WorkSpaceLavozimNomlari.MEMBER)),
    GIT("Git", "Userlarga tasklarni GitHub, BitBucket", Arrays.asList(WorkSpaceLavozimNomlari.OWNER, WorkSpaceLavozimNomlari.ADMIN, WorkSpaceLavozimNomlari.MEMBER)),
    EDIT_STATUS("Edit", "Statuslarni edit qilish", Arrays.asList(WorkSpaceLavozimNomlari.OWNER, WorkSpaceLavozimNomlari.ADMIN, WorkSpaceLavozimNomlari.MEMBER));
    String nomi;
    String izoh;
    List<WorkSpaceLavozimNomlari> workSpaceLavozimNomlariList;

    WorkSpaceHuquqlarNomi(String nomi, String izoh, List<WorkSpaceLavozimNomlari> workSpaceLavozimNomlariList) {
        this.nomi = nomi;
        this.izoh = izoh;
        this.workSpaceLavozimNomlariList = workSpaceLavozimNomlariList;
    }

    public String getNomi() {
        return nomi;
    }

    public String getIzoh() {
        return izoh;
    }

    public List<WorkSpaceLavozimNomlari> getWorkSpaceLavozimNomlariList() {
        return workSpaceLavozimNomlariList;
    }
}
