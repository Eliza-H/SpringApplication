import React from "react";
import ReactDOM from "react-dom";
import {Provider} from "react-redux";
import Add from "./containers/Add";
import {getMuiTheme, MuiThemeProvider} from 'material-ui/styles'
import configureStore from "./stores/configureStore";
import {deepOrange500} from 'material-ui/styles/colors';

const muiTheme = getMuiTheme({
    palette: {
        accent1Color: deepOrange500
    }
});

configureStore()
    .then(store => {
        ReactDOM.render(
            <Provider store={store}>
                <MuiThemeProvider muiTheme={muiTheme}>
                    <Add/>
                </MuiThemeProvider>
            </Provider>,
            document.getElementById('app')
        );
    });

