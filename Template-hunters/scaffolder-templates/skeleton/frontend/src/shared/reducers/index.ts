import { combineReducers } from 'redux';

import init from './init'
import demo from '../../demo/reducers';

//Here we put all the reducers related to module Shared.
const shared = combineReducers({
  init,
})

//Here we put all the reducers related to the different modules
const rootReducerShared = combineReducers({
  demo ,
  shared
});

export default rootReducerShared;