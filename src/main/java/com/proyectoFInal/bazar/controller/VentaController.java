package com.proyectoFInal.bazar.controller;

import com.proyectoFInal.bazar.dto.VentaDto;
import com.proyectoFInal.bazar.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bazar")
public class VentaController {

    @Autowired
    VentaService ventaService;

    @PostMapping("/ventas/crear")
    public ResponseEntity<?> crearVenta(@RequestBody @Valid VentaDto ventaDto){

        ResponseEntity response = ventaService.createVenta(ventaDto);
        return response;
    }

    @GetMapping("/ventas/obtenerVentas")
    public ResponseEntity<?> obtenerVentas(){

        ResponseEntity response = ventaService.getVentas();

        return response;
    }

    @GetMapping("/ventas/obtenerVentas/{id}")
    public ResponseEntity<?> obtenerVentasId(@PathVariable Long id){

        ResponseEntity response = ventaService.getVentaId(id);

        return response;
    }

    @DeleteMapping("/ventas/eliminar/{id}")
    public ResponseEntity<?> eliminarVenta(@PathVariable Long id){

        ResponseEntity response = ventaService.deleteVenta(id);

        return response;
    }

    @PutMapping("/ventas/actualizar/{id}")
    public ResponseEntity<?> actualizarVenta(@RequestBody @Valid VentaDto ventaDto, @PathVariable Long id){

        ResponseEntity response = ventaService.updateVenta(ventaDto, id);

        return response;
    }


}
