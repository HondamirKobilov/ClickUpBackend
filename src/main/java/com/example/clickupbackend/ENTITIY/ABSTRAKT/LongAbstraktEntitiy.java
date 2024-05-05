package com.example.clickupbackend.ENTITIY.ABSTRAKT;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class LongAbstraktEntitiy extends MainAbstraktEntitiy{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
