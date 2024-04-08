package com.example.crudgerardo.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crudgerardo.model.usuaruio;

public interface usuariosRepo extends JpaRepository<usuaruio,Integer> {
    List<usuaruio> findByNombreContaining(String nombre);
}