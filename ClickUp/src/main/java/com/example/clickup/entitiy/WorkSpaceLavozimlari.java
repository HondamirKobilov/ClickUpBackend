package com.example.clickup.entitiy;

import com.example.clickup.entitiy.abstractentity.LongAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WorkSpaceLavozimlari extends LongAbstractEntity {
    @ManyToOne
    private WorkSpace workSpace;
    @Column(nullable = false)
    private String lavozmiNomlari;
}
