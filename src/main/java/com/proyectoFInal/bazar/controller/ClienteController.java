package com.proyectoFInal.bazar.controller;

import com.proyectoFInal.bazar.dto.ClienteDto;
import com.proyectoFInal.bazar.repository.ClienteRepository;
import com.proyectoFInal.bazar.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bazar")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping("/clientes/crear")
    public ResponseEntity<?> crearCliente(@RequestBody @Valid ClienteDto clienteDto){

        ResponseEntity response = clienteService.createClient(clienteDto);
        return response;
    }

    @GetMapping("/clientes")
    public ResponseEntity<?> obtenerClientes(){

        ResponseEntity response = clienteService.getClients();
        return response;
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> obtenerClienteId(@PathVariable Long id){
        ResponseEntity response = clienteService.getClientId(id);
        return response;
    }

    @DeleteMapping("/clientes/eliminar/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long id){
        ResponseEntity response = clienteService.deleteClientId(id);
        return response;
    }

    @PutMapping("/cliente/actualizar/{id}")
    public ResponseEntity<?> editarCliente(@PathVariable Long id, @RequestBody @Valid ClienteDto clienteDto){
        ResponseEntity response = clienteService.editClientId(id, clienteDto);
        return response;
    }

}
