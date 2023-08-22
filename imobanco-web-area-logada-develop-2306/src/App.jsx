import { useKeycloak } from "@react-keycloak/web";

function App() {
  const { keycloak } = useKeycloak();
  return keycloak.authenticated === false ? null : <h1>Logado</h1>;
}

export default App;
