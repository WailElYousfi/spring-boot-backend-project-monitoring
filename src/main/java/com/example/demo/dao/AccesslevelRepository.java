package com.example.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Accesslevel;


@Repository
public interface AccesslevelRepository extends JpaRepository<Accesslevel, Integer> {
	Boolean existsByAccesslevelId(Integer accesslevelId);

}