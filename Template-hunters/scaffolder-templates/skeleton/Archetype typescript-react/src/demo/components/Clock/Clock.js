import React from 'react'
import '../../../shared/components/layout/header/style.css';
import dayjs from 'dayjs';
import { date, time } from '../../actions/datetime';
import {connect} from "react-redux";

const Clock = (props) => {

    return (
        <div >
            <h2> {props.todaydate} </h2>
            <h2> {props.actualtime} </h2>
            <p> 
                <button type= "button" onClick={() => props.settingdate(dayjs().format('DD-MM-YYYY'))} > Update date </button>
                <button type= "button" onClick={() => props.settinttime(dayjs().format('HH:mm:ss'))} > Update time </button>
            </p>

        </div>
    )
}
const mapStatetoProps = (state) => ({
    todaydate: state.demo.datetime.date,
    actualtime: state.demo.datetime.time,
})

const mapDispatchToProps = (dispatch) => ({
    settingdate:  (value) => dispatch(date(value)),
    settinttime: (value) => dispatch(time(value))
})
export default connect (mapStatetoProps, mapDispatchToProps) (Clock);