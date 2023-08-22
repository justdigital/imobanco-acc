CREATE TABLE IF NOT EXISTS genero_usu_pf
(
    cd_genero_usu SMALLINT auto_increment,
    ds_genero_usu VARCHAR(12) NOT NULL UNIQUE,
    PRIMARY KEY(cd_genero_usu)
);

CREATE TABLE IF NOT EXISTS cadastro_pf
(
    id_cadastro INT NOT NULL auto_increment,
    id_usu_keycloak VARCHAR(48) UNIQUE,
    ts_criacao timestamp DEFAULT current_timestamp,
    nm_usu VARCHAR(60) NOT NULL,
    no_cpf_usu VARCHAR(11) UNIQUE,
    nm_mae VARCHAR(60) NOT NULL,
    dt_nasc DATE NOT NULL,
    email_usu VARCHAR(30) NOT NULL UNIQUE,
    no_fone_usu VARCHAR(13) NOT NULL UNIQUE,
    no_ddd_fone_usu VARCHAR(2),
    cd_genero_usu SMALLINT,
    nm_logradouro VARCHAR(40) NOT NULL,
    no_endereco VARCHAR(10),
    no_cep VARCHAR(8),
    ds_comp_end VARCHAR(20),
    cd_pep TINYINT(1) DEFAULT false NOT NULL,
    cd_aceite_termo TINYINT(1) DEFAULT true NOT NULL,
    ts_atualizacao timestamp null,
    ts_inativacao timestamp  null,
    PRIMARY KEY(id_cadastro),
    FOREIGN KEY FK_GENERO_USU (cd_genero_usu) REFERENCES genero_usu_pf(cd_genero_usu)
);





