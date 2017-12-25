import React from "react";

export default class extends React.Component {

    onChange({target}) {
        const index = this.props.items.indexOf(target.value);
        this.props.onChange(index);
    }

    render() {
        const items = this.props.items;
        const selectedIndex = this.props.selected;
        return (
            <select onChange={e => this.onChange(e)} value={items[selectedIndex]}>
                {items.map((item, i) => (
                    <option key={i} value={item}>{item}</option>
                ))}
            </select>
        );
    }

    static get propTypes () {
        return {
            items: React.PropTypes.arrayOf(React.PropTypes.string),
            selected: React.PropTypes.number,
            onChange: React.PropTypes.func
        }
    }
}