package com.example.clickup.entitiy;

import com.example.clickup.entitiy.abstractentity.UuidAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class RezervUser extends UuidAbstractEntity {
    private String username;
    @ManyToOne
    private WorkSpace workSpace;
}
