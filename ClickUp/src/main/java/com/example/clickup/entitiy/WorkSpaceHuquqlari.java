package com.example.clickup.entitiy;

import com.example.clickup.entitiy.abstractentity.LongAbstractEntity;
import com.example.clickup.entitiy.enums.WorkSpaceHuquqlarNomi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WorkSpaceHuquqlari extends LongAbstractEntity {
    @ManyToOne
    private WorkSpaceLavozimlari workSpaceLavozimlari;
    @Enumerated(EnumType.STRING)
    private WorkSpaceHuquqlarNomi workSpaceHuquqlarNomi;
}