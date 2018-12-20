package com.wadzem.backendwadzen.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the province database table.
 * 
 */
@Entity
@NamedQuery(name="Province.findAll", query="SELECT p FROM Province p")
@NamedQuery(name="Province.findAllProvince", query="SELECT p.idProvince, p.province FROM Province p")
public class Province implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProvince;

	private String province;

	//bi-directional many-to-one association to Region
	@OneToMany(mappedBy="province")
	private List<Region> regions;

	public Province() {
	}

	public int getIdProvince() {
		return this.idProvince;
	}

	public void setIdProvince(int idProvince) {
		this.idProvince = idProvince;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public List<Region> getRegions() {
		return this.regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public Region addRegion(Region region) {
		getRegions().add(region);
		region.setProvince(this);

		return region;
	}

	public Region removeRegion(Region region) {
		getRegions().remove(region);
		region.setProvince(null);

		return region;
	}

}