import ActionTypes from "."

export const name = (value:any) => ({ type: ActionTypes.NAME, payload: value })

export const email = (value:any) => ({ type: ActionTypes.EMAIL, payload: value })
