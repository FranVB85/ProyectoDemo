package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Articulo;

public interface CarritoCompraServiceI {
	
	public void limpiarCesta();
	
	public void addArticulo(Articulo articulo);
	
	public Integer getNumArticulos();
	
	public List<Articulo> getArticulos();
	
	public Double totalPrice();
	
	public Double calculadorDescuento(Double precio, Double porcentaje);
	
	public Double aplicarDescuento(Integer idArticulo, Double porcentaje);
	

	Integer insertar(Articulo articulo);

}
