import React from "react";
import Select from "./Select";
import {connect} from "react-redux";
import {select} from "../../actions/index";

class SelectContainer extends React.Component {

    onSelectChange(selected) {
        this.props.dispatch(select.languageChange(selected));
    }

    render() {
        return <Select onChange={selected => this.onSelectChange(selected)}
                       items={this.props.items}
                       selected={this.props.index}/>
    }

    static get propTypes () {
        return {
            items: React.PropTypes.arrayOf(React.PropTypes.string),
            index: React.PropTypes.number
        }
    }
}

export default connect(state => ({
    items: state.select.items,
    index: state.select.index
}))(SelectContainer);