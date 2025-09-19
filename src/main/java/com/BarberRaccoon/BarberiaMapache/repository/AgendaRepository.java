package com.BarberRaccoon.BarberiaMapache.repository;

import com.BarberRaccoon.BarberiaMapache.model.Agenda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends CrudRepository<Agenda, Long> {
}
