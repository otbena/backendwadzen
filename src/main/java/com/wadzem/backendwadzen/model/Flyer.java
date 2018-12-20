package com.wadzem.backendwadzen.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the flyer database table.
 * 
 */
@Entity
@NamedQuery(name="Flyer.findAll", query="SELECT f FROM Flyer f")
public class Flyer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idFlyer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Lob
	private byte[] flyerFile;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	private byte visible;

	//bi-directional many-to-one association to Region
	@ManyToOne
	@JoinColumn(name="idRegion")
	private Region region;

	//bi-directional many-to-one association to Store
	@ManyToOne
	@JoinColumn(name="idStore")
	private Store store;

	public Flyer() {
	}

	public int getIdFlyer() {
		return this.idFlyer;
	}

	public void setIdFlyer(int idFlyer) {
		this.idFlyer = idFlyer;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public byte[] getFlyerFile() {
		return this.flyerFile;
	}

	public void setFlyerFile(byte[] flyerFile) {
		this.flyerFile = flyerFile;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}