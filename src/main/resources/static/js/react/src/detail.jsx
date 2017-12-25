import React from "react";
import ReactDOM from "react-dom";
import {Provider} from "react-redux";
import MainList from "./containers/MainList";
import Detail from "./containers/Detail";
import {getMuiTheme, MuiThemeProvider} from 'material-ui/styles'
import configureStore from "./stores/configureStore";
import {deepOrange500} from 'material-ui/styles/colors';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'

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
                    <Detail/>
                </MuiThemeProvider>
            </Provider>,
            document.getElementById('app')
        );
    });

