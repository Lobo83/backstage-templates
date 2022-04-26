import ActionTypes from "../actions"

const initialState = {
    name : "?",
    email : "?",
}

const userdata = (state = initialState, {type, payload}:{type:any, payload:any}) => {
        switch (type) {
            case ActionTypes.NAME :
                return {
                    ...state,
                    name: payload
                }
            case ActionTypes.EMAIL:
                return {
                    ...state,
                    email: payload
                }
            default : 
                return state
        }
}

export default userdata;