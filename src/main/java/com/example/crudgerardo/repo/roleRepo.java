package com.example.crudgerardo.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crudgerardo.model.role;

public interface roleRepo extends JpaRepository<role,Integer> {
    List<role> findByNombreContaining(String nombre);
}
