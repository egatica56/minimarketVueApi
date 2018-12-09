package com.minimarket.web.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class CategoriaDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private ICategoriaDAO crud;

	public ICategoriaDAO crud() {
		return this.crud;
	}

}
