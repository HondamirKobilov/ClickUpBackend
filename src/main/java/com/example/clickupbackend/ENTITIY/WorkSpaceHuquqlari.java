package com.example.clickupbackend.ENTITIY;
import com.example.clickupbackend.ENTITIY.ABSTRAKT.LongAbstraktEntitiy;
import com.example.clickupbackend.ENTITIY.Enums.WorkSpaceHuquqlarNomi;
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
public class WorkSpaceHuquqlari extends LongAbstraktEntitiy {
    @ManyToOne
    private WorkSpaceLavozimlari workSpaceLavozimlari;

    @Enumerated(EnumType.STRING)
    private WorkSpaceHuquqlarNomi workSpaceHuquqlarNomi;
}