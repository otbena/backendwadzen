package com.wadzem.backendwadzen.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import com.wadzem.backendwadzen.model.Price;

public interface PriceRepository extends CrudRepository<Price, Integer> {

	public ArrayList<Price> findAllPrices();
}
