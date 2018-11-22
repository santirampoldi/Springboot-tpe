package com.packt.microservices.dao;

import java.util.List;

import com.packt.microservices.entity.Trabajo;
import com.packt.microservices.entity.Usuario;

public interface IUsuarioDAO {
	List<Trabajo> findAllTrabajosEnviados(Integer usuario_id);
	
	Usuario finbById(Integer usuario_id);
}
