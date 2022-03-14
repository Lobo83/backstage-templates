import React from 'react';
import {BrowserRouter as Router, Link, Route} from "react-router-dom"

import MyApp from '../../../../demo/components/MyApp/MyApp';
import HolaMundo from '../../../../demo/pages/HolaMundo/HolaMundo'
import UserInfo from '../../../../demo/components/UserInfo/UserInfo';
import '../header/style.css'

export default function Routes() {
    return(
        <>
        <Router>
            <div className='header'>
                <ul>
                    <li>
                        <button type="button"><Link to="/myapp">Altia private section</Link></button> 
                        <button type="button"><Link to="/profile">Profile</Link></button>
                        <button type="button"><Link to="/sayhi">Saluda</Link></button>
                    </li>
                </ul>
            </div>
            <Route path="/myapp"> <MyApp/> </Route>
            <Route path="/profile"> <UserInfo/> </Route>
            <Route path="/sayhi"> <HolaMundo/> </Route>
        </Router>
        </>
    );
}
