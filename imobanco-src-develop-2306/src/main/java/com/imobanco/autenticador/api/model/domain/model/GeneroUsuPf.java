package com.imobanco.autenticador.api.model.domain.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="genero_usu_pf")
@Data
public class GeneroUsuPf {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_genero_usu")
    private Long codigo_genero_usu;

    @Column(name = "ds_genero_usu", nullable = false, unique = true)
    private String descricao_genero_usu;

    public String getSiglaGenero(){
        return this.descricao_genero_usu.substring(0,1);
    }

}

