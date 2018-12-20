package com.wadzem.backendwadzen.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a order by a.idAccount DESC")

public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAccount;

	private String accountPassword;

	private byte active;

	private int confirmationCode;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	private String email;

	private String firstName;

	private String lastName;

	private String role;

	private byte visible;

	//bi-directional many-to-one association to Region
	@ManyToOne
	@JoinColumn(name="idRegion")
	private Region region;

	//bi-directional many-to-one association to Alert
	@OneToMany(mappedBy="account")
	private List<Alert> alerts;

	//bi-directional many-to-one association to Shoppinglist
	@OneToMany(mappedBy="account")
	private List<Shoppinglist> shoppinglists;

	public Account() {
	}

	public int getIdAccount() {
		return this.idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public String getAccountPassword() {
		return this.accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public int getConfirmationCode() {
		return this.confirmationCode;
	}

	public void setConfirmationCode(int confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public byte getVisible() {
		return this.visible;
	}

	public void setVisible(byte visible) {
		this.visible = visible;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<Alert> getAlerts() {
		return this.alerts;
	}

	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}

	public Alert addAlert(Alert alert) {
		getAlerts().add(alert);
		alert.setAccount(this);

		return alert;
	}

	public Alert removeAlert(Alert alert) {
		getAlerts().remove(alert);
		alert.setAccount(null);

		return alert;
	}

	public List<Shoppinglist> getShoppinglists() {
		return this.shoppinglists;
	}

	public void setShoppinglists(List<Shoppinglist> shoppinglists) {
		this.shoppinglists = shoppinglists;
	}

	public Shoppinglist addShoppinglist(Shoppinglist shoppinglist) {
		getShoppinglists().add(shoppinglist);
		shoppinglist.setAccount(this);

		return shoppinglist;
	}

	public Shoppinglist removeShoppinglist(Shoppinglist shoppinglist) {
		getShoppinglists().remove(shoppinglist);
		shoppinglist.setAccount(null);

		return shoppinglist;
	}

}