package com.example.clickup.entitiy.abstractentity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class LongAbstractEntity extends MainAbstractEntitiy{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
