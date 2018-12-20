package com.wadzem.backendwadzen.repository;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.wadzem.backendwadzen.model.Shoppinglist;

public interface ShoppingListRepository  extends CrudRepository<Shoppinglist, Integer> {

	@Query("SELECT s.product.idProduct FROM Shoppinglist s")
	public ArrayList<Shoppinglist> findShoppingListAllOnlyIdProduit();
	
	@Query("SELECT s.idShoppingList, s.product.idProduct, s.product.nameFr, s.product.productFormat, s.product.image FROM Shoppinglist s")
	public ArrayList<Shoppinglist> findShoppingList();
	
	@Modifying
    @Transactional
	@Query("DELETE FROM Shoppinglist d where idShoppingList=?1")
	public int deleteShoppingListById(int idShoppingList);
}
