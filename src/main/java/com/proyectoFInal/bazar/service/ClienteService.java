package com.proyectoFInal.bazar.service;

import java.util.List;
import java.util.Optional;

import com.proyectoFInal.bazar.dto.ClienteDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.proyectoFInal.bazar.model.Cliente;
import com.proyectoFInal.bazar.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClienteService implements IClienteService {

	private static final Logger log = LoggerFactory.getLogger(ClienteService.class);
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public ResponseEntity<?> createClient(ClienteDto cliente) {
		log.info("Inicio del metodo crear cliente" + cliente.getApellido());
		Cliente cliente1 = Cliente.builder()
				.nombre(cliente.getNombre())
				.apellido(cliente.getApellido())
				.dni(cliente.getDni())
				.build();
		try {
			clienteRepository.save(cliente1);
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
			if(list.isEmpty()) {

				return new ResponseEntity<String>("No hay clientes registrados", HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Cliente>>(list, HttpStatus.OK);
		} catch (DataAccessException e) {
			log.error("Error al recuperar los clientes");
			log.error(e.getMessage());
			return new ResponseEntity<String>("Cliente creado con exito", HttpStatus.INTERNAL_SERVER_ERROR);
		}				
	}

	@Override
	public ResponseEntity<?> getClientId(Long id) {
		log.info("Inicio del metodo traer clientes");
		try {			
			Optional<Cliente> clienteBuscado = clienteRepository.findById(id);
			if(clienteBuscado.isPresent()) {
				log.info("Cliente encontrado");
				return new ResponseEntity<Cliente>(clienteBuscado.get(), HttpStatus.OK);
			} else {
				log.error("Cliente no encontrado");
				return new ResponseEntity<String>("Error no existe cliente con ese ID", HttpStatus.NOT_FOUND);
			}
			
		} catch (DataAccessException e) {
			log.error("Error al recuperar el cliente");
			log.error(e.getMessage());
			return new ResponseEntity<String>("No se pudo recuperar el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@Override
	public ResponseEntity<?> deleteClientId(Long id) {
		try {
			Optional<Cliente> clienteBuscado = clienteRepository.findById(id);
			if(clienteBuscado.isPresent()) {
				log.info("Cliente encontrado");
				clienteRepository.deleteById(id);
				return new ResponseEntity<String>("Cliente eliminado con exito", HttpStatus.OK);
			} else {
				log.error("Cliente no encontrado");
				return new ResponseEntity<String>("Error no existe cliente con ese ID", HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			log.error("Error al eliminar el cliente");
			log.error(e.getMessage());
			return new ResponseEntity<String>("No se pudo eliminar el cliente solicitado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> editClientId(Long id, ClienteDto cliente) {
		try {
			Optional<Cliente> clienteBuscado = clienteRepository.findById(id);
			if(clienteBuscado.isPresent()) {
				log.info("Cliente encontrado");
				clienteBuscado.get().setNombre(cliente.getNombre());
				clienteBuscado.get().setDni(cliente.getDni());
				clienteBuscado.get().setApellido(cliente.getApellido());
				
				clienteRepository.save(clienteBuscado.get());
				return new ResponseEntity<String>("Cliente eliminado con exito", HttpStatus.OK);
			} else {
				log.error("Cliente no encontrado");
				return new ResponseEntity<String>("Error no existe cliente con ese ID", HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			log.error("Error al actualizar cliente");
			log.error(e.getMessage());
			return new ResponseEntity<String>("No se pudo actualizar el cliente solicitado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
