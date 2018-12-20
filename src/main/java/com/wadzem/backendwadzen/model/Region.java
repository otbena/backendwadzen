package com.wadzem.backendwadzen.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the region database table.
 * 
 */
@Entity
@NamedQuery(name="Region.findAll", query="SELECT r FROM Region r")
@NamedQuery(name="Region.findRegionByProvince", query="SELECT r.idRegion, r.region FROM Region r where r.province.idProvince=?1")
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRegion;

	private String region;

	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="region")
	private List<Account> accounts;

	//bi-directional many-to-one association to Flyer
	@OneToMany(mappedBy="region")
	private List<Flyer> flyers;

	//bi-directional many-to-one association to Province
	@ManyToOne
	@JoinColumn(name="idProvince")
	private Province province;

	public Region() {
	}

	public int getIdRegion() {
		return this.idRegion;
	}

	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Account addAccount(Account account) {
		getAccounts().add(account);
		account.setRegion(this);

		return account;
	}

	public Account removeAccount(Account account) {
		getAccounts().remove(account);
		account.setRegion(null);

		return account;
	}

	public List<Flyer> getFlyers() {
		return this.flyers;
	}

	public void setFlyers(List<Flyer> flyers) {
		this.flyers = flyers;
	}

	public Flyer addFlyer(Flyer flyer) {
		getFlyers().add(flyer);
		flyer.setRegion(this);

		return flyer;
	}

	public Flyer removeFlyer(Flyer flyer) {
		getFlyers().remove(flyer);
		flyer.setRegion(null);

		return flyer;
	}

	public Province getProvince() {
		return this.province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

}