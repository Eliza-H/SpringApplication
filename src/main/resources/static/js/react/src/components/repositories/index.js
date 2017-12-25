"use strict";

import React from "react";
import List from "./List";
import {connect} from "react-redux";
import {repositories} from "../../actions/index";


class ListContainer extends React.Component {
    onItemSelect(itemIndex) {
        this.props.dispatch(repositories.selectRepository(itemIndex));

    }

    render() {
        const {list, isFetching} = this.props;
        if (isFetching) {
            return <div> Loading... </div>
        }

        return <List onItemSelect={itemIndex => this.onItemSelect(itemIndex)}
                     items={list}/>
    }

    static get propTypes () {
        return {
            list: React.PropTypes.arrayOf(React.PropTypes.object),
            isFetching: React.PropTypes.bool
        }
    }
}

export default connect(state => ({
    list: state.repositories.list,
    isFetching: state.repositories.isFetching
}))(ListContainer);

