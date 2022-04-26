import ActionTypes from "../actions"

const initialState = {
    visible : false
}

const date = (state = initialState, {type, payload}:{type:any, payload:any}) => {
        switch (type) {
            case ActionTypes.VISIBLE :
                return {
                    ...state,
                    visible: payload
                }
            default : 
                return state
        }
}
export default date;