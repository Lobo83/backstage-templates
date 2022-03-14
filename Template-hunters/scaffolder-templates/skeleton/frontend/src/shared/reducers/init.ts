import ActionTypes from "../actions"

const initialState = {
    init : false
}

const init = (state = initialState, {type, payload}:{type:any, payload:any}) => {
        switch (type) {
            case ActionTypes.INIT :
                return {
                    ...state,
                    init: payload
                }
            default : 
                return state
        }
}

export default init;