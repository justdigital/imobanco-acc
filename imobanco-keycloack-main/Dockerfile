FROM quay.io/keycloak/keycloak

#ARG AUTHENTICATOR_JAR=authenticators/target/authenticators.jar

# copy the jars ...
#COPY ${AUTHENTICATOR_JAR} /opt/jboss/keycloak/standalone/deployments/

# theme customisation region

ARG CODELENS_THEME_BASE_DIR=/opt/keycloak/themes/codelens

ARG CODELENS_THEME_LOCAL_ROOT_DIR=themes/codelens

RUN mkdir ${CODELENS_THEME_BASE_DIR}

COPY ${CODELENS_THEME_LOCAL_ROOT_DIR} ${CODELENS_THEME_BASE_DIR}
