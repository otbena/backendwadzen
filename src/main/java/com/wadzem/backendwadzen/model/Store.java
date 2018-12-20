package com.wadzem.backendwadzen.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the store database table.
 * 
 */
@Entity
@NamedQuery(name="Store.findAll", query="SELECT s.idStore, s.name FROM Store s")
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idStore;

	@Lob
	private byte[] image;

	private String name;

	//bi-directional many-to-one association to Flyer
	@OneToMany(mappedBy="store")
	private List<Flyer> flyers;

	public Store() {
	}

	public int getIdStore() {
		return this.idStore;
	}

	public void setIdStore(int idStore) {
		this.idStore = idStore;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Flyer> getFlyers() {
		return this.flyers;
	}

	public void setFlyers(List<Flyer> flyers) {
		this.flyers = flyers;
	}

	public Flyer addFlyer(Flyer flyer) {
		getFlyers().add(flyer);
		flyer.setStore(this);

		return flyer;
	}

	public Flyer removeFlyer(Flyer flyer) {
		getFlyers().remove(flyer);
		flyer.setStore(null);

		return flyer;
	}

}