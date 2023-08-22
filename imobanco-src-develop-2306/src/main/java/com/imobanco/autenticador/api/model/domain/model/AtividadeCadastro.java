package com.imobanco.autenticador.api.model.domain.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="atividade_cadastro")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtividadeCadastro {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_atividade_cadastro",nullable = false)
    private Long idAtividadeCadastro;

    @ManyToOne
    @JoinColumn(name="id_cadastro_pf")
    CadastroPf cadastroPf;

    @ManyToOne
    @JoinColumn(name="id_status_onboardind")
    StatusOnboarding statusOnboarding;

    @Column(name="ts_data_inicio",nullable = false, updatable = false)
    private LocalDateTime tsDataInicio;

    @Column(name="ts_data_fim")
    private LocalDateTime tsDataFim;


   }
