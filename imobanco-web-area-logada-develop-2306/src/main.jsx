import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.jsx";
import { ReactKeycloakProvider } from "@react-keycloak/web";
import keycloak from "./Keycloak.js";

ReactDOM.createRoot(document.getElementById("root")).render(
  <ReactKeycloakProvider
    authClient={keycloak}
    initOptions={{
      checkLoginIframe: false,
      onLoad: "login-required",
      flow: "standard",
      pkceMethod: "S256",
    }}
  >
    <React.StrictMode>
      <App />
    </React.StrictMode>
  </ReactKeycloakProvider>
);
