import React, { useContext } from 'react'
import '../../../shared/components/layout/header/style.css';
import PageNotFound from '../../pages/error/page-not-found';
import { KeycloackContext } from '../../../shared/contexts/Keycloak/KeycloakContext.js';
import { visibility } from '../../actions/visiblility';
import {email, name} from '../../actions/Userdata'

import {connect} from "react-redux";
import User from '../User/User'

const UserInfo = (props) => {

    const { isAuthenticated, keycloack} = useContext(KeycloackContext)

    const clickhandler = () => {
        props.changeVisibility(true)
        if(props.email==='?' && props.name==='?'){
            keycloack.loadUserInfo().then( userInfo => {
                props.changeName(userInfo.name);
                props.changeEmail(userInfo.email);
            })
        }
    }
    if(isAuthenticated &&  keycloack && props.isvisible){
        return (
            <div >
                <User/>
                <p> <button type= "button" onClick={() => props.changeVisibility(false)} > Hide my profile </button></p>
            </div>
        )
    }
    else if(!props.isvisible){
        return (
            <div>
                <p> <button type= "button" onClick={clickhandler} > Show my profile </button></p>
            </div>
        );
    }
    else {// When I'm not authenticated
        return (
            <div>
                <PageNotFound/>
            </div>
        );
    }
}
//Recovering data
const mapStatetoProps = (state) => ({
    isvisible: state.demo.visibility.visible,
    name: state.demo.userdata.name,
    email: state.demo.userdata.email,
})

//Dispatching actions
const mapDispatchToProps = (dispatch) => ({
    changeVisibility:  (value) => dispatch(visibility(value)),
    changeName: (value) => dispatch(name(value)),
    changeEmail: (value) => dispatch(email(value)),
})

export default connect (mapStatetoProps, mapDispatchToProps) (UserInfo);
