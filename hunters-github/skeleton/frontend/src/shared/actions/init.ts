import ActionTypes from "."

export const init = (value:boolean) => ({ type: ActionTypes.INIT, payload: value })