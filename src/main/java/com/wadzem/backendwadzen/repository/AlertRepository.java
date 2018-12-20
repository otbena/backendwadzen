package com.wadzem.backendwadzen.repository;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.wadzem.backendwadzen.model.Alert;


public interface AlertRepository extends CrudRepository<Alert, Integer>{
	
	@Query("SELECT a.product.idProduct FROM Alert a")
	public ArrayList<Alert> findAlertAllOnlyIdProduit();

	
	@Query("SELECT al.idAlert, al.product.nameFr, al.product.productFormat, al.creationDate, al.product.image FROM Alert al where idAccount=?1")
	public ArrayList<Alert> findAlertByUser(int idAccount);
	
	@Modifying
    @Transactional
	@Query("DELETE FROM Alert d where idAccount=?1 and idProduct=?2")
	public int deleteAlert(int idAccount, int idProduct);
	
	
	@Modifying
    @Transactional
	@Query("DELETE FROM Alert d where idAlert=?1")
	public int deleteAlertById(int idAlert);
	



}
