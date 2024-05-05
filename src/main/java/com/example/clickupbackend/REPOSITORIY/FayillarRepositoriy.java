package com.example.clickupbackend.REPOSITORIY;

import com.example.clickupbackend.ENTITIY.Fayllar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FayillarRepositoriy extends JpaRepository<Fayllar, UUID> {

}
