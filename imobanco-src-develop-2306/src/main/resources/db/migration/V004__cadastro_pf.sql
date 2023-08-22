 DELETE FROM status_onboarding;

 ALTER TABLE status_onboarding AUTO_INCREMENT = 1;

 INSERT INTO status_onboarding values (null, 'CADASTRO', 1, 1, 'Fase de cadastro inicial');
 INSERT INTO status_onboarding values (null, 'CADASTRO_COMPROVANTE_ENDERECO', 2, 2, 'Fase de envio do comprovante de endereço');
 INSERT INTO status_onboarding valgitues ( null,'UPLOAD', 3, 3, 'Fasgie de upload de documentos');