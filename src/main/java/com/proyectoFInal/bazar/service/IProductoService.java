package com.proyectoFInal.bazar.service;

import com.proyectoFInal.bazar.dto.ProductoDtoRequest;

import org.springframework.http.ResponseEntity;

public interface IProductoService {

    public ResponseEntity<?> getProductos();

    public ResponseEntity<?> getProductoId(Long codigoProducto);

    public ResponseEntity<?> altaProducto(ProductoDtoRequest producto);

    public ResponseEntity<?> eliminarProducto(Long id);

    public ResponseEntity<?> editarProducto(ProductoDtoRequest producto, Long id);

}