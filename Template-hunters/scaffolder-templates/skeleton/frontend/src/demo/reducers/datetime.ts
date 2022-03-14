import ActionTypes from "../actions"

const initialState = {
    date : "05-07-1997",
    time : "77:77:77"
}

const timedate = (state = initialState, {type, payload}:{type:any, payload:any}) => {
        switch (type) {
            case ActionTypes.DATE :
                return {
                    ...state,
                    date: payload
                }
            case ActionTypes.TIME:
                return {
                    ...state,
                    time: payload
                }
            default : 
                return state
        }
}

export default timedate;