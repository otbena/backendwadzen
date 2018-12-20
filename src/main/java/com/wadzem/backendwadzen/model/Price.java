package com.wadzem.backendwadzen.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the price database table.
 * 
 */
@Entity
@NamedQuery(name="Price.findAll", query="SELECT p FROM Price p")
@NamedQuery(name="Price.findAllPrices", query="SELECT p.idPrice, p.product.nameFr, p.product.productFormat, p.price, p.flyer.store.name, p.product.image, p.product.idProduct  FROM Price p")
public class Price implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPrice;

	@ManyToOne
	@JoinColumn(name="idFlyer")
	private Flyer flyer;

	@ManyToOne
	@JoinColumn(name="idProduct")
	private Product product;

	private BigDecimal price;

	public Price() {
	}
	
	public Product getProduct() {
		return this.product;
	}
	
	public Flyer getFlyer() {
		return this.flyer;
	}


	public int getIdPrice() {
		return this.idPrice;
	}

	public void setIdPrice(int idPrice) {
		this.idPrice = idPrice;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}