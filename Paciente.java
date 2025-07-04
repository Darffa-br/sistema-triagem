package com.triagem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int idade;
    private String sintomas;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Enumerated(EnumType.STRING)
    private Gravidade gravidade;

    public enum Prioridade { VERMELHA, AMARELA, VERDE }
    public enum Gravidade { LEVE, MODERADA, GRAVE }
}