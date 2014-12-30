package ngesu.and.findit.models;

import java.io.Serializable;

public class Sucursal implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idSucursal;
	private Integer idEmpresa;
	private String vNombreSuc;
	private String vDirecc;
	private String vLongitud;
	private String vLatitud;
	private String placeName;
	
	
	public Integer getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getvNombreSuc() {
		return vNombreSuc;
	}
	public void setvNombreSuc(String vNombreSuc) {
		this.vNombreSuc = vNombreSuc;
	}
	public String getvDirecc() {
		return vDirecc;
	}
	public void setvDirecc(String vDirecc) {
		this.vDirecc = vDirecc;
	}
	public String getvLongitud() {
		return vLongitud;
	}
	public void setvLongitud(String vLongitud) {
		this.vLongitud = vLongitud;
	}
	public String getvLatitud() {
		return vLatitud;
	}
	public void setvLatitud(String vLatitud) {
		this.vLatitud = vLatitud;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

}
