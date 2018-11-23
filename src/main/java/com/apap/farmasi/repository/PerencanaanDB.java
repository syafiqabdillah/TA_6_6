package com.apap.farmasi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.farmasi.model.PerencanaanModel;


@Repository
public interface PerencanaanDB extends JpaRepository<PerencanaanModel, Long>{
	List<PerencanaanModel> findAll();
}
