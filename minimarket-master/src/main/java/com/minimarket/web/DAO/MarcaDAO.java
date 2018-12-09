package com.minimarket.web.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class MarcaDAO {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IMarcaDAO crud;

	public IMarcaDAO crud() {
		return this.crud;

	}
}
