import { Types } from '../constants/actionTypes';

const initialState = {
    profile: {
        name: '',
        age: 1,
        email: '',
        address: '',
        password: '',
        profileImage: ''
    },

    authTokens: {
        accessToken: '',
        refreshToken: ''
    },

    formSubmitted: false
}

const userReducer = (state = initialState, action) => {
    switch (action.type) {
        case Types.LOGIN:
            return {
                ...state,
                profile: action.payload.user,
                formSubmitted: false // after update user form submittion reset
            }
        case Types.GET_USER_AUTH_TOKENS:
            return {
                ...state,
                authTokens: action.payload.user,
            }
        case Types.ADD_USER:
            return {
                ...state,
                profile: action.payload.user,
                formSubmitted: false // after update user formsubmition reset
            }
        case Types.UPDATE_USER:
            return {
                ...state,
                profile: action.payload.user,
                formSubmitted: false // after update user formsubmition reset
            }
        case Types.UPDATE_PROFILE_PICTURE:
            return {
                ...state,
                profile: {
                    ...state.profile,
                    profileImage: action.payload.image
                }
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

export default userReducer;
