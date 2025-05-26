package com.ecomarket.ventas.service;

import com.ecomarket.ventas.model.DetalleVenta;
import com.ecomarket.ventas.model.Venta;
import com.ecomarket.ventas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    public Venta registrarVenta(Venta venta) {
        double total = 0;

        for (DetalleVenta detalle : venta.getDetalles()) {
            double subtotal = detalle.getPrecioUnitario() * detalle.getCantidad();
            detalle.setSubtotal(subtotal);
            detalle.setVenta(venta);
            total += subtotal;
        }

        venta.setTotal(total);
        return ventaRepository.save(venta);
    }

    public List<Venta> obtenerTodas() {
        return ventaRepository.findAll();
    }

    public Venta obtenerPorId(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }

    public List<Venta> obtenerPorClienteID(Integer clienteId) {
        return ventaRepository.findByClienteId(clienteId);
    }
}
