import React from "react";
import {connect} from "react-redux";
import GridList from 'material-ui/GridList';
import GridTile from 'material-ui/GridList/GridTile';
import PropTypes from 'prop-types';
import {Link} from 'react-router-dom'

class ListContainer extends React.Component {

    static contextTypes = {
        router: PropTypes.shape({
            history: PropTypes.shape({
                push: PropTypes.func.isRequired,
                replace: PropTypes.func.isRequired,
                createHref: PropTypes.func.isRequired
            }).isRequired
        }).isRequired
    };

    onShowDetails = ({target}) => {
        const {history} = this.context.router;
        history.push(`/product/detail/${target.dataset.id}`);
    };

    render() {
        const {list} = this.props;
        return (
            <GridList cellHeight={200} cols={5}>
                {list.map((item, i) => (
                    <GridTile
                        key={i}
                        title={<div><span>{item.title}</span>
                            <span className="list-item-price-label">{item.price} USD</span>
                        </div>}
                        subtitle={<div>by <b>{item.author}</b></div>}
                    >
                        <img data-id={item.id} onClick={this.onShowDetails} style={{width: "100%"}} src={item.img}/>
                    </GridTile>
                ))}
            </GridList>)
    }
}

export default connect(state => ({
    list: state.list.items,
    isFetching: state.list.isFetching
}))(ListContainer);

