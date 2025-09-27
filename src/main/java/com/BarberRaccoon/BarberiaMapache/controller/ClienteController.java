package com.BarberRaccoon.BarberiaMapache.controller;

import com.BarberRaccoon.BarberiaMapache.model.Cliente;
import com.BarberRaccoon.BarberiaMapache.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Cliente>> findAll() {
        return ResponseEntity.ok(this.clienteRepository.findAll());
    }
    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> findById(@PathVariable Long idCliente) {
        Optional<Cliente> clienteOpcional = clienteRepository.findById(idCliente);
        if (clienteOpcional.isPresent()) {
            return ResponseEntity.ok(clienteOpcional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Cliente newCliente, UriComponentsBuilder uriBuilder) {
        Cliente savedCliente = clienteRepository.save(newCliente);
        URI uri =  uriBuilder.path("/cliente/{idCliente}").buildAndExpand(savedCliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Void> update(@PathVariable Long idCliente, @RequestBody Cliente ClienteAct) {
        Cliente updatedCliente = clienteRepository.findById(idCliente).get();
        if(updatedCliente != null){
            updatedCliente.setId(updatedCliente.getId());
            clienteRepository.save(updatedCliente);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


<<<<<<< HEAD
=======
    
>>>>>>> ed4d0a1 (Commit de Cesar)
    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> delete(@PathVariable Long idCliente) {
        if(clienteRepository.existsById(idCliente)){
            clienteRepository.deleteById(idCliente);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
