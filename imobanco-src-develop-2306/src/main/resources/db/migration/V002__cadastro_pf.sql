CREATE TABLE IF NOT EXISTS status_onboarding
(
    id_status_onboard INT NOT NULL auto_increment,
    ds_status VARCHAR(100) NOT NULL,
    nm_ordem INT NOT NULL UNIQUE,
    nm_sequencia INT NOT NULL UNIQUE,
    ds_descricao VARCHAR(100) UNIQUE,
    PRIMARY KEY(id_status_onboard)
 );

 INSERT INTO status_onboarding values (null, 'CADASTRO', 1, 1, 'Fase de cadastro inicial');
 INSERT INTO status_onboarding values ( null,'UPLOAD', 2, 2, 'Fase de upload de documentos');
