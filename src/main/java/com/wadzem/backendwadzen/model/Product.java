package com.wadzem.backendwadzen.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProduct;

	@Column(name="description_en")
	private String descriptionEn;

	@Column(name="description_fr")
	private String descriptionFr;

	@Lob
	private byte[] image;

	@Column(name="name_en")
	private String nameEn;

	@Column(name="name_fr")
	private String nameFr;

	@Column(name="product_format")
	private String productFormat;

	private byte visible;

	//bi-directional many-to-one association to Alert
	@OneToMany(mappedBy="product")
	private List<Alert> alerts;

	//bi-directional many-to-one association to Brand
	@ManyToOne
	@JoinColumn(name="idBrand")
	private Brand brand;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="idCategory")
	private Category category;

	//bi-directional many-to-one association to Shoppinglist
	@OneToMany(mappedBy="product")
	private List<Shoppinglist> shoppinglists;

	public Product() {
	}

	public int getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getDescriptionEn() {
		return this.descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}

	public String getDescriptionFr() {
		return this.descriptionFr;
	}

	public void setDescriptionFr(String descriptionFr) {
		this.descriptionFr = descriptionFr;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getNameEn() {
		return this.nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameFr() {
		return this.nameFr;
	}

	public void setNameFr(String nameFr) {
		this.nameFr = nameFr;
	}

	public String getProductFormat() {
		return this.productFormat;
	}

	public void setProductFormat(String productFormat) {
		this.productFormat = productFormat;
	}

	public byte getVisible() {
		return this.visible;
	}

	public void setVisible(byte visible) {
		this.visible = visible;
	}

	public List<Alert> getAlerts() {
		return this.alerts;
	}

	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}

	public Alert addAlert(Alert alert) {
		getAlerts().add(alert);
		alert.setProduct(this);

		return alert;
	}

	public Alert removeAlert(Alert alert) {
		getAlerts().remove(alert);
		alert.setProduct(null);

		return alert;
	}

	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Shoppinglist> getShoppinglists() {
		return this.shoppinglists;
	}

	public void setShoppinglists(List<Shoppinglist> shoppinglists) {
		this.shoppinglists = shoppinglists;
	}

	public Shoppinglist addShoppinglist(Shoppinglist shoppinglist) {
		getShoppinglists().add(shoppinglist);
		shoppinglist.setProduct(this);

		return shoppinglist;
	}

	public Shoppinglist removeShoppinglist(Shoppinglist shoppinglist) {
		getShoppinglists().remove(shoppinglist);
		shoppinglist.setProduct(null);

		return shoppinglist;
	}

}