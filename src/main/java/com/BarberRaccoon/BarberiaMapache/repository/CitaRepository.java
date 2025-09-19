package com.BarberRaccoon.BarberiaMapache.repository;

import com.BarberRaccoon.BarberiaMapache.model.Cita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends CrudRepository<Cita, Long> {

}
