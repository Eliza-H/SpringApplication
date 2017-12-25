import {REQUEST_ITEMS, RECEIVE_ITEMS} from "../constants/actionTypes";

export function requestItems() {
    return {
        type: REQUEST_ITEMS,
        isFetching: true
    }
}

export function receiveItems(list) {
    return {
        type: RECEIVE_ITEMS,
        list,
        isFetching: false,
    }
}

export function fetchList() {
    return (dispatch) => {
        dispatch(requestItems());
        fetch("/product/list")
            .then(res => res.json())
            .then(data => {
                dispatch(receiveItems(data));
            });
    }
}

export function fetchCreatedByUserList() {
    return (dispatch) => {
        dispatch(requestItems());
        fetch("/product/list")
            .then(res => res.json())
            .then(data => {
                dispatch(receiveItems(data));
            });
    }
}