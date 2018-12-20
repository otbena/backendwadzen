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
import com.wadzem.backendwadzen.repository.BrandRepository;
import com.wadzem.backendwadzen.repository.CategoryRepository;
import com.wadzem.backendwadzen.repository.FlyerRepository;
import com.wadzem.backendwadzen.repository.PriceRepository;
import com.wadzem.backendwadzen.repository.ProductRepository;
import com.wadzem.backendwadzen.repository.ProvinceRepsitory;
import com.wadzem.backendwadzen.repository.RegionRepository;
import com.wadzem.backendwadzen.repository.StoreRepository;

@RestController
//@RequestMapping("/api")
public class AccountController {

	/* Declaration Repository
	========================================================================*/
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private FlyerRepository flyerRepository;
	
	@Autowired
	private ProvinceRepsitory provinceRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PriceRepository priceRepository;

	/* Get accounts list
	========================================================================*/
	@GetMapping("/accounts")
	@CrossOrigin(origins = "http://localhost:4200")
	List<Account> getAccounts() {
		return (List<Account>) accountRepository.findAll();
	}
	

	/* Add new account
	========================================================================*/
	@RequestMapping(method = RequestMethod.POST, value = "/addaccount")
	@CrossOrigin(origins = "http://localhost:4200")
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

	/* Get category list
	========================================================================*/
	@GetMapping("/categories")
	@CrossOrigin(origins = "http://localhost:4200")
	List<Category> getCategorys() {
		return (List<Category>) categoryRepository.findAllCategory();
	}

	/* Add new category
	========================================================================*/
	@RequestMapping(method = RequestMethod.POST, value = "/addcategory")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean createCategory(@RequestBody Category category) {

		categoryRepository.save(category);

		return true;
	}


	/* Add new brand
	========================================================================*/
	@RequestMapping(method = RequestMethod.POST, value = "/addbrand")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean createBrand(@RequestBody Brand brand) {

		brandRepository.save(brand);
		return true;

	}

	/* Get brands list
	========================================================================*/
	@GetMapping("/brands")
	@CrossOrigin(origins = "http://localhost:4200")
	List<Brand> getBrand() {
		return (List<Brand>) brandRepository.findAllBrand();
	}


	/* Add new store
	========================================================================*/
	@RequestMapping(method = RequestMethod.POST, value = "/addstore")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean createStore(@RequestBody Store store) throws IOException {

		storeRepository.save(store);
		return true;

	}


	/* Get stores list
	========================================================================*/
	@GetMapping("/stores")
	@CrossOrigin(origins = "http://localhost:4200")
	List<Store> getStore() {
		return (List<Store>) storeRepository.findStores();
	}

	
	/* Get provinces list
	========================================================================*/
	@GetMapping("/provinces")
	@CrossOrigin(origins = "http://localhost:4200")
	List<Province> getProvince() {
		return (List<Province>) provinceRepository.findAllProvince();
	}
	
	
	/* Get regions list
	========================================================================*/
	@GetMapping("/regions/{idProvince}")
	@CrossOrigin(origins = "http://localhost:4200")
	List<Region> getRegion(@PathVariable int idProvince) {
		return (List<Region>) regionRepository.findRegionByProvince(idProvince);
	}
	
	/* Add new flyer
	========================================================================*/
	@RequestMapping(method = RequestMethod.POST, value = "/addflyer")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean createFlyer(@RequestBody Flyer flyer) throws IOException, ParseException {
		
		flyerRepository.save(flyer);
		return true;

	}
	
	/* Get flyer list
	========================================================================*/
	@GetMapping("/flyers")
	@CrossOrigin(origins = "http://localhost:4200")
	List<Flyer> getFlyer() {
		return (List<Flyer>) flyerRepository.findAllFlyers();
	}
	
	/* Add new store
	========================================================================*/
	@RequestMapping(method = RequestMethod.POST, value = "/addproduct")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean createProduct(@RequestBody Product product) throws IOException {
		
		productRepository.save(product);
		return true;

	}
	
	/* Get product list
	========================================================================*/
	@GetMapping("/products")
	@CrossOrigin(origins = "http://localhost:4200")
	List<Product> getProduct() {
		return (List<Product>) productRepository.findAllProducts();
	}
	
	/* Get product list by name
	========================================================================*/
	@GetMapping("/productbyname/{productName}")
	@CrossOrigin(origins = "http://localhost:4200")
	List<Product> getProductByName(@PathVariable String productName) {
		return (List<Product>) productRepository.findProductByName(productName);
	}

	
	/* Add price 
	========================================================================*/
	@RequestMapping(method = RequestMethod.POST, value = "/addprice")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean createPrice(@RequestBody Price price) {

		priceRepository.save(price);

		return true;
	}
	
	/* Get product list
	========================================================================*/
	@GetMapping("/prices")
	@CrossOrigin(origins = "http://localhost:4200")
	List<Price> getPrice() {
		return (List<Price>) priceRepository.findAllPrices();
	}
	
	/* Get product list by name
	========================================================================*/
	@GetMapping("/login/{email}/{password}")
	@CrossOrigin(origins = "http://localhost:4200")
	List<Account> getLogin(@PathVariable String email, @PathVariable String password) throws NoSuchAlgorithmException {
		
		String cryptPass = cryptPassword.getKey(password);
		System.out.println(email + "/" + password);
		return (List<Account>) accountRepository.findLogin(email, cryptPass);
	}
	
}