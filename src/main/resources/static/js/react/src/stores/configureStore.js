import {createStore, applyMiddleware, compose} from "redux";
import createLogger from 'redux-logger'
import rootReducer from "../reducers";
import thunk from "redux-thunk";
import DevTools from '../containers/DevTools'

export default () => {

    return new Promise(resolve => {
        setTimeout(() => {
            const data = localStorage.getItem("app-state");
            if(!data) {
                return resolve(undefined);
            }

            resolve(JSON.parse(data));
        }, 50);
    })

        .then(initialState => createStore(rootReducer, initialState,  compose(
            applyMiddleware(thunk, createLogger()),
            DevTools.instrument()
        )))
        .then(store => {
            // store.subscribe(() => {
            //     setTimeout(() => {
            //         const state = {
            //             select: {
            //                 index: store.getState().select.index
            //             },
            //             repositories: {
            //                 selectedIndex: store.getState().repositories.selectedIndex
            //             }
            //         };
            //         //it simulates save to server
            //         localStorage.setItem("app-state", JSON.stringify(state));
            //     }, 100);
            // });
            return store;
        })

} ;