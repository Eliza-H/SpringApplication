import React from "react";
import Add from "../components/add/Add";
import {connect} from "react-redux";

class App extends React.Component {

    render() {
        return (
            <Add/>
        );
    }
}

export default connect()(App);