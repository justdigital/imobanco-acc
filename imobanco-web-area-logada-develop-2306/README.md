# Alteração no Keycloack

- Dentro do Keycloack, ir na aba 'clients'
- Abrir o client 'account'
- Editar os campos Root URL, Home URL e Valid redirect URIs
- Inserir nesses 3 campos o seguinte valor: http://localhost:5173/
- Esse é o endereço da aplicação React

# Executar o APP

- Execute o comando yarn no painel
- Execute o comando yarn dev
- Abra o app em uma guia anônima, pois na guia normal vc provavelmente já estará logado
- Faça o login e verifique se o app abriu como 'Logado'

# Configuração Keycloack

- Caso precise alterar algum dado a aplicação keycloack, altere no arquivo Keycloack.js
- Nesse arquivo vc consegue alterar o REALM, CLIENT ID e URL do Keycloack
