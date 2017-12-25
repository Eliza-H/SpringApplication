import React from "react";
import List from "../components/list/ListContainer";
import {connect} from "react-redux";
import {list} from "../actions";

class MainList extends React.Component {

    componentDidMount() {
        this.props.dispatch(list.fetchList());
    }

    render() {
        return (
            <div>
                <div><h3>Latest products:</h3></div>
                <List/>
            </div>
        );
    }
}

export default connect()(MainList);