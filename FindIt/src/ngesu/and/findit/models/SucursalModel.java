package ngesu.and.findit.models;

import java.util.List;



public class SucursalModel  {
	
	Integer idSucursal;
	Integer idEmpresa;
	String vNombre;
	String vDirecc;
	String vLongitud;
	String vLatitud;
	String placeName;
	Integer idUser;
	String vUserName;
	String vEmpresa;
	String vDescripcion;
	String vCarpeta;
	String vWeb;
	String vMail;
	List<ImagenSucursal> imagenes;
	List<RedSocial> redesSociales;
	List<Telefono> telefonos;
	
	
	public Integer getIdSucursal() {
		return idSucursal;
	}
	public List<RedSocial> getRedesSociales() {
		return redesSociales;
	}
	public void setRedesSociales(List<RedSocial> redesSociales) {
		this.redesSociales = redesSociales;
	}
	public List<Telefono> getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(List<Telefono> telefonos) {
		this.telefonos = telefonos;
	}
	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}
	public List<ImagenSucursal> getImagenes() {
		return imagenes;
	}
	public void setImagenes(List<ImagenSucursal> imagenes) {
		this.imagenes = imagenes;
	}
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getvNombre() {
		return vNombre;
	}
	public void setvNombre(String vNombre) {
		this.vNombre = vNombre;
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
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
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
