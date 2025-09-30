package com.BarberRaccoon.BarberiaMapache.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAgenda")
    private Long idAgenda;

    private int anio;
    private int mes;

    @OneToOne
    @JoinColumn(name = "idCita", nullable = false)
    private Cita cita;
}
