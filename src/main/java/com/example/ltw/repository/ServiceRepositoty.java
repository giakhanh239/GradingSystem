package com.example.ltw.repository;

import com.example.ltw.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepositoty extends JpaRepository<Service,Long> {
    List<Service> findByNameContaining(String name);
}
