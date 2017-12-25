import * as actionTypes from "../constants/actionTypes";

const initialState = {
    items: [],
    index: 0
};

export default function (state = initialState, action) {

    switch (action.type) {

        case actionTypes.RECEIVE_OPTIONS:
            return {
                ...state,
                items: action.items
            };
            break;
        case actionTypes.LANGUAGE_CHANGE:
            return {
                ...state,
                index: action.selectedIndex
            };
            break;
    }


    return Object.assign({}, initialState, state);
}
