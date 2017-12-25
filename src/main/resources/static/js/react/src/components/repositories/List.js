import React from "react";


export default class List extends React.Component {

    onItemSelect(index) {
        this.props.onItemSelect(index);
    }

    render() {
        return (
            <ul>
                {this.props.items.map((item, i) =>
                    <li key={item.id}>
                        <h3>
                            <input onChange={() => this.onItemSelect(i)} defaultChecked={item.selected} type="radio" name="repository-item"/>
                            <a href={item.html_url}>{item.name}</a>
                        </h3>
                    </li>
                )}
            </ul>
        )
    }

    static get propTypes() {
        return {
            items: React.PropTypes.arrayOf(React.PropTypes.object),
        }
    }
}
