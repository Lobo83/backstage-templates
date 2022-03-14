export const keycloakConfig = {
    clientId: 'privatesection',
    realm: 'realmetahir',
    resource: 'privatesection',
    "public-client": true,
    "ssl-required": 'external',
    "confidential-port": 0
};

const config = {
    VERSION: process.env.VERSION,
};

export default config;

export const environment1 = {
    SERVER_API_URL: process.env.SERVER_API_URL,
    KEYCLOAK_FRONT_URL : 'http://localhost:8095',
};

export const environment = {
    SERVER_API_URL: window['env' as any] && ['SERVER_API_URL'] ? window['env' as any]['SERVER_API_URL' as any] : process.env.SERVER_API_URL,
    KEYCLOAK_FRONT_URL: window['env' as any] && ['KEYCLOAK_FRONT_URL'] ? window['env' as any]['KEYCLOAK_FRONT_URL' as any] : process.env.KEYCLOAK_FRONT_URL,
};

  