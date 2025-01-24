package com.proyectoFInal.bazar.controller;

import com.proyectoFInal.bazar.dto.ProductoDtoRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoFInal.bazar.model.Producto;
import com.proyectoFInal.bazar.service.ProductoService;

@RestController
public class ProductController {
    
	
    @Autowired
    ProductoService producServ;

    //Traer todos los productos
    @GetMapping("/productos")
    public ResponseEntity<?> traerProductos(){
    	ResponseEntity<?> response = producServ.getProductos();

        return response;
    }

    // Traer producto individualmente
    @GetMapping("/productos/{codigoProducto}")
    public ResponseEntity<?> traerProducto(@PathVariable Long codigoProducto){

    	ResponseEntity<?> response = producServ.getProductoId(codigoProducto);
    	
        return response ;          
    }

    //Crear producto
    @PostMapping("/productos/crear")
    public String altaProducto(@RequestBody @Valid ProductoDtoRequest producto){

        producServ.altaProducto(producto);
        return "Producto creado con exito";
    }

    //Eliminar producto
    @DeleteMapping("/productos/eliminar/{id}")
    public ResponseEntity<?> bajaProducto(@PathVariable Long id){

    	ResponseEntity<?> response = producServ.eliminarProducto(id);
        return response;
    }

    //Editar producto
    @PutMapping("/producto/editar/{codigoProducto}")
    public ResponseEntity<?> editarProducto(@PathVariable Long codigoProducto ,@RequestBody @Valid ProductoDtoRequest producto){

    	ResponseEntity<?> response = producServ.editarProducto(producto, codigoProducto);
        return response;
    }

}

