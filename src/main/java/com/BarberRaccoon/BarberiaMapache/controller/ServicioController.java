package com.BarberRaccoon.BarberiaMapache.controller;

import com.BarberRaccoon.BarberiaMapache.model.Servicio;
import com.BarberRaccoon.BarberiaMapache.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5500")
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
        if (servicio.isPresent()) {
            return ResponseEntity.ok(servicio.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Servicio> create(@RequestBody Servicio newServicio, UriComponentsBuilder uriBuilder) {
        Servicio savedServicio = servicioRepository.save(newServicio);
        URI uri = uriBuilder.path("/servicio/{idServicio}")
                .buildAndExpand(savedServicio.getIdServicio())
                .toUri();
        return ResponseEntity.created(uri).body(savedServicio);
    }

    @PutMapping("/{idServicio}")
    public ResponseEntity<Servicio> update(@PathVariable Long idServicio, @RequestBody Servicio ServicioAct) {
        Optional<Servicio> servicio = servicioRepository.findById(idServicio);
        if(servicio.isPresent()){
            Servicio updatedServicio = servicio.get();
            updatedServicio.setServicio(ServicioAct.getServicio());
            updatedServicio.setDescripcion(ServicioAct.getDescripcion());
            updatedServicio.setCosto(ServicioAct.getCosto());
            servicioRepository.save(updatedServicio);
            return ResponseEntity.ok(updatedServicio);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{idServicio}")
    public ResponseEntity<Servicio> delete(@PathVariable Long idServicio) {
        Optional<Servicio> servicio = servicioRepository.findById(idServicio);
        if (servicio.isPresent()) {
            servicioRepository.deleteById(idServicio);
            return ResponseEntity.ok(servicio.get());
        }
        return ResponseEntity.notFound().build();
    }
}
