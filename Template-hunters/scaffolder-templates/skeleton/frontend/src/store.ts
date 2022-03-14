import { createStore, applyMiddleware, compose } from 'redux';
import thunkMiddleware from 'redux-thunk';
import { composeWithDevTools } from 'redux-devtools-extension';

import reducer from './shared/reducers';

const defaultMiddlewares = [
  thunkMiddleware,
];

const composedMiddlewares = () =>
  process.env.NODE_ENV === 'development'
    ? composeWithDevTools(compose(applyMiddleware(...defaultMiddlewares)))
    : compose(applyMiddleware(...defaultMiddlewares))

  const store = createStore(
    reducer,
    composedMiddlewares(),
  );

export default store
