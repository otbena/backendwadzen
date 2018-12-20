package com.wadzem.backendwadzen.repository;


import java.util.ArrayList;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.wadzem.backendwadzen.model.Account;

public interface AccountRepository extends CrudRepository<Account, Integer>{
	
	@Query("SELECT a.idAccount, a.firstName, a.email, a.accountPassword, a.role, a.active FROM Account a where a.email=?1 and a.accountPassword=?2")
	public ArrayList<Account> findLogin(String email, String password);
}
