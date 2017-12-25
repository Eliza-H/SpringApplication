import * as select from "./select"

export function fetchInitialData() {
    return dispatch => {
        dispatch(select.fetchOptions());
    }
}