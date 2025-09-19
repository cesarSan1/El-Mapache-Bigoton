package com.BarberRaccoon.BarberiaMapache.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.BarberRaccoon.BarberiaMapache.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {


}
