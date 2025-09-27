package com.BarberRaccoon.BarberiaMapache.controller;

import com.BarberRaccoon.BarberiaMapache.model.Agenda;
import com.BarberRaccoon.BarberiaMapache.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepository;

    @GetMapping
    public ResponseEntity<Iterable<Agenda>> findAll() {
        return ResponseEntity.ok(agendaRepository.findAll());
    }

    @GetMapping("/{idAgenda}")
    public ResponseEntity<Agenda> findById(@PathVariable Long idAgenda) {
        Optional<Agenda> agenda = agendaRepository.findById(idAgenda);
        return agenda.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Agenda newAgenda, UriComponentsBuilder uriBuilder) {
        Agenda savedAgenda = agendaRepository.save(newAgenda);
        URI uri = uriBuilder.path("/agenda/{idAgenda}")
                .buildAndExpand(savedAgenda.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{idAgenda}")
    public ResponseEntity<Void> update(@PathVariable Long idAgenda, @RequestBody Agenda agendaAct) {
        Optional<Agenda> optionalAgenda = agendaRepository.findById(idAgenda);

        if (optionalAgenda.isPresent()) {
            Agenda agenda = optionalAgenda.get();
            agenda.setAnio(agendaAct.getAnio());
            agenda.setMes(agendaAct.getMes());
            agenda.setCita(agendaAct.getCita());
            agendaRepository.save(agenda);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

<<<<<<< HEAD

=======
 
>>>>>>> ed4d0a1 (Commit de Cesar)
    @DeleteMapping("/{idAgenda}")
    public ResponseEntity<Void> delete(@PathVariable Long idAgenda) {
        if (agendaRepository.existsById(idAgenda)) {
            agendaRepository.deleteById(idAgenda);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
