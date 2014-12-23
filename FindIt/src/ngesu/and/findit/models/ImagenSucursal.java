package ngesu.and.findit.models;

import java.io.Serializable;

public class ImagenSucursal implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 3684302708542105564L;
Integer idSucursal;
String image;
Integer status;
String url;
public Integer getIdSucursal() {
	return idSucursal;
}
public void setIdSucursal(Integer idSucursal) {
	this.idSucursal = idSucursal;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}


}
