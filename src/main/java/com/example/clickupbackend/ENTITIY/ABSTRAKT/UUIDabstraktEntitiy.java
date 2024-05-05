package com.example.clickupbackend.ENTITIY.ABSTRAKT;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class
UUIDabstraktEntitiy extends MainAbstraktEntitiy {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

}
