import Keycloak from "keycloak-js";

const initOptions = {
  url: "http://localhost:8080/",
  realm: "master",
  clientId: "account",
};

const keycloak = new Keycloak(initOptions);

export default keycloak;
