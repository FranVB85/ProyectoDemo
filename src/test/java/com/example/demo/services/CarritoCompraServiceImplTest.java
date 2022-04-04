package com.example.demo.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.model.Articulo;

@RunWith(MockitoJUnitRunner.class)
public class CarritoCompraServiceImplTest {

	@InjectMocks
	private CarritoCompraServiceImpl carritoService = new CarritoCompraServiceImpl();
	
	@Mock
	private BaseDatosI baseDatos;

	@Test
	public void testLimpiarCesta() {
		carritoService.addArticulo(new Articulo("Camiseta", 15.99D));
		assertFalse(carritoService.getArticulos().isEmpty());
		carritoService.limpiarCesta();
		assertTrue(carritoService.getArticulos().isEmpty());
	}

	@Test
	public void testAddArticlulo() {
		assertTrue(carritoService.getArticulos().isEmpty());
		carritoService.addArticulo(new Articulo("Camiseta", 15.99D));
		assertFalse(carritoService.getArticulos().isEmpty());
		
	}

	@Test
	public void testGetNumArticulos() {
		carritoService.addArticulo(new Articulo("Camiseta", 15.99D));
		carritoService.addArticulo(new Articulo("Pantalón", 35.99D));
		carritoService.addArticulo(new Articulo("Gorro", 5.99D));
		Integer res = carritoService.getNumArticulos();
		assertEquals(Integer.valueOf(3), res);

	}

	@Test
	public void testGetArticulos() {
		carritoService.addArticulo(new Articulo("Camiseta", 15.99D));
		carritoService.addArticulo(new Articulo("Pantalón", 35.99D));
		List<Articulo> res = carritoService.getArticulos();
		assertEquals("Camiseta", res.get(0).getNombre());
		assertEquals(2, res.size());
	}

	@Test
	public void testTotalPrice() {
		carritoService.addArticulo(new Articulo("Camiseta", 15.00D));
		carritoService.addArticulo(new Articulo("Pantalón", 35.00D));
		Double res = carritoService.totalPrice();
		assertEquals(Double.valueOf(50D), res);
			
	}

	@Test
	public void testCalculadorDescuento() {
		assertEquals(Double.valueOf(90D), carritoService.calculadorDescuento(100D, 10D));
	}
	
	@Test
	public void testAplicarDescuento() {
		Articulo articulo = new Articulo("Camiseta", 20.00);
		when(baseDatos.buscarArticulo(any(Integer.class))).thenReturn(articulo);
		Double res = carritoService.aplicarDescuento(1, 10D);
		assertEquals(Double.valueOf(18D), res);
		verify(baseDatos, atLeast(1)).buscarArticulo(1);
		
	}

	@Test
	public void testInsertarArticulo() {
		Articulo articulo = new Articulo("Camiseta", 15.00D);
		when(this.baseDatos.insertarArticulo(articulo)).thenReturn(1);
		Integer res = carritoService.insertar(articulo);
		assertEquals(1, res);
		assertEquals(carritoService.getArticulos().get(0).getNombre(), "Camiseta");
		verify(baseDatos, times(1)).insertarArticulo(articulo);
	}
}
