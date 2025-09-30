package com.BarberRaccoon.BarberiaMapache.controller;

import com.BarberRaccoon.BarberiaMapache.model.Cita;
import com.BarberRaccoon.BarberiaMapache.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5500")
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
        Optional<Cita> citaOpcional = citaRepository.findById(idCita);
        if (citaOpcional.isPresent()) {
            return ResponseEntity.ok(citaOpcional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cita> create(@RequestBody Cita newCita, UriComponentsBuilder uriBuilder) {

        Cita savedCita = citaRepository.save(newCita);
        URI uri = uriBuilder.path("/cita/{idCita}")
                .buildAndExpand(savedCita.getIdCita())
                .toUri();
        return ResponseEntity.created(uri).body(savedCita);
    }

    @PutMapping("/{idCita}")
    public ResponseEntity<Cita> update(@PathVariable Long idCita, @RequestBody Cita citaAct) {
        Optional<Cita> optionalCita = citaRepository.findById(idCita);

        if (optionalCita.isPresent()) {
            Cita cita = optionalCita.get();
            cita.setFecha(citaAct.getFecha());
            cita.setHora(citaAct.getHora());
            cita.setServicio(citaAct.getServicio());
            cita.setCliente(citaAct.getCliente());

            citaRepository.save(cita);
            return ResponseEntity.ok(cita);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{idCita}")
    public ResponseEntity<Cita> delete(@PathVariable Long idCita) {
        Optional<Cita> citaOptional = citaRepository.findById(idCita);
        if (citaOptional.isPresent()) {
            citaRepository.deleteById(idCita);
            return ResponseEntity.ok(citaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
