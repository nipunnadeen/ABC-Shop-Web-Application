import { Types } from '../constants/actionTypes';

export const ActionCreators = {

    getProducts: (products) => ({ type: Types.GET_PRODUCT, payload: { products } }),

    getSingleProduct: (singleProduct) => ({ type: Types.GET_PRODUCT, payload: { singleProduct } }),

    addProduct: (product) => ({ type: Types.ADD_PRODUCT, payload: { product } }),

    updateProduct: (product) => ({ type: Types.UPDATE_PRODUCT, payload: { product } }),

    formSubmitionStatus: (status) => ({ type: Types.FORM_SUBMITION_STATUS, payload: { status }}),

}
