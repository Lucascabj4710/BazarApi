package com.proyectoFInal.bazar.service;

import com.proyectoFInal.bazar.dto.ClienteDto;
import org.springframework.http.ResponseEntity;

import com.proyectoFInal.bazar.model.Cliente;

public interface IClienteService {
	
	public ResponseEntity<?> createClient(ClienteDto cliente);
	public ResponseEntity<?> getClients();
	public ResponseEntity<?> getClientId(Long id);
	public ResponseEntity<?> deleteClientId(Long id);
	public ResponseEntity<?> editClientId(Long id, ClienteDto cliente);
}
