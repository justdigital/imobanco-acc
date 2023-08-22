package com.imobanco.autenticador.api.model.domain.model;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="cadastro_pf")
@Data
public class CadastroPf {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cadastro",nullable = false)
    private Long idCadastro;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy="cadastroPf")
    Set<AtividadeCadastro> atividadesCadastro;

    @ManyToOne(cascade= {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name="cd_genero_usu")
    GeneroUsuPf generoUsuPf;

    @Column(name="id_usu_keycloak",nullable = false)
    private String idUsuKeycloak;

    @Column(name="ts_criacao",nullable = false, updatable = false)
    private LocalDateTime tsCriacao;

    @Column(name="nm_usu",nullable = false)
    private String nomeUsu;

    @Column(name="no_cpf_usu",nullable = false)
    private String numeroCpfUsu;

    @Column(name="nm_mae",nullable = false)
    private String nomeMae;

    @Column(name="dt_nasc",nullable = false)
    private LocalDate dataNasc;

    @Column(name="email_usu",nullable = false)
    private String emailUsu;

    @Column(name="no_fone_usu",nullable = false)
    private String numeroFoneUsu;

    @Column(name="no_ddd_fone_usu",nullable = false)
    private String numeroDddFoneUsu;

   // @Column(name="cd_genero_usu",nullable = false)
   // private Integer codigoGeneroUsu;

    @Column(name="nm_logradouro",nullable = false)
    private String nomeLogradouro;

    @Column(name="no_endereco",nullable = false)
    private String numeroEndereço;

    @Column(name="no_cep",nullable = false)
    private String numeroCep;

    @Column(name="ds_comp_end",nullable = false)
    private String descricaoCompEnd; //ds = decricao no = numero , cd = codigo, nm = nome

    @Column(name="cd_pep",nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean codigoPep;

    @Column(name="cd_aceite_termo",nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean codigoAceiteTermo;

    @Column(name="ts_atualizacao",nullable = true)
    private LocalDateTime tsAtualizacao;

    @Column(name="ts_inativacao",nullable = true)
    private LocalDateTime tsInativacao;

    public void addAtividadeCadastro(AtividadeCadastro atividadeCadastro) {
        if(this.atividadesCadastro == null)
            this.atividadesCadastro = new HashSet<>();
        atividadeCadastro.setCadastroPf(this);
        atividadeCadastro.setIdAtividadeCadastro(null);
        this.atividadesCadastro.add(atividadeCadastro);

    }

    public void removeAtividadeCadastro(AtividadeCadastro atividadeCadastro) {
        this.atividadesCadastro.remove(atividadeCadastro);
        atividadeCadastro.setCadastroPf(null);

    }

    public boolean seNomeIguail(String nome){
        return this.nomeUsu.equalsIgnoreCase(nome);
    }

    public boolean seDataNascimentoIgual(LocalDate dataNasc){
        return this.dataNasc.isEqual(dataNasc);
    }

    public boolean seNomeMaeIgual(String nomeMae){
        return this.nomeMae.equalsIgnoreCase(nomeMae);
    }












}
