package com.wadzem.backendwadzen.repository;


import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import com.wadzem.backendwadzen.model.*;

public interface BrandRepository extends CrudRepository<Brand, Integer>{
	
	@Query("SELECT b.idBrand, b.name, b.description FROM Brand b")
	public List<Brand> findAllBrand();
}
