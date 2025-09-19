package com.BarberRaccoon.BarberiaMapache.controller;

import com.BarberRaccoon.BarberiaMapache.model.Cita;
import com.BarberRaccoon.BarberiaMapache.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/cita")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @GetMapping
    public ResponseEntity<Iterable<Cita>> findAll() {
        return ResponseEntity.ok(citaRepository.findAll());
    }

    @GetMapping("/{idCita}")
    public ResponseEntity<Cita> findById(@PathVariable Long idCita) {
        Optional<Cita> cita = citaRepository.findById(idCita);
        return cita.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Cita newCita, UriComponentsBuilder uriBuilder) {
        Cita savedCita = citaRepository.save(newCita);
        URI uri = uriBuilder.path("/cita/{idCita}")
                .buildAndExpand(savedCita.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{idCita}")
    public ResponseEntity<Void> update(@PathVariable Long idCita, @RequestBody Cita citaAct) {
        Optional<Cita> optionalCita = citaRepository.findById(idCita);

        if (optionalCita.isPresent()) {
            Cita cita = optionalCita.get();
            cita.setFecha(citaAct.getFecha());
            cita.setHora(citaAct.getHora());
            cita.setServicio(citaAct.getServicio());
            cita.setCliente(citaAct.getCliente());

            citaRepository.save(cita);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{idCita}")
    public ResponseEntity<Void> delete(@PathVariable Long idCita) {
        if (citaRepository.existsById(idCita)) {
            citaRepository.deleteById(idCita);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
