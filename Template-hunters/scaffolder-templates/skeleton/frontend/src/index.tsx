import React from "react";
import ReactDOM from 'react-dom';
import * as serviceWorkerRegistration from './serviceWorkerRegistration';
import App from './shared/components/App/App'
import { Provider } from 'react-redux';
import store from "./store";
import { KeycloackContextProvider } from "./shared/contexts/Keycloak/KeycloakContext";
import { environment1 } from './shared/constants';

const config = {
    keycloakUrl: environment1.KEYCLOAK_FRONT_URL,
};

ReactDOM.render(
  <KeycloackContextProvider config={config}>
    <Provider store={store}>
      <div>
        <App/>
      </div>
    </Provider>
  </KeycloackContextProvider>
  ,document.getElementById("root")
);

//Register the service worker
serviceWorkerRegistration.register();
