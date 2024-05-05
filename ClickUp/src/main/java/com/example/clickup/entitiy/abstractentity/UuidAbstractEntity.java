package com.example.clickup.entitiy.abstractentity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class UuidAbstractEntity extends MainAbstractEntitiy{
    @Id
    @GeneratedValue(generator = "UUID2")
    private UUID id;
}
