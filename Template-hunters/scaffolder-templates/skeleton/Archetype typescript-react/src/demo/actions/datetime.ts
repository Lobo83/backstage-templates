import ActionTypes from "."

export const date = (value:any) => ({ type: ActionTypes.DATE, payload: value })

export const time = (value:any) => ({ type: ActionTypes.TIME, payload: value })
