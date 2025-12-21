package com.vistudiyo.registration.repository;

import com.vistudiyo.registration.entity.Editors;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegisterRepo extends JpaRepository<Editors,Integer> {
    @Query(nativeQuery = true,value = "select * from editors e where e.editorID = ?1")
    Optional<Editors> findById(UUID id);

    @Query(nativeQuery = true,value = "select * from editors e where e.user_name = ?1")
    Optional<Editors> findByUsername( String username);

}
