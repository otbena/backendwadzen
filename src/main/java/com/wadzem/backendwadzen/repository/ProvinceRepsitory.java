package com.wadzem.backendwadzen.repository;


import java.util.ArrayList;
import org.springframework.data.repository.Repository;

import com.wadzem.backendwadzen.model.Province;


public interface ProvinceRepsitory extends Repository<Province, Integer> {
 
	public ArrayList<Province> findAllProvince();
}
