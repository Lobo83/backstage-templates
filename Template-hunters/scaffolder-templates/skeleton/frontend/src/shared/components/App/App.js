import React from 'react';
import '../layout/header/style.css';
import Layout from '../layout/Layout';
import { init } from '../../actions/init';
import {connect} from "react-redux";

function App(props) {
    return (
        <div>
            <Layout> {props.initialize(true)} </Layout>
        </div>
    );
}

const mapDispatchToProps = (dispatch) => ({
    initialize:  (value) => dispatch(init(value)),
})

export default connect (undefined, mapDispatchToProps) (App);