import { createStore, combineReducers, compose, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';

import userReducer from '../reducers/userReducer';

const allReducer = combineReducers({
    user: userReducer
});

const configureStore = () => {
    return createStore(
        allReducer,
        compose(applyMiddleware(thunk))
    );
};

export default configureStore;
