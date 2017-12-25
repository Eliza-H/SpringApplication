

export function addProduct(data) {
    return dispatch => {
        var headers = new Headers();
        headers.append("X-CSRF-TOKEN", window.__csrf_token);

        fetch("/product/add", {
            method: "POST",
            body: data,
        });
    }
}

