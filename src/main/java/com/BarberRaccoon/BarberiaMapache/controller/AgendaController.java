package com.BarberRaccoon.BarberiaMapache.controller;

import com.BarberRaccoon.BarberiaMapache.model.Agenda;
import com.BarberRaccoon.BarberiaMapache.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepository;

    @GetMapping
    public ResponseEntity<Iterable<Agenda>> findAll() {
        return ResponseEntity.ok(this.agendaRepository.findAll());
    }

    @GetMapping("/{idAgenda}")
    public ResponseEntity<Agenda> findById(@PathVariable Long idAgenda) {
        Optional<Agenda> agenda = agendaRepository.findById(idAgenda);
        if (agenda.isPresent()) {
            return ResponseEntity.ok(agenda.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Agenda> create(@RequestBody Agenda newAgenda, UriComponentsBuilder uriBuilder) {
        newAgenda.setCita(null);
        Agenda savedAgenda = agendaRepository.save(newAgenda);
        URI uri = uriBuilder.path("/agenda/{idAgenda}")
                .buildAndExpand(savedAgenda.getIdAgenda())
                .toUri();
        return ResponseEntity.created(uri).body(savedAgenda);
    }

    @PutMapping("/{idAgenda}")
    public ResponseEntity<Agenda> update(@PathVariable Long idAgenda, @RequestBody Agenda agendaAct) {
        Optional<Agenda> optionalAgenda = agendaRepository.findById(idAgenda);
        if (optionalAgenda.isPresent()) {
            Agenda agenda = optionalAgenda.get();
            agenda.setAnio(agendaAct.getAnio());
            agenda.setMes(agendaAct.getMes());
            agendaRepository.save(agenda);
            return ResponseEntity.ok(agenda);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{idAgenda}")
    public ResponseEntity<Agenda> delete(@PathVariable Long idAgenda) {
        Optional<Agenda> optionalAgenda = agendaRepository.findById(idAgenda);
        if (optionalAgenda.isPresent()) {
            agendaRepository.deleteById(idAgenda);
            return ResponseEntity.ok(optionalAgenda.get());
        }
        return ResponseEntity.notFound().build();
    }
}
