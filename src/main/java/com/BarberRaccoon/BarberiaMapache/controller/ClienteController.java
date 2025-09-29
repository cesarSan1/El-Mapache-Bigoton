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
        URI uri =  uriBuilder.path("/cliente/{idCliente}").buildAndExpand(savedCliente.getIdCliente()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Cliente> update(@PathVariable Long idCliente, @RequestBody Cliente clienteAct) {
        Optional<Cliente> clienteOpcional = clienteRepository.findById(idCliente);
        if(clienteOpcional.isPresent()){
            Cliente updatedCliente = clienteOpcional.get();
            updatedCliente.setNombre(clienteAct.getNombre());
            updatedCliente.setTelefono(clienteAct.getTelefono());
            clienteRepository.save(updatedCliente);
            return ResponseEntity.ok(updatedCliente);  // Devuelve 200 + JSON
        }
        return ResponseEntity.notFound().build(); // 404 si no existe
    }



    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Cliente> delete(@PathVariable Long idCliente) {
        Optional<Cliente> clienteOpcional = clienteRepository.findById(idCliente);
        if(clienteOpcional.isPresent()){
            clienteRepository.deleteById(idCliente);
            return ResponseEntity.ok(clienteOpcional.get()); // Devuelve el cliente eliminado
        }
        return ResponseEntity.notFound().build();
    }


}
