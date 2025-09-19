package com.BarberRaccoon.BarberiaMapache.controller;

import com.BarberRaccoon.BarberiaMapache.model.Cliente;
import com.BarberRaccoon.BarberiaMapache.model.Servicio;
import com.BarberRaccoon.BarberiaMapache.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/servicio")
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping
    public ResponseEntity<Iterable<Servicio>> findAll() {
        return ResponseEntity.ok(servicioRepository.findAll());
    }

    @GetMapping("/{idServicio}")
    public ResponseEntity<Servicio> findById(@PathVariable Long idServicio) {
        Optional<Servicio> servicio = servicioRepository.findById(idServicio);
        return servicio.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Servicio newServicio, UriComponentsBuilder uriBuilder) {
        Servicio savedServicio = servicioRepository.save(newServicio);
        URI uri = uriBuilder.path("/servicio/{idServicio}")
                .buildAndExpand(savedServicio.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{idServicio}")
    public ResponseEntity<Void> update(@PathVariable Long idServicio, @RequestBody Servicio ServicioAct) {
        Servicio updatedServicio = servicioRepository.findById(idServicio).get();
        if(updatedServicio != null){
            updatedServicio.setId(updatedServicio.getId());
            servicioRepository.save(updatedServicio);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{idServicio}")
    public ResponseEntity<Void> delete(@PathVariable Long idServicio) {
        if (servicioRepository.existsById(idServicio)) {
            servicioRepository.deleteById(idServicio);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
