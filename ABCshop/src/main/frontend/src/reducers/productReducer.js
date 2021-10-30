import { Types } from '../constants/actionTypes';

const initialState = {
    productsDetails: {
        productName: '',
        productDescription: '',
        productQuantity: 0,
        productPrice: 0.00,
        productPromoId: 0
    },
    product: [],
    allProducts: [],
    formSubmitted: false
}

const productReducer = (state = initialState, action) => {
    switch (action.type) {
        case Types.GET_PRODUCT:
            return {
                ...state,
                product: action.payload.singleProduct,
                formSubmitted: false // after update user formsubmition reset
            }
        case Types.GET_PRODUCTS:
            return {
                ...state,
                allProducts: action.payload.products,
                formSubmitted: false // after update user formsubmition reset
            }
        case Types.ADD_PRODUCT:
            return {
                ...state,
                productsDetails: action.payload.product,
                formSubmitted: false // after update user formsubmition reset
            }
        case Types.UPDATE_PRODUCT:
            return {
                ...state,
                productsDetails: action.payload.product,
                formSubmitted: false // after update user formsubmition reset
            }
        case Types.FORM_SUBMITION_STATUS:
            return {
                ...state,
                formSubmitted: action.payload.status
            }
        default:
            return state;
    }
}

export default productReducer;
