package com.proyectoFInal.bazar.service;

import com.proyectoFInal.bazar.dto.VentaDto;
import com.proyectoFInal.bazar.model.Cliente;
import com.proyectoFInal.bazar.model.Producto;
import com.proyectoFInal.bazar.model.Venta;
import com.proyectoFInal.bazar.repository.ClienteRepository;
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
import java.util.stream.Collectors;

@Service
public class VentaService implements IVentaService {

    private static Logger logger = LoggerFactory.getLogger(VentaService.class);

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    VentasRepository ventasRepository;


    @Override
    public ResponseEntity<?> createVenta(VentaDto ventaDto) {
        Cliente clienteExistente = clienteRepository.findById(ventaDto.getCliente().getId_cliente()).orElse(null);
        double total = 0;

        logger.info("Info:" + ventaDto.getListaProductos());

        for (Producto Promo : ventaDto.getListaProductos()){

            total = total + Promo.getCosto();

            logger.info("Info: " + total);
        }

        ventaDto.setTotal(total);

        if (clienteExistente != null) {
            Venta venta = Venta.builder()
                    .fechaVenta(ventaDto.getFecha_venta())
                    .Total(ventaDto.getTotal())
                    .cliente(clienteExistente)
                    .productos(ventaDto.getListaProductos())
                    .build();
            ventasRepository.save(venta);
            return new ResponseEntity<>(venta, HttpStatus.CREATED);
         } else {
            // Si el cliente no existe, maneja el error o lógica de creación
            System.out.println("Cliente no encontrado");
            return new ResponseEntity<>("Error", HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<?> getVentas() {
        logger.info("Iniciando metodo para obtener las ventas");
        try {
            List<Venta> list = ventasRepository.findAll();
            if(list.isEmpty()) {

                return new ResponseEntity<String>("No hay ventas registradas", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Venta>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error al obtener las ventas", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<?> getVentaId(Long id) {
        logger.info("Iniciando metodo para obtener venta por id");
        try {
            Optional<Venta> ventaBuscada = ventasRepository.findById(id);
            if(ventaBuscada.isPresent()) {
                logger.info("Venta encontrada");
                return new ResponseEntity<Venta>(ventaBuscada.get(), HttpStatus.OK);
            } else {
                logger.error("Venta no encontrada");
                return new ResponseEntity<String>("Error no existe venta con ese ID", HttpStatus.NOT_FOUND);
            }

        } catch (DataAccessException e) {
            logger.error("Error al recuperar la venta");
            logger.error(e.getMessage());
            return new ResponseEntity<String>("No se pudo recuperar la venta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteVenta(Long id) {
        try {
            Optional<Venta> ventaBuscada = ventasRepository.findById(id);
            if(ventaBuscada.isPresent()) {
                logger.info("Venta encontrada");
                ventasRepository.deleteById(id);
                return new ResponseEntity<String>("Cliente eliminado con exito", HttpStatus.OK);
            } else {
                logger.error("Venta no encontrada");
                return new ResponseEntity<String>("Error no existe una venta con ese ID", HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            logger.error("Error al eliminar la venta");
            logger.error(e.getMessage());
            return new ResponseEntity<String>("No se pudo eliminar la venta solicitada", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateVenta(VentaDto ventaDto, Long id) {
        try {
            Optional<Venta> ventaBuscada = ventasRepository.findById(id);
            if(ventaBuscada.isPresent()) {
                logger.info("Venta encontrada");
              /*  ventaBuscada.get().setFecha_venta(ventaDto.getFecha_venta());
                ventaBuscada.get().setListaProductos(ventaDto.getListaProductos());
                ventaBuscada.get().setCliente(ventaDto.getCliente()); */

                ventasRepository.save(ventaBuscada.get());
                return new ResponseEntity<String>("Venta eliminada con exito", HttpStatus.OK);
            } else {
                logger.error("Venta encontrada");
                return new ResponseEntity<String>("Error no existe venta con ese ID", HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            logger.error("Error al actualizar venta");
            logger.error(e.getMessage());
            return new ResponseEntity<String>("No se pudo actualizar la venta solicitada", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    }

