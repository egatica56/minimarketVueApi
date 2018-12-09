package com.minimarket.web.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.minimarket.web.DAO.IUsuarioDAO;
import com.minimarket.web.entity.Usuario;

@Transactional
@Repository
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IUsuarioDAO crud;

	public IUsuarioDAO crud() {
		return this.crud;
	}

	// crearemos un metodo que busque un usuario por nombre de usuario
	// ya que spring solo necesita validar el nombre de usuario desde la BBDD,
	// despues el valida
	// la contraseña
	public Usuario buscarPorNombreUsuario(String nombreUsuario) {
		String hql = "select u from Usuario u where u.username =:user";
		return (Usuario) em.createQuery(hql, Usuario.class).setParameter("user", nombreUsuario).getSingleResult();

	}
}
