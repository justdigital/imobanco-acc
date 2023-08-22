package com.imobanco.autenticador.api.model.domain.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="status_onboarding")
@Data
public class StatusOnboarding {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_status_onboard",nullable = false)
    private Long idStatusOnboarding;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy="statusOnboarding")
    Set<AtividadeCadastro> atividadeCadastro;

    @Column(name="ds_status",nullable = false)
    private String descricaoStatus;

    @Column(name="nm_ordem",nullable = false)
    private Long numeroOrdem;

    @Column(name="nm_sequencia",nullable = false)
    private String numeroSequencia;

    @Column(name="ds_descricao",nullable = false)
    private String descricao;

   }
