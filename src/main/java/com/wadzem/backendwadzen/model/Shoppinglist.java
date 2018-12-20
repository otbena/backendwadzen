package com.wadzem.backendwadzen.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the shoppinglist database table.
 * 
 */
@Entity
@NamedQuery(name="Shoppinglist.findAll", query="SELECT s FROM Shoppinglist s")
public class Shoppinglist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idShoppingList;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	private byte visible;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="idAccount")
	private Account account;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="idProduct")
	private Product product;

	public Shoppinglist() {
	}

	public int getIdShoppingList() {
		return this.idShoppingList;
	}

	public void setIdShoppingList(int idShoppingList) {
		this.idShoppingList = idShoppingList;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public byte getVisible() {
		return this.visible;
	}

	public void setVisible(byte visible) {
		this.visible = visible;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}