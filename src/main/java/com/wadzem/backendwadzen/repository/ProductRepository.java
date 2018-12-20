package com.wadzem.backendwadzen.repository;

import java.util.ArrayList;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.wadzem.backendwadzen.model.Product;


public interface ProductRepository extends CrudRepository<Product, Integer>{

	@Query("SELECT p.idProduct, p.nameFr, p.nameEn, p.productFormat, p.brand.name, p.category.nameFr, p.descriptionFr, p.descriptionEn, p.image FROM Product p")
	public ArrayList<Product> findAllProducts();
	@Query("SELECT p.idProduct, p.nameFr, p.nameEn, p.productFormat, p.brand.name, p.category.nameFr, p.descriptionFr, p.descriptionEn, p.image FROM Product p where p.nameFr =?1")
	public ArrayList<Product> findProductByName(String productName);
}
