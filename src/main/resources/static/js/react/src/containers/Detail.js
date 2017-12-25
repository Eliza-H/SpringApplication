import React from "react";
import Detail from "../components/detail/Detail";
import {connect} from "react-redux";

class App extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            product: null
        };
    }

    componentDidMount() {
        fetch(`/product/${this.props.match.params.id}`).then(data => data.json())
            .then(data => {
            this.setState({product: data})
        });
    }

    render() {
        if(!this.state.product) {
            return <h1>Loading...</h1>
        }

        return (
            <Detail {...this.state.product} />
        );
    }
}

export default connect()(App);