package com.wadzem.backendwadzen.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.wadzem.backendwadzen.model.*;

import com.wadzem.backendwadzen.repository.AccountRepository;
import com.wadzem.backendwadzen.repository.AlertRepository;
import com.wadzem.backendwadzen.repository.BrandRepository;
import com.wadzem.backendwadzen.repository.CategoryRepository;
import com.wadzem.backendwadzen.repository.FlyerRepository;
import com.wadzem.backendwadzen.repository.PriceRepository;
import com.wadzem.backendwadzen.repository.ProductRepository;
import com.wadzem.backendwadzen.repository.ProvinceRepsitory;
import com.wadzem.backendwadzen.repository.RegionRepository;
import com.wadzem.backendwadzen.repository.ShoppingListRepository;
import com.wadzem.backendwadzen.repository.StoreRepository;

@RestController
@RequestMapping("api/mobile")
public class AccountControllerMobile {

	/* Declaration Repository
	========================================================================*/
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AlertRepository alertRepository;

	@Autowired
	private StoreRepository storeRepository;
	

	@Autowired
	private ProvinceRepsitory provinceRepository;
	
	@Autowired
	private PriceRepository priceRepository;
	
	@Autowired
	private ShoppingListRepository shoppinglistRepository;


	/* Add new account
	========================================================================*/
	@RequestMapping(method = RequestMethod.POST, value = "/addaccount")
	@CrossOrigin(origins = "http://localhost:8000")
	public boolean createAccount(@RequestBody Account account) {

		Date now = new Date();
		int codeActivation = 10000 + (int) (Math.random() * ((19999 - 10000) + 1));

		account.setActive((byte) 0);
		account.setVisible((byte) 1);
		account.setCreationDate(now);
		account.setConfirmationCode(codeActivation);
		String pwd = account.getAccountPassword();
		

		try {

			String cryptPass = cryptPassword.getKey(pwd);
			account.setAccountPassword(cryptPass);

			accountRepository.save(account);
			return true;

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
			return false;
		}

	}

	/* Add new alert
	========================================================================*/
	@RequestMapping(method = RequestMethod.POST, value = "/addalert")
	@CrossOrigin(origins = "http://localhost:8000")
	public boolean createAlert(@RequestBody Alert alert) throws IOException {

		Date currentDate = new Date();
		alert.setCreationDate(currentDate);
		
		alertRepository.save(alert);
		return true;

	}
	
	/* Get id product of alert
	========================================================================*/
	@GetMapping("/alertByIdProduct")
	@CrossOrigin(origins = "http://localhost:8000")
	List<Alert> getProductById() {
		return (List<Alert>) alertRepository.findAlertAllOnlyIdProduit();
	}

	/* Get id product of shopping list
	========================================================================*/
	@GetMapping("/shoppingListByIdProduct")
	@CrossOrigin(origins = "http://localhost:8000")
	List<Shoppinglist> getProductByIdShoppingList() {
		return (List<Shoppinglist>) shoppinglistRepository.findShoppingListAllOnlyIdProduit();
	}

	/* Get stores list
	========================================================================*/
	@GetMapping("/stores")
	@CrossOrigin(origins = "http://localhost:8000")
	List<Store> getStore() {
		return (List<Store>) storeRepository.findStores();
	}
	
	/* Get provinces list
	========================================================================*/
	@GetMapping("/provinces")
	@CrossOrigin(origins = "http://localhost:8000")
	List<Province> getProvince() {
		return (List<Province>) provinceRepository.findAllProvince();
	}
	

	
	/* Get product list
	========================================================================*/
	@GetMapping("/prices")
	@CrossOrigin(origins = "http://localhost:8000")
	List<Price> getPrice() {
		
		return (List<Price>) priceRepository.findAllPrices();
	}
	
	/* Get product list by name
	========================================================================*/
	@GetMapping("/login/{email}/{password}")
	@CrossOrigin(origins = "http://localhost:8000")
	List<Account> getLogin(@PathVariable String email, @PathVariable String password) throws NoSuchAlgorithmException {
		
		String cryptPass = cryptPassword.getKey(password);
		System.out.println(email + "/" + password);
		return (List<Account>) accountRepository.findLogin(email, cryptPass);
	}
	
	
	/* Delete alert by idAccount and idProduct
	========================================================================*/
	@GetMapping("/deletealert/{idAccount}/{idProduct}")
	@CrossOrigin(origins = "http://localhost:8000")
	int deleteAlert(@PathVariable int idAccount, @PathVariable int idProduct) {
		
		return alertRepository.deleteAlert(idAccount, idProduct);
		
	}
	
	/* Delete alert by idAlert
	========================================================================*/
	@GetMapping("/deletealertbyid/{idAlert}")
	@CrossOrigin(origins = "http://localhost:8000")
	int deleteAlertById(@PathVariable int idAlert) {
			return alertRepository.deleteAlertById(idAlert);
	}
	
	/* Delete alert by idAlert
	========================================================================*/
	@GetMapping("/deleteshoppinglistbyid/{idShoppingList}")
	@CrossOrigin(origins = "http://localhost:8000")
	int deleteShoppingListById(@PathVariable int idShoppingList) {
			return shoppinglistRepository.deleteShoppingListById(idShoppingList);
	}
	
	/* Add to shopping list
	========================================================================*/
	@RequestMapping(method = RequestMethod.POST, value = "/addshoppinglist")
	@CrossOrigin(origins = "http://localhost:8000")
	public boolean createShoppingList(@RequestBody Shoppinglist shoppinglist) throws IOException {

		Date currentDate = new Date();
		shoppinglist.setCreationDate(currentDate);
		
		shoppinglistRepository.save(shoppinglist);
		return true;

	}
	
	/* Get product list by name
	========================================================================*/
	@GetMapping("/alertbyuser/{idAccount}")
	@CrossOrigin(origins = "http://localhost:8000")
	List<Alert> getAlertByUser(@PathVariable int idAccount) {
		
		return (List<Alert>) alertRepository.findAlertByUser(idAccount);
	}
	
	/* Get product list
	========================================================================*/
	@GetMapping("/shoppinglist")
	@CrossOrigin(origins = "http://localhost:8000")
	List<Shoppinglist> getShoppingList() {
		
		return (List<Shoppinglist>) shoppinglistRepository.findShoppingList();
	}	
	
}