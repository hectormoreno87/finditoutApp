package ngesu.and.findit.models;

import java.io.Serializable;
import java.util.List;

public class Empresa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idEmpresa;
	private String vUserName;
	private String vEmpresa;
	private String vDescripcion;
	private String vCarpeta;
	private String vWeb;
	private String vMail;
	private Sucursal sucursal;
	private List<Categoria> categorias;
	
	
	
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getvUserName() {
		return vUserName;
	}
	public void setvUserName(String vUserName) {
		this.vUserName = vUserName;
	}
	public String getvEmpresa() {
		return vEmpresa;
	}
	public void setvEmpresa(String vEmpresa) {
		this.vEmpresa = vEmpresa;
	}
	public String getvDescripcion() {
		return vDescripcion;
	}
	public void setvDescripcion(String vDescripcion) {
		this.vDescripcion = vDescripcion;
	}
	public String getvCarpeta() {
		return vCarpeta;
	}
	public void setvCarpeta(String vCarpeta) {
		this.vCarpeta = vCarpeta;
	}
	public String getvWeb() {
		return vWeb;
	}
	public void setvWeb(String vWeb) {
		this.vWeb = vWeb;
	}
	public String getvMail() {
		return vMail;
	}
	public void setvMail(String vMail) {
		this.vMail = vMail;
	}

}
