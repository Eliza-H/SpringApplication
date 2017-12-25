import React from "react";
import Dropzone from 'react-dropzone';
import {product} from "../../actions/index"
import RaisedButton from 'material-ui/RaisedButton';
import {connect} from "react-redux";

class Add extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            title: "",
            price: null,
            description: "",
            file: null,
            isDropped: false
        }
    }

    addProduct = () => {
        const formData = new FormData();
        formData.append("title", this.state.title);
        formData.append("description", this.state.description);
        formData.append("price", this.state.price);
        formData.append("image", this.state.file);
        formData.append("_csrf", window.__csrf_token);
        this.props.dispatch(product.addProduct(formData));
    }

    onDrop = (files) => {
        this.setState((prev) => {
            return {...prev, ...{file: files [0]}}
        })
        if (files.length > 0) {
            this.setState(prev => {
                return {...prev, ...{isDropped: true}};
            })
        }
    };

    onChangeTitle = ({target}) => {
        this.setState({
            title: target.value
        })
    };
    onChangePrice = ({target}) => {
        this.setState({
            price: parseInt(target.value)
        })
    };

    onChangeDescription = ({target}) => {
        this.setState({
            description: target.value
        })
    };

    render() {
        const product = this.props;
        return (
            <div className="main-container">
                <div className="main-container-item">
                    <Dropzone accept="image/*" className={`dropzone ${this.state.isDropped ? "" : "dropzone-wrapper"}`}
                              onDrop={this.onDrop}>
                        {({isDragActive, isDragReject, acceptedFiles, rejectedFiles}) => {
                            if (rejectedFiles.length) {
                                return (<div className="drop-info-container">
                                    <div className="drag-text">Invalid file type</div>
                                </div>);
                            }

                            if (isDragActive) {
                                return (<div className="drop-info-container">
                                    <div className="drag-text">Drop file</div>
                                </div>);
                            }

                            if (acceptedFiles.length) {
                                return <img src={acceptedFiles[0].preview}/>;
                            }


                            return (<div className="drop-info-container">
                                <div className="drag-text">Drag image here</div>
                                <div className="drag-text">or..</div>
                                <RaisedButton label="Select a file from your computer" primary={true}/>
                            </div>)
                        }}
                    </Dropzone>
                </div>
                <div className="main-container-item input-container">
                    <div className="group">
                        <input type="text" onChange={this.onChangeTitle}
                               value={this.state.title}
                               className={this.state.title ? "used" : ""}
                               name="login" required="true"/>
                        <span className="highlight"></span>
                        <span className="bar"></span>
                        <label>Tittle</label>
                    </div>
                </div>
                <div className="main-container-item">
                    <div className="group">
                        <input type="number" onChange={this.onChangePrice}
                               className={this.state.price ? "used" : ""}
                               name="login" required="true"/>
                        <span className="highlight"></span>
                        <span className="bar"></span>
                        <label>Price</label>
                    </div>
                    <div className="currency">USD</div>
                </div>

                <div className="main-container-item description-container">
                    <div className="group">
                        <textarea onChange={this.onChangeDescription} name="description"
                                  className={this.state.description ? "used" : ""}
                                  cols="30" rows="10" value={this.state.description}/>
                        <span className="highlight"></span>
                        <span className="bar"></span>
                        <label>Description</label>
                    </div>
                </div>
                <div className="right-info-bar">
                    <a onClick={this.addProduct} className="add-button">
                        <span>Add product</span>
                    </a>
                </div>
            </div>
        )
    }
}


export default connect()(Add);