package com.example.clickup.entitiy.abstractentity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.util.UUID;

@Data // bu anotatsiya bizga getter va setter funksiyalarni o'zimiz yaratmasdan foydalanishimizga yordam beradi.
@MappedSuperclass // n ta class ushbu classdan extends qilganda ishlab berishini ta'minlab beradi.
public abstract class MainAbstractEntitiy {
    @Column(updatable = false)
    @CreationTimestamp // yaratilgan vaqtni saqlab qo'yadi. @Column(updatable=false) orqali jadvaldagi ma'lumot qayta tahrirlanganda shu ustundagi ma'lumot qayta tahrirlanishi o'chirib qo'yiladi.
    private Timestamp yaratilganVaqt;
    @UpdateTimestamp // jadvaldagi ma'lumot tahrirlangan vaqtni o'zida saqlaydi.
    private Timestamp tahrirlanganVaqt;
    @CreatedBy // kim tomonidan yaratilganligini token orqali aniqlab idsidagi ma'lumotni saqlab qo'yadi.
    private UUID kimTomonidanYaratilgan;
    @LastModifiedBy //bu esa jadvaldagi ma'lumot kim tomonidan tahrirlanganligini token orqali aniqlab idsini saqlab qo'yadi.
    private UUID kimTomonidanTahrirlangan;
}
