package com.minimarket.web.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.minimarket.web.entity.Producto;

@Repository
@Transactional
public class ProductoDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IProductoDAO crud;

	public IProductoDAO crud() {
		return this.crud;

	}
	
	public List<Producto>  listarProductoCategoria(int categoriaId) 
	{
		List<Producto> listado= null;
		String hql ="from Producto where categoria.idCategoria =:categoriaId";
		return (List<Producto>) em.createQuery(hql,Producto.class)
				.setParameter("categoriaId",categoriaId)
				.getResultList();
		
		
	}
	
	
	public List<Producto>  listarProductoPrecio(int precioMin, int precioMax) 
	{
		List<Producto> listado= null;
		String hql ="SELECT p FROM Producto p WHERE p.precioProducto >=:precioMin and p.precioProducto<=:precioMax";
		return (List<Producto>) em.createQuery(hql,Producto.class)
				.setParameter("precioMin",precioMin)
				.setParameter("precioMax",precioMax)
				.getResultList();
		
		
	}

	

}
