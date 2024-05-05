package com.example.clickupbackend.REPOSITORIY;

import com.example.clickupbackend.ENTITIY.IshchilarSoni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IshchilarSoniRepositoriy extends JpaRepository<IshchilarSoni, Long> {
    Optional<IshchilarSoni> findByIshchilarSoni(String ishchilarSoni);
}
