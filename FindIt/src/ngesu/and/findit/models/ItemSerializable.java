package ngesu.and.findit.models;

import java.io.Serializable;

import android.graphics.Bitmap;

public class ItemSerializable implements Serializable {
	
	Integer idItem;
	Integer idCategory;
	String name;
	Integer hasCost;
	Double cost;
	Integer active;
	String image;
	String finditoutName;
	Double distance;
	String latitude;
	String longitude;
	String logo;
	String allImages;
	
	String description;
	Integer idSucursal;
	
	
	public Integer getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}
	
	public Integer getIdItem() {
		return idItem;
	}
	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}
	public String getAllImages() {
		return allImages;
	}
	public void setAllImages(String allImages) {
		this.allImages = allImages;
	}
	public Integer getIdcategory() {
		return idCategory;
	}
	public void setIdcategory(Integer idCategory) {
		this.idCategory = idCategory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getHasCost() {
		return hasCost;
	}
	public void setHasCost(Integer hasCost) {
		this.hasCost = hasCost;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getFinditoutName() {
		return finditoutName;
	}
	public void setFinditoutName(String finditoutName) {
		this.finditoutName = finditoutName;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	

	

}
