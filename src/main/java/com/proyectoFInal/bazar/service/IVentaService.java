package com.proyectoFInal.bazar.service;

import com.proyectoFInal.bazar.dto.VentaDto;
import org.springframework.http.ResponseEntity;

public interface IVentaService {

    public ResponseEntity<?> createVenta(VentaDto ventaDto);
    public ResponseEntity<?> getVentas();
    public ResponseEntity<?> getVentaId(Long id);
    public ResponseEntity<?> deleteVenta(Long id);
    public ResponseEntity<?> updateVenta(VentaDto ventaDto, Long id);


}
