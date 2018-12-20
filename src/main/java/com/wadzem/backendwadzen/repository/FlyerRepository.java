package com.wadzem.backendwadzen.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.wadzem.backendwadzen.model.Flyer;


public interface FlyerRepository extends CrudRepository<Flyer, Integer> {

	@Query("SELECT f.idFlyer, f.store.name, f.region.region.province.province, f.region.region, f.startDate, f.endDate, f.flyerFile FROM Flyer f")
	public ArrayList<Flyer> findAllFlyers();
}
