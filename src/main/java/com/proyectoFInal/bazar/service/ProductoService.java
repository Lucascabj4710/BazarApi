package com.proyectoFInal.bazar.service;

import com.proyectoFInal.bazar.model.Producto;
import com.proyectoFInal.bazar.repository.ProductoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {

	private static final Logger log = LoggerFactory.getLogger(ProductoService.class);
	
    @Autowired
    ProductoRepository productRepository;

	@Override
	public ResponseEntity<?> getProductos() {
		log.info("Iniciando el metodo getProductos");
		List<Producto> list;
		
		try {
			list= productRepository.findAll();
			if(list.isEmpty()) {
				return new ResponseEntity<String>("No hay productos disponibles", HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error al recuperar los productos", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
		return new ResponseEntity<List<Producto>>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getProductoId(Long codigoProducto) {
		log.info("Iniciando el metodo getProductoId");
		try {
			Optional<Producto> productoBuscado = productRepository.findById(codigoProducto);
			if(productoBuscado.isPresent()) {
				log.info("Producto encontrado");
				return new ResponseEntity<Producto>(productoBuscado.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Error al recuperar los productos", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error al recuperar los productos", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> altaProducto(Producto producto) {
		log.info("Iniciando metodo altaProducto");
		try {
			productRepository.save(producto);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error al recuperar los productos", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<?> eliminarProducto(Long id) {
		try {
			Optional<Producto> productoBuscado = productRepository.findById(id);
			if(productoBuscado.isPresent()) {
				log.info("Producto encontrado");
				productRepository.deleteById(id);
			} else {
				return new ResponseEntity<String>("Error producto no encontrado", HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			return new ResponseEntity<String>("Error al eliminar el producto", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e){
			return new ResponseEntity<String>("Error al eliminar el producto", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("Producto eliminado con exito", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> editarProducto(Producto producto, Long id) {
		
		try {
			Optional<Producto> productoBuscado = productRepository.findById(id);
			if(productoBuscado.isPresent()) {
				log.info("Producto encontrado");
				Producto productoActualizado = productoBuscado.get();
				
				log.info("Hola mundo" + productoActualizado.getCodigoProducto());
				productoBuscado.get().setNombre(producto.getNombre());
				productoBuscado.get().setMarca(producto.getMarca());
				productoBuscado.get().setCosto(producto.getCosto());			
				productoBuscado.get().setCantidad_disponible(producto.getCantidad_disponible());
				
				
				productRepository.save(productoBuscado.get());
				return new ResponseEntity<String>("Producto actualizado con exito", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Error al editar producto", HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			log.error("Error al editar producto en la base de datos");
			return new ResponseEntity<String>("Error al editar el producto", HttpStatus.INTERNAL_SERVER_ERROR);
		}			
	}

  
}
