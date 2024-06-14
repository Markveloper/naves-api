package com.prueba.naves_api.repository;

import com.prueba.naves_api.model.Nave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NaveRepository extends JpaRepository<Nave, Long> {
    List<Nave> findByNameContainingIgnoreCase(String name);
}