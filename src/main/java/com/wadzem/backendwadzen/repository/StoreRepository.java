package com.wadzem.backendwadzen.repository;

import java.util.ArrayList;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.wadzem.backendwadzen.model.Store;

public interface StoreRepository extends CrudRepository<Store, Integer>{
	
	@Query("SELECT s.idStore, s.name, s.image FROM Store s")
	public ArrayList<Store> findStores();

}
