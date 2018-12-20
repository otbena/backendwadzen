package com.wadzem.backendwadzen.repository;

import java.util.ArrayList;


import org.springframework.data.repository.Repository;


import com.wadzem.backendwadzen.model.Region;

public interface RegionRepository extends Repository<Region, Integer> {
	
	public ArrayList<Region> findRegionByProvince(int idProvince);

}
