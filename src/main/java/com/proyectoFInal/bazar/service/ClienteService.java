package com.proyectoFInal.bazar.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.proyectoFInal.bazar.model.Cliente;
import com.proyectoFInal.bazar.repository.ClienteRepository;

public class ClienteService implements IClienteService {

	private static final Logger log = LoggerFactory.getLogger(ClienteService.class);
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public ResponseEntity<?> createClient(Cliente cliente) {
		log.info("Inicio del metodo crear cliente");
		try {
			clienteRepository.save(cliente);
			return new ResponseEntity<String>("Cliente creado con exito", HttpStatus.CREATED);
		} catch (DataAccessException e) {
			return new ResponseEntity<String>("Error al crear cliente", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@Override
	public ResponseEntity<?> getClients() {
		log.info("Inicio del metodo traer clientes");
		try {			
			List<Cliente> list = clienteRepository.findAll();
		} catch (Exception e) {
			log.error("Error al recuperar los clientes");
			return new ResponseEntity<String>("Cliente creado con exito", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return null;
	}

	@Override
	public ResponseEntity<?> getClientId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> deleteClientId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> editClientId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
