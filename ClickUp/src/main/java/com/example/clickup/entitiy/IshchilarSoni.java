package com.example.clickup.entitiy;

import com.example.clickup.entitiy.abstractentity.LongAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class IshchilarSoni extends LongAbstractEntity {
    private String ishchilarSoni;
}
