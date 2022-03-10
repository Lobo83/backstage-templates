import { combineReducers } from 'redux';

import datetime from './datetime'
import visibility from './visibility'
import userdata from './userdata'

//Here we will put all the reducers related to module Demo.
const rootReducerDemo = combineReducers({
  datetime,
  visibility,
  userdata,
});

export default rootReducerDemo;