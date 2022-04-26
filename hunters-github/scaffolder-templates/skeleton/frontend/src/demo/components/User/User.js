import React, { useContext } from 'react'
import '../../../shared/components/layout/header/style.css';
import PageNotFound from '../../pages/error/page-not-found';
import { KeycloackContext } from '../../../shared/contexts/Keycloak/KeycloakContext.js';
import {email, name} from '../../actions/Userdata'
import {connect} from "react-redux";

const User = (props) => {

    const { isAuthenticated, keycloack} = useContext(KeycloackContext)

    if(isAuthenticated && keycloack){
        return (
            <div >
                <p>Name: {props.name}</p>
                <p>Email: {props.email}</p>
            </div>
        )
    }
    else {// When I'm not authenticated
        return (
            <div>
                <PageNotFound/>
            </div>
        );
    }
}
//Recover data from store
const mapStatetoProps = (state) => ({
    name: state.demo.userdata.name,
    email: state.demo.userdata.email,
})

//Dispatch action
const mapDispatchToProps = (dispatch) => ({
    changeName: (value) => dispatch(name(value)),
    changeEmail: (value) => dispatch(email(value)),
})

export default connect (mapStatetoProps, mapDispatchToProps) (User);
