package ngesu.and.findit.models;

import java.io.Serializable;

public class Telefono implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7873810081300753923L;
	Integer idSucursal;
	String vTelefono;
	Boolean bWhatsApp;
	
	public Integer getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}
	public String getvTelefono() {
		return vTelefono;
	}
	public void setvTelefono(String vTelefono) {
		this.vTelefono = vTelefono;
	}
	public Boolean getbWhatsApp() {
		return bWhatsApp;
	}
	public void setbWhatsApp(Boolean bWhatsApp) {
		this.bWhatsApp = bWhatsApp;
	}
	

}
