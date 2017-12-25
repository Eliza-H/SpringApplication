import React from "react";
import moment from "moment";

export default class List extends React.Component {
    sendMessage(id) {
        const message = prompt("Please writes you message");
    }

    render() {
        const product = this.props;
        return (
            <div className="main-container">
                <div className="main-container-item">
                    <div>
                        <img src={product.img}/>
                    </div>
                </div>
                <div className="main-container-item title-container">
                    <h1>
                        {product.title}
                    </h1>
                </div>
                <div className="main-container-item description-container">
                    <span>Added: {moment(product.creationDate).format('MMMM Do YYYY, h:mm:ss')}</span>
                </div>
                <div className="main-container-item description-container">{product.description}</div>
                <div className="right-info-bar">
                    <div className="price-label">
                        <strong className="xxxx-large arranged">{product.price} USD</strong>
                    </div>
                    <a onClick={() => this.sendMessage(product.id)} className="message-button">
                        <span>Send message</span>
                    </a>
                </div>
            </div>
        )
    }
}
