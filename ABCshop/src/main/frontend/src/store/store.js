// import { createStore, combineReducers } from 'redux';
import { createStore, combineReducers, compose, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';

import userReducer from '../reducers/userReducer';
import productReducer from "../reducers/productReducer";

const allReducer = combineReducers({
    user: userReducer,
    product: productReducer
});

const configureStore = () => {
    return createStore(
        allReducer,
        compose(applyMiddleware(thunk))
    );
};

export default configureStore;
