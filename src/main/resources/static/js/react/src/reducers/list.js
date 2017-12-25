import * as actionTypes from "../constants/actionTypes";

var initialState = {
    selectedIndex: -1,
    items: [
        // {
        //     id:1,
        //     img: "https://imagejournal.org/wp-content/uploads/bb-plugin/cache/grave-roses-by-Eric-Huybrechts-on-flickr-panorama.jpg",
        //     title: "xxxx",
        //     author: "zzz"
        // }
    ]
};
export default function (state = initialState, action) {

    switch (action.type) {
        case actionTypes.REQUEST_ITEMS:
            return {
                ...state,
                isFetching: action.isFetching
            };

        case actionTypes.RECEIVE_ITEMS:
            const newState = {
                ...state,
                items: action.list,
                isFetching: action.isFetching
            };

            // if (newState.selectedIndex >= 0) {
            //     item = newState.list[newState.selectedIndex];
            //     newState.list[newState.selectedIndex] = {...item, ...{
            //         selected: true
            //     }};
            // }

            return newState;

        // case actionTypes.SELECT_ITEM:
        //
        //     if (action.repositoryIndex >= 0) {
        //         item = state.list[action.repositoryIndex];
        //         if(state.selectedIndex >= 0) {
        //             state.list[state.selectedIndex] = {...state.list[state.selectedIndex], ...{
        //                 selected: false
        //             }};
        //         }
        //
        //         state.list[action.repositoryIndex] = {...item, ...{
        //             selected: true
        //         }};
        //     }
        //     return {
        //         ...state,
        //         selectedIndex: action.repositoryIndex
        //     };
    }

    return Object.assign({}, initialState, state);
}
