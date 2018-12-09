package com.minimarket.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minimarket.web.DAO.CategoriaDAO;
import com.minimarket.web.DAO.MarcaDAO;
import com.minimarket.web.DAO.ProductoDAO;
import com.minimarket.web.DAO.UsuarioDAO;
import com.minimarket.web.entity.Categoria;
import com.minimarket.web.entity.Marca;
import com.minimarket.web.entity.Producto;
import com.minimarket.web.entity.Usuario;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiMinimarketController {

	@Autowired
	ProductoDAO pDAO;

	@Autowired
	MarcaDAO mDAO;

	@Autowired
	CategoriaDAO cDAO;
	
	@Autowired
	UsuarioDAO uDAO;
	
	
	
	@GetMapping("/categoria")
	public Iterable<Categoria> listarCategoria() {

		return cDAO.crud().findAll();
	}
	
	@GetMapping("/producto")
	public Iterable<Producto> listarProducto() {

		return pDAO.crud().findAll();
	}
	
	
	@PostMapping("/producto")
	public ResponseEntity<Producto> guardarProducto(
			@RequestBody Producto producto) {
		
		try {
			
			Producto productoCreado
					= pDAO.crud().save(producto);
			
			return new ResponseEntity<Producto>(productoCreado, HttpStatus.ACCEPTED);
		} catch(Exception ex) {
			
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@DeleteMapping("/producto/{id}")
	public ResponseEntity<Map<String, String>> eliminarProducto(@PathVariable("id") int id) {
		
		try {
			pDAO.crud().deleteById(id);
			HashMap<String, String> mensaje = new HashMap<>();
			mensaje.put("mensaje", "Producto Eliminado correctamente");
			return new ResponseEntity<Map<String,String>>(mensaje, HttpStatus.OK);
			
		} catch(Exception ex) {
			
			return new ResponseEntity(HttpStatus.NOT_FOUND);
			
		}
		

	}
	
	
	
	@PutMapping("/producto/{id}")
	public ResponseEntity<Producto> modificarProducto(
			@RequestBody Producto producto,
			@PathVariable("id") int id) {
		
		Producto productoBuscado = null;
		
		try {
			productoBuscado = pDAO.crud().findById(id).get();
			//si lo encuentra le pasamos los nuevos datos
			Categoria cat= new Categoria();
			cat.setIdCategoria(productoBuscado.getCategoria().getIdCategoria());
			Marca mar = new Marca();
			mar.setIdMarca(productoBuscado.getMarca().getIdMarca());
			productoBuscado.setNombreProduto(producto.getNombreProduto());
			productoBuscado.setCategoria(cat);
			productoBuscado.setMarca(mar);
			productoBuscado.setPrecioProducto(producto.getPrecioProducto());
			productoBuscado.setStockProducto(producto.getStockProducto());
			productoBuscado.setDescripcionProduto(producto.getDescripcionProduto());
			
			
		} catch(Exception ex) {
			
			return new ResponseEntity(HttpStatus.NOT_FOUND);
			
		}
		
		try {
			
			Producto productoModificado
					= pDAO.crud().save(productoBuscado);
			return new ResponseEntity<Producto>(productoModificado,HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
			
		}
		
		
	}


	/// API MARCA//////
	@GetMapping("/marca")
	public Iterable<Marca> listarMarca() {

		return mDAO.crud().findAll();
	}
	
	
	@PostMapping("/marca")
	public ResponseEntity<Marca> guardar(
			@RequestBody Marca marca) {
		
		try {
			
			Marca marcaCreada
					= mDAO.crud().save(marca);
			
			return new ResponseEntity<Marca>(marcaCreada, HttpStatus.ACCEPTED);
		} catch(Exception ex) {
			
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping("/marca/{id}")
	public ResponseEntity<Map<String, String>> eliminarMarca(@PathVariable("id") int id) {
		
		try {
			mDAO.crud().deleteById(id);
			HashMap<String, String> mensaje = new HashMap<>();
			mensaje.put("mensaje", "Eliminado correctamente");
			return new ResponseEntity<Map<String,String>>(mensaje, HttpStatus.OK);
			
		} catch(Exception ex) {
			
			return new ResponseEntity(HttpStatus.NOT_FOUND);
			
		}
		

	}
		
	@PutMapping("/marca/{id}")
	public ResponseEntity<Marca> modificar(
			@RequestBody Marca marca,
			@PathVariable("id") int id) {
		
		Marca marcaBuscada = null;
		
		try {
			marcaBuscada = mDAO.crud().findById(id).get();
			//si lo encuentra le pasamos los nuevos datos
			marcaBuscada.setNombreMarca(marca.getNombreMarca());
			marcaBuscada.setDescripcionMarca(marca.getDescripcionMarca());
			
			
		} catch(Exception ex) {
			
			return new ResponseEntity(HttpStatus.NOT_FOUND);
			
		}
		
		try {
			
			Marca marcaModificada
					= mDAO.crud().save(marcaBuscada);
			return new ResponseEntity<Marca>(marcaModificada,
					HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
			
		}
		
		
	}
	
	
	////API USUARIO/////
	
	@GetMapping("/usuario")
	public Iterable<Usuario> listarUsuario() {

		return uDAO.crud().findAll();
	}
		
	@PostMapping("/usuario")
	public ResponseEntity<Usuario> guardarUsuario(
			@RequestBody Usuario usuario) {
		
		try {
			
			Usuario usuarioCreado
					= uDAO.crud().save(usuario);
			
			return new ResponseEntity<Usuario>(usuarioCreado, HttpStatus.ACCEPTED);
		} catch(Exception ex) {
			
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<Map<String, String>> eliminarUsuario(@PathVariable("id") int id) {
		
		try {
			uDAO.crud().deleteById(id);
			HashMap<String, String> mensaje = new HashMap<>();
			mensaje.put("mensaje", "Usuario Eliminado correctamente");
			return new ResponseEntity<Map<String,String>>(mensaje, HttpStatus.OK);
			
		} catch(Exception ex) {
			
			return new ResponseEntity(HttpStatus.NOT_FOUND);
			
		}
		

	}
		
	@PutMapping("/usuario/{id}")
	public ResponseEntity<Usuario> modificarUsuario(
			@RequestBody Usuario usuario,
			@PathVariable("id") int id) {
		
		Usuario usuarioBuscado = null;
		
		try {
			usuarioBuscado = uDAO.crud().findById(id).get();
			//si lo encuentra le pasamos los nuevos datos
			usuarioBuscado.setNombres(usuario.getNombres());
			usuarioBuscado.setApellidos(usuario.getApellidos());
			usuarioBuscado.setDireccion(usuario.getDireccion());
			usuarioBuscado.setEmail(usuario.getEmail());
			usuarioBuscado.setUsername(usuario.getUsername());
			usuarioBuscado.setPassword(usuario.getPassword());
			
			
		} catch(Exception ex) {
			
			return new ResponseEntity(HttpStatus.NOT_FOUND);
			
		}
		
		try {
			
			Usuario usuarioModificado
					= uDAO.crud().save(usuarioBuscado);
			return new ResponseEntity<Usuario>(usuarioModificado,
					HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
			
		}
		
		
	}

}
