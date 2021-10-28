import { Types } from '../constants/actionTypes';

export const ActionCreators = {

    getProducts: (product) => ({ type: Types.GET_PRODUCT, payload: { product } }),

    addProduct: (product) => ({ type: Types.ADD_PRODUCT, payload: { product } }),

    updateProduct: (product) => ({ type: Types.UPDATE_PRODUCT, payload: { product } }),

    formSubmitionStatus: (status) => ({ type: Types.FORM_SUBMITION_STATUS, payload: { status }}),

}
