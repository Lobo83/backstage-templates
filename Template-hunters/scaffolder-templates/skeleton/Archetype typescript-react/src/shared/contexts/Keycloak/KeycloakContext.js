import React, { useCallback, createContext, useState, useEffect } from 'react'
import Keycloak from 'keycloak-js'
import Cookies from 'js-cookie';
import { decode } from 'js-base64';
import { keycloakConfig } from '../../constants';
const AUTH_TOKEN_KEY = 'authenticationToken';
const AUTH_KEYCLOAK_REFRESH_TOKEN_KEY = 'keycloak-refreshToken';
const AUTH_KEYCLOAK_TOKEN_KEY = 'keycloak-token';

export const clearAuthToken = () => {
    Cookies.remove(AUTH_TOKEN_KEY);
    Cookies.remove(AUTH_KEYCLOAK_REFRESH_TOKEN_KEY);
    Cookies.remove(AUTH_KEYCLOAK_TOKEN_KEY);
  };

export const authToken = () => Cookies.get(AUTH_TOKEN_KEY);
export const keycloakRefreshTokenValue = () => Cookies.get(AUTH_KEYCLOAK_REFRESH_TOKEN_KEY);
export const keycloakTokenValue = () => Cookies.get(AUTH_KEYCLOAK_REFRESH_TOKEN_KEY);

export const setAuthToken = (idToken) => Cookies.set(AUTH_TOKEN_KEY, idToken);
export const setKeycloakToken = (token) => Cookies.set(AUTH_KEYCLOAK_TOKEN_KEY, token);
export const setKeycloakRefreshToken = (token) => Cookies.set(AUTH_KEYCLOAK_REFRESH_TOKEN_KEY, token);

export const tokenExpired = (token) => {
    const parts = token.split('.');
    if (!parts[1]) {
      return true;
    }
    const { exp } = JSON.parse(decode(parts[1]));
    return exp * 1000 < new Date().getTime();
};

export const tokenIsAuthenticated = (token) => token && !tokenExpired(token);

const defaultCreateContextValue = { isAuthenticated: null, logout: null };

export const KeycloackContext = createContext(defaultCreateContextValue)

export const saveTokenInfo = (keycloak) => {
    const { idToken, token, refreshToken } = keycloak;
    setAuthToken(idToken);
    setKeycloakToken(token);
    setKeycloakRefreshToken(refreshToken);
};

export const KeycloackContextProvider = (props) => {
    const { keycloakUrl } = props.config;
    const [initialLoad, setInitialLoad] = useState(true);
    const [isAuthenticated, setIsAuthenticated] = useState(tokenIsAuthenticated(authToken()));
    const [keycloack, setkeycloack] = useState();

    const keycloak = Keycloak({ ...keycloakConfig, url: `${keycloakUrl}/auth` });

    const init = (loadNewToken = false) => {
        if (loadNewToken) {
          clearAuthToken();
        }
        keycloak
          .init({ onLoad: 'login-required', checkLoginIframe: false, token: keycloakTokenValue(), refreshToken: keycloakRefreshTokenValue() })
          .then(authenticated => {
            if (authenticated) {
              saveTokenInfo(keycloak);
              setIsAuthenticated(true);

            } else {
              setIsAuthenticated(false);
            }
            setkeycloack(keycloak);
            setInitialLoad(false);
          })
        keycloak.onTokenExpired = () => {
          keycloak
            .updateToken(30)
            .then(() => {
              saveTokenInfo(keycloak);
            })
            .catch(() => (document.location.href = `${document.location.href}?refresh=true`));
        };
      };

    useEffect(() => {
        init(!tokenIsAuthenticated(authToken()));
        setInitialLoad(false);
    }, []);

    const logout = useCallback(() => {
        keycloak
          .init({ onLoad: 'login-required', checkLoginIframe: false, token: keycloakTokenValue(), refreshToken: keycloakRefreshTokenValue() })
          .then(() => {
            keycloak.logout();
          });
      }, [keycloak]);
    

    return (
        <KeycloackContext.Provider
            value={{
                keycloack,
                isAuthenticated,
                logout
            }}
        >
            {initialLoad ? null : props['children']}
        </KeycloackContext.Provider>
    )
}

