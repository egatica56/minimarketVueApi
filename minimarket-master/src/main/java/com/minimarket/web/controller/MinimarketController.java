package com.minimarket.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.minimarket.web.DAO.IProductoDAO;
import com.minimarket.web.DAO.MarcaDAO;
import com.minimarket.web.DAO.ProductoDAO;
import com.minimarket.web.DAO.UsuarioDAO;
import com.minimarket.web.DAO.CategoriaDAO;
import com.minimarket.web.DAO.ICategoriaDAO;
import com.minimarket.web.DAO.IMarcaDAO;
import com.minimarket.web.entity.Marca;
import com.minimarket.web.entity.Producto;
import com.minimarket.web.entity.Usuario;
import com.minimarket.web.entity.Categoria;;

@Controller
//@RequestMapping("/minimarket")
public class MinimarketController {

	@Autowired
	private MarcaDAO mDAO;

	@Autowired
	private ProductoDAO pDAO;

	@Autowired
	private CategoriaDAO cDAO;

	@Autowired
	private UsuarioDAO uDAO;
	
	@GetMapping("/listar")
	public String listar(Model model) {

		model.addAttribute("productos", pDAO.crud().findAll());

		return "listar_productos.html";
	}

	@GetMapping("/crear")
	public String crear(Model model) {

		model.addAttribute("categorias", cDAO.crud().findAll());
		model.addAttribute("marcas", mDAO.crud().findAll());

		return "agregar.html";
	}

	@PostMapping("/almacenar")
	public String almacenar(Model model, RedirectAttributes ra, 
			@RequestParam("txtSKU") int sku,
			@RequestParam("txtNombre") String nombre,
			@RequestParam("txtPrecio") int precio,
			@RequestParam("cboMarca") int marcaId,
			@RequestParam("cboCategoria") int categoriaId,
			@RequestParam("txtDescripcion") String descripcion
			) {

		Categoria categoria = new Categoria();
		categoria.setIdCategoria(categoriaId);
		
		Marca marca = new Marca();
		marca.setIdMarca(marcaId);

		Producto producto = new Producto();
		producto.setCategoria(categoria);
		producto.setMarca(marca);
		producto.setSku(sku);
		producto.setNombreProduto(nombre);
		producto.setPrecioProducto(precio);
		producto.setDescripcionProduto(descripcion);

		// guardamos el animal y comprobamos que se haya
		// insertado correctamente
		Producto produtoAgregado = pDAO.crud().save(producto);
		String mensaje = "Error al agregar el producto";
		if (produtoAgregado != null) {
			mensaje = "Producto Guardado correctamente";
		}

		ra.addFlashAttribute("mensaje", mensaje);

		return "redirect:crear";
	}

	@GetMapping("/eliminar")
	public String eliminar(Model model, RedirectAttributes ra, @RequestParam("id") int id) {

		String mensaje = "";

		try {
			// eliminamos al animal
			pDAO.crud().deleteById(id);
			mensaje = "Eliminado correctamente";
		} catch (Exception ex) {
			mensaje = "No se ha podido eliminar";
		}

		ra.addFlashAttribute("mensaje", mensaje);

		return "redirect:listar";
	}

	@GetMapping("/modificar")
	public String modificar(Model model,
			RedirectAttributes ra,
			@RequestParam("id") int id) {
		
		//buscamos al animal
		Producto p = null;
		
		try {
			
			p = pDAO.crud().findById(id).get();
			
		} catch (Exception e) {
			
			//si el animal no existe en la BBDD
			//lo redirigimos de vuelta con un mensaje de error
			ra.addFlashAttribute("mensaje", "El Producto no existe");
			return "redirect:listar";
		}
		
		//si encuentra el animal lo enviamos a la vista
		model.addAttribute("p", p);
		
		//mandamos las razas y familias para llenar los combobox
		model.addAttribute("categorias", cDAO.crud().findAll());
		model.addAttribute("marcas", mDAO.crud().findAll());
		
		return "modificar.html";
	}

	@PostMapping("/actualizar")
	public String actualizar(Model model,
			RedirectAttributes ra,
			@RequestParam("txtSKU") int sku,
			@RequestParam("txtNombre") String nombre,
			@RequestParam("txtPrecio") int precio,
			@RequestParam("txtStock") int stock,
			@RequestParam("cboMarca") int marcaId,
			@RequestParam("cboCategoria") int categoriaId,
			@RequestParam("txtDescripcion") String descripcion
			)
	{
			
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(categoriaId);
		
		Marca marca = new Marca();
		marca.setIdMarca(marcaId);

		Producto producto = new Producto();
		producto.setCategoria(categoria);
		producto.setMarca(marca);
		producto.setSku(sku);
		producto.setStockProducto(stock);
		producto.setNombreProduto(nombre);
		producto.setPrecioProducto(precio);
		producto.setDescripcionProduto(descripcion);
		
		
		//guardamos el animal y comprobamos que se haya
		//insertado correctamente
		Producto productoAgregado = pDAO.crud().save(producto);
		String mensaje = "Error al modificar";
		if(productoAgregado!= null) {
			mensaje = "Modificado correctamente";
		}
		
		ra.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:listar";
	}
	
	
	//categorias
	@GetMapping("/crearCategoria")
	public String crearCategoria(Model model) {

		return "agregarCategoria.html";
	}

	
	
	@GetMapping("/listarCategoria")
	public String listarCategoria(Model model) {

		model.addAttribute("categorias", cDAO.crud().findAll());

		return "listar_categorias.html";
	}
	

	
	

	
	@PostMapping("/almacenarCategoria")
	public String almacenarCategoria(Model model, RedirectAttributes ra, 
			@RequestParam("txtNombreCat") String nombreCat,
			@RequestParam("txtDescCategoria") String descripcionCat
			
			) {

		Categoria categoria = new Categoria();
		categoria.setNombreCategoria(nombreCat);
		categoria.setDescripcionCategoria(descripcionCat);
		
		
		// guardamos el animal y comprobamos que se haya
		// insertado correctamente
		Categoria categoriaAgregado = cDAO.crud().save(categoria);
		String mensaje = "Error al agregar el producto";
		if (categoriaAgregado != null) {
			mensaje = "Categoria creada correctamente";
		}

		ra.addFlashAttribute("mensaje", mensaje);

		return "redirect:crearCategoria";
	}
	
	
	
	@GetMapping("/eliminarCategoria")
	public String eliminarCategoria(Model model, RedirectAttributes ra, @RequestParam("id") int id) {

		String mensaje = "";

		try {
			// eliminamos al animal
			cDAO.crud().deleteById(id);
			mensaje = "Categoria Eliminada correctamente";
		} catch (Exception ex) {
			mensaje = "No se ha podido eliminar la categoria";
		}

		ra.addFlashAttribute("mensaje", mensaje);

		return "redirect:listarCategoria";
	}

	
	

	@GetMapping("/modificarCategoria")
	public String modificarCategoria(Model model,
			RedirectAttributes ra,
			@RequestParam("id") int id) {
		
		//buscamos al animal
		Categoria c = null;
		
		try {
			
			c = cDAO.crud().findById(id).get();
			
		} catch (Exception e) {
			
			//si el animal no existe en la BBDD
			//lo redirigimos de vuelta con un mensaje de error
			ra.addFlashAttribute("mensaje", "Error la categoria no existe");
			return "redirect:listarCategoria";
		}
		
		//si encuentra el animal lo enviamos a la vista
		model.addAttribute("c", c);
		
		
		
		return "modificarCategorias.html";
	}

	@PostMapping("/actualizarCategoria")
	public String actualizarCategoria(Model model,
			RedirectAttributes ra,
			@RequestParam("txtIdCategoria") int idCategoria,
			@RequestParam("txtNombreCat") String nombreCategoria,
			@RequestParam("txtDescripcionCat") String descripcionCategoria
			)
	{
			
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(idCategoria);
		categoria.setDescripcionCategoria(descripcionCategoria);
		categoria.setNombreCategoria(nombreCategoria);
				
		
		//guardamos el animal y comprobamos que se haya
		//insertado correctamente
		Categoria categoriaAgregada = cDAO.crud().save(categoria);
		String mensaje = "Error al modificar Categoria";
		if(categoriaAgregada!= null) {
			mensaje = "Categoria Modificada correctamente";
		}
		
		ra.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:listarCategoria";
	}
	
//////////////////////////MARCAS//////////////////////////////////////////////////////////
	@GetMapping("/crearMarca")
	public String crearMarca(Model model) {

		return "agregarMarca.html";
	}

	
	
	@GetMapping("/listarMarca")
	public String listarMarca(Model model) {

		model.addAttribute("marcas", mDAO.crud().findAll());

		return "listar_marcas.html";
	}
	

	
	

	
	@PostMapping("/almacenarMarca")
	public String almacenarMarca(Model model, RedirectAttributes ra, 
			@RequestParam("txtNombreMar") String nombreMar,
			@RequestParam("txtDescMarca") String descripcionMar
			
			) {

		Marca marca = new Marca();
		marca.setNombreMarca(nombreMar);
		marca.setDescripcionMarca(descripcionMar);
		
		
		// guardamos el animal y comprobamos que se haya
		// insertado correctamente
		Marca marcaAgregado = mDAO.crud().save(marca);
		String mensaje = "Error al agregar la Marca";
		if (marcaAgregado != null) {
			mensaje = "Marca creada correctamente";
		}

		ra.addFlashAttribute("mensaje", mensaje);

		return "redirect:crearMarca";
	}
	
	
	
	@GetMapping("/eliminarMarca")
	public String eliminarMarca(Model model, RedirectAttributes ra, @RequestParam("id") int id) {

		String mensaje = "";

		try {
			// eliminamos al animal
			mDAO.crud().deleteById(id);
			mensaje = "Marca Eliminada correctamente";
		} catch (Exception ex) {
			mensaje = "No se ha podido eliminar la Marca";
		}

		ra.addFlashAttribute("mensaje", mensaje);

		return "redirect:listarMarca";
	}

	
	

	@GetMapping("/modificarMarca")
	public String modificarMarca(Model model,
			RedirectAttributes ra,
			@RequestParam("id") int id) {
		
		//buscamos al animal
		Marca m = null;
		
		try {
			
			m= mDAO.crud().findById(id).get();
			
		} catch (Exception e) {
			
			//si el animal no existe en la BBDD
			//lo redirigimos de vuelta con un mensaje de error
			ra.addFlashAttribute("mensaje", "Error la Marca no existe");
			return "redirect:listarMarca";
		}
		
		//si encuentra el animal lo enviamos a la vista
		model.addAttribute("m", m);
		
		
		
		return "modificarMarcas.html";
	}

	@PostMapping("/actualizarMarca")
	public String actualizarMarca(Model model,
			RedirectAttributes ra,
			@RequestParam("txtIdMarca") int idMarca,
			@RequestParam("txtNombreMar") String nombreMarca,
			@RequestParam("txtDescripcionMar") String descripcionMarca
			)
	{
			
		Marca marca=new Marca();
		marca.setIdMarca(idMarca);
		marca.setNombreMarca(nombreMarca);
		marca.setDescripcionMarca(descripcionMarca);
				
		
		//guardamos el animal y comprobamos que se haya
		//insertado correctamente
		Marca marcaAgregada = mDAO.crud().save(marca);
		String mensaje = "Error al modificar Marca";
		if(marcaAgregada!= null) {
			mensaje = "Marca Modificada correctamente";
		}
		
		ra.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:listarMarca";
	}
	
	

	@GetMapping("/listarProductoCategoria")
	public String listarCategorias(Model model) {
		
		model.addAttribute("categorias", cDAO.crud().findAll());
		return "listar_productoCategoria.html";
	}

	
	

	@PostMapping("/listarProductoCategoria")
	public String listarProductoCategoria(Model model,
			RedirectAttributes ra,		
			@RequestParam("cboCategoria") int categoriaId) {
		model.addAttribute("categorias", cDAO.crud().findAll());
		model.addAttribute("productos", pDAO.listarProductoCategoria(categoriaId));

		return "listar_productoCategoria.html";
	}


	
	@GetMapping("/listarProductoPrecio")
	public String listarProductoPrecio(Model model) {
				
		return "listar_productoPrecio.html";
	}

	
	

	@PostMapping("/listarProductoPrecio")
	public String listarProductoPrecio(Model model,
			RedirectAttributes ra,		
			@RequestParam("txtMinimo") int minimo,
			@RequestParam("txtMaximo") int maximo) {
		model.addAttribute("categorias", cDAO.crud().findAll());
		model.addAttribute("productos", pDAO.listarProductoPrecio(minimo, maximo));

		return "listar_productoPrecio.html";
	}
	
	
/////////////////////////////////////////////////////////USUARIO//////////
	
	@GetMapping("/crearUsuario")
	public String crearUsuario(Model model) {

		return "agregarUsuario.html";
	}

	
	
	@GetMapping("/listarUsuario")
	public String listarUsuario(Model model) {

		model.addAttribute("usuarios", uDAO.crud().findAll());

		return "listar_usuarios.html";
	}
	

	
	

	
	@PostMapping("/almacenarUsuario")
	public String almacenarUsuario(Model model, RedirectAttributes ra, 
			@RequestParam("txtUsername") String username,
			@RequestParam("txtPassword") String pass,
			@RequestParam("txtEmail") String email,
			@RequestParam("txtNombre") String nombres,
			@RequestParam("txtApellido") String apellidos,
			@RequestParam("txtDireccion") String direccion
			
			) {

		Usuario u =new Usuario();
		u.setUsername(username);
		u.setPassword(pass);
		u.setEmail(email);
		u.setNombres(nombres);
		u.setApellidos(apellidos);
		u.setDireccion(direccion);
		
		
		// guardamos el animal y comprobamos que se haya
		// insertado correctamente
		Usuario usuarioAgregado = uDAO.crud().save(u);
		String mensaje = "Error al agregar el usuario";
		if (usuarioAgregado != null) {
			mensaje = "Usuario agregado correctamente";
		}

		ra.addFlashAttribute("mensaje", mensaje);

		return "redirect:crearUsuario";
	}
	
	
	
	@GetMapping("/eliminarUsuario")
	public String eliminarUsuario(Model model, RedirectAttributes ra, @RequestParam("id") int id) {

		String mensaje = "";

		try {
			// eliminamos al animal
			uDAO.crud().deleteById(id);
			mensaje = "Usuario Eliminado correctamente";
		} catch (Exception ex) {
			mensaje = "No se ha podido eliminar el Usuario";
		}

		ra.addFlashAttribute("mensaje", mensaje);

		return "redirect:listarUsuario";
	}

	
	

	@GetMapping("/modificarUsuario")
	public String modificarUsuario(Model model,
			RedirectAttributes ra,
			@RequestParam("id") int id) {
		
		//buscamos al animal
		Usuario u = null;
		
		try {
			
			u= uDAO.crud().findById(id).get();
			
		} catch (Exception e) {
			
			//si el animal no existe en la BBDD
			//lo redirigimos de vuelta con un mensaje de error
			ra.addFlashAttribute("mensaje", "Error la Marca no existe");
			return "redirect:listarUsuario";
		}
		
		//si encuentra el animal lo enviamos a la vista
		model.addAttribute("u", u);
		
		
		
		return "modificarUsuario.html";
	}

	@PostMapping("/actualizarUsuario")
	public String actualizarUsuario(Model model,
			RedirectAttributes ra,
			@RequestParam("txtIdUsuario") int idUsuario,
			@RequestParam("txtUsername") String username,
			@RequestParam("txtPassword") String pass,
			@RequestParam("txtEmail") String email,
			@RequestParam("txtNombre") String nombres,
			@RequestParam("txtApellido") String apellidos,
			@RequestParam("txtDireccion") String direccion
			)
	{
			
		Usuario u =new Usuario();
		u.setIdUsuario(idUsuario);
		u.setUsername(username);
		u.setPassword(pass);
		u.setEmail(email);
		u.setNombres(nombres);
		u.setApellidos(apellidos);
		u.setDireccion(direccion);
				
		
		//guardamos el animal y comprobamos que se haya
		//insertado correctamente
		Usuario usuarioAgregado = uDAO.crud().save(u);
		String mensaje = "Error al modificar el usuario";
		if(usuarioAgregado!= null) {
			mensaje = "Usuario Modificado correctamente";
		}
		
		ra.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:listarUsuario";
	}

	
	
	
	
	
	
	
}
