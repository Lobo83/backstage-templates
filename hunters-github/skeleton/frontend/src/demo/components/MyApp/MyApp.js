import React, { useContext } from 'react'
import '../../../shared/components/layout/header/style.css';
import PageNotFound from '../../pages/error/page-not-found';
import Clock from '../Clock/Clock'
import { KeycloackContext } from '../../../shared/contexts/Keycloak/KeycloakContext.js';

const MyApp = () => {

    const { logout } = useContext(KeycloackContext)
    const { isAuthenticated } = useContext(KeycloackContext)

    if (isAuthenticated) {
        return (
            <div >
                <h1>Welcome</h1>
                <Clock/>
                <p> <button type="button" onClick={() => logout()}> Log Out </button> </p>
            </div>
        )
    }
    else{
        return (
            <div>
                <PageNotFound/>
            </div>
        );
    }
}
export default MyApp