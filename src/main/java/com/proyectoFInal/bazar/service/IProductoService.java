package com.proyectoFInal.bazar.service;

import com.proyectoFInal.bazar.model.Producto;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IProductoService {

    public ResponseEntity<?> getProductos();

    public ResponseEntity<?> getProductoId(Long codigoProducto);

    public ResponseEntity<?> altaProducto(Producto producto);

    public ResponseEntity<?> eliminarProducto(Long id);

    public ResponseEntity<?> editarProducto(Producto producto, Long id);

}