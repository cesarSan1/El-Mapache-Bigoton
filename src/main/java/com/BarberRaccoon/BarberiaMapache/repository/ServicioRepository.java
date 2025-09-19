package com.BarberRaccoon.BarberiaMapache.repository;

import com.BarberRaccoon.BarberiaMapache.model.Servicio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends CrudRepository<Servicio, Long> {

}