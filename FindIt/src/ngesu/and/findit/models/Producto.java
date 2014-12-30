package ngesu.and.findit.models;

import java.io.Serializable;
import java.util.List;

public class Producto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idProducto;
	private Integer idCategoria;
	private Boolean activo;
	private String nombre;
	private String descripcion;
	private Double precio;
	private Boolean bloqueado;
	private List<ImagenProducto> imagenes;
	
	
	public List<ImagenProducto> getImagenesProducto() {
		return imagenes;
	}
	public void setImagenesProducto(List<ImagenProducto> imagenesProducto) {
		this.imagenes = imagenes;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Boolean getBloqueado() {
		return bloqueado;
	}
	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

}
