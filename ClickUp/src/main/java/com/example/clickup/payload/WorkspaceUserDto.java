package com.example.clickup.payload;

import com.example.clickup.entitiy.enums.BuyruqTuri;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkspaceUserDto {
    private String username;
    @Enumerated(EnumType.STRING)
    private BuyruqTuri buyruqTuri;
}
