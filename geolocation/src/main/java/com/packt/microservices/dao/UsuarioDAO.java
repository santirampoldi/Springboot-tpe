package com.packt.microservices.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packt.microservices.entity.Usuario;
import com.packt.microservices.entity.Trabajo;

@Transactional
@Repository
public class UsuarioDAO implements IUsuarioDAO{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Trabajo> findAllTrabajosEnviados(Integer id){
		Usuario user = this.finbById(id);

		if(user == null) {
			throw new IllegalArgumentException("el autor no existe");
		}
		
		Query query = entityManager.createQuery(
				"SELECT t FROM Trabajo t, Usuario u "
				+ "WHERE u.dni = :id AND t MEMBER OF u.trabajos_investigacion");

		query.setParameter("id", id);
		return (List<Trabajo>) query.getResultList();

	}

	@Override
	public Usuario finbById(Integer usuario_id) {
		return entityManager.find(Usuario.class, usuario_id);
	}
}
