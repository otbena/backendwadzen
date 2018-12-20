package com.wadzem.backendwadzen.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.wadzem.backendwadzen.model.Category;


public interface CategoryRepository extends CrudRepository<Category, Integer> {

	@Query("SELECT c.idCategory, c.nameFr, c.nameEn  FROM Category c")
	public ArrayList<Category> findAllCategory();
}
