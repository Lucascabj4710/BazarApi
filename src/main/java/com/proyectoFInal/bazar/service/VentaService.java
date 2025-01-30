package com.proyectoFInal.bazar.service;

import com.proyectoFInal.bazar.dto.ProductoDto;
import com.proyectoFInal.bazar.dto.VentaDto;
import com.proyectoFInal.bazar.model.Cliente;
import com.proyectoFInal.bazar.model.Producto;
import com.proyectoFInal.bazar.model.Venta;
import com.proyectoFInal.bazar.repository.ClienteRepository;
import com.proyectoFInal.bazar.repository.ProductoRepository;
import com.proyectoFInal.bazar.repository.VentasRepository;
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
public class VentaService implements IVentaService {

    final String ventaEncontrada = "Venta encontrada";

    private static Logger logger = LoggerFactory.getLogger(VentaService.class);

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    VentasRepository ventasRepository;
    @Autowired
    ProductoRepository productoRepository;


    @Override
    public ResponseEntity<?> createVenta(VentaDto ventaDto) {
        Cliente clienteExistente = clienteRepository.findById(ventaDto.getCliente().getId_cliente()).orElse(null);
        List<Producto> productos = new ArrayList<>();
        double total = 0;

        for (ProductoDto productoDto : ventaDto.getListaProductos()) {

            Producto producto = productoRepository.findById(productoDto.getId()).orElse(null);
            productos.add(producto);

            total = total + (producto.getCosto() * productoDto.getCantidad());

        }



    logger.info("El total es:" + total);

        if (clienteExistente != null) {
            Venta venta = Venta.builder()
                    .fechaVenta(ventaDto.getFecha_venta())
                    .Total(total)
                    .cliente(clienteExistente)
                    .productos(productos)
                    .build();
            ventasRepository.save(venta);
            return new ResponseEntity<>(venta, HttpStatus.CREATED);
         } else {
            // Si el cliente no existe, maneja el error o lógica de creación
            logger.info("Cliente no encontrado");
            return new ResponseEntity<>("Error", HttpStatus.NO_CONTENT);
        }
    }


    @Override
    @Transactional
    public ResponseEntity<?> getVentas() {
        logger.info("Iniciando metodo para obtener las ventas");
        try {
            List<Venta> list = ventasRepository.findAll();


            if(list.isEmpty()) {

                return new ResponseEntity<>("No hay ventas registradas", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener las ventas", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<?> getVentaId(Long id) {
        logger.info("Iniciando metodo para obtener venta por id");
        try {
            Optional<Venta> ventaBuscada = ventasRepository.findById(id);
            if(ventaBuscada.isPresent()) {
                logger.info(ventaEncontrada);
                return new ResponseEntity<>(ventaBuscada.get(), HttpStatus.OK);
            } else {
                logger.error("Venta no encontrada");
                return new ResponseEntity<>("Error no existe venta con ese ID", HttpStatus.NOT_FOUND);
            }

        } catch (DataAccessException e) {
            logger.error("Error al recuperar la venta");
            logger.error(e.getMessage());
            return new ResponseEntity<>("No se pudo recuperar la venta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteVenta(Long id) {
        try {
            Optional<Venta> ventaBuscada = ventasRepository.findById(id);
            if(ventaBuscada.isPresent()) {
                logger.info(ventaEncontrada);
                ventasRepository.deleteById(id);
                return new ResponseEntity<>("Cliente eliminado con exito", HttpStatus.OK);
            } else {
                logger.error("Venta no encontrada");
                return new ResponseEntity<>("Error no existe una venta con ese ID", HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            logger.error("Error al eliminar la venta");
            logger.error(e.getMessage());
            return new ResponseEntity<>("No se pudo eliminar la venta solicitada", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateVenta(VentaDto ventaDto, Long id) {
        try {
            Optional<Venta> ventaBuscada = ventasRepository.findById(id);
            if(ventaBuscada.isPresent()) {
                logger.info(ventaEncontrada);
                ventaBuscada.get().setFechaVenta(ventaDto.getFecha_venta());
                //ventaBuscada.get().setProductos();
                ventaBuscada.get().setTotal(100);
                ventaBuscada.get().setCliente(ventaDto.getCliente());

                ventasRepository.save(ventaBuscada.get());
                return new ResponseEntity<>("Venta actualizada con exito", HttpStatus.OK);
            } else {
                logger.error(ventaEncontrada);
                return new ResponseEntity<>("Error no existe venta con ese ID", HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            logger.error("Error al actualizar venta");
            logger.error(e.getMessage());
            return new ResponseEntity<>("No se pudo actualizar la venta solicitada", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    }

