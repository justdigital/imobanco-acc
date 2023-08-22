CREATE TABLE IF NOT EXISTS atividade_cadastro
(
    id_atividade_cadastro INT auto_increment,
    id_cadastro_pf  INT NOT NULL,
    id_status_onboarding INT NOT NULL ,
    ts_data_inicio timestamp DEFAULT current_timestamp,
    ts_data_fim timestamp null,
    PRIMARY KEY(id_atividade_cadastro),
    FOREIGN KEY FK_ATIVIDADE_CADASTRO_PF(id_cadastro_pf) REFERENCES cadastro_pf(id_cadastro),
    FOREIGN KEY FK_STATUS_ONBOARDING(id_status_onboardind) REFERENCES status_onboarding(id_status_onboard)
 );