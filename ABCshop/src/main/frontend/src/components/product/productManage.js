import React, {Component} from "react";
import axios from "axios";
import {withRouter} from 'react-router-dom';
import { connect } from 'react-redux';
import {ActionCreators} from "../../actions/userAction";

class ProductManage extends Component {

    state = {
        isProductUpdated: false,
        isProductCreated: false,
        data: {},
        submitted: false,
        errorMessage: ""
    };

    sendProductCreateData = () => {
        axios.post("http://localhost:8080/api/user/createProduct", this.props.productData,
            { headers: {"Authorization" : `Bearer ${this.props.userTokens}`} }).then(res => {
            if (res.status === 200) {
                this.state.isUserRegistered(true);
            } else {
                this.setState({errorMessage: "Incorrect Username & Password"})
            }
        })
    }

    sendProductUpdateData = () => {
        axios.post("http://localhost:8080/api/user/updateProduct", this.props.productData,
            { headers: {"Authorization" : `Bearer ${this.props.userTokens}`} }).then(res => {
            if (res.status === 200) {
                this.state.isUserRegistered(true);
            } else {
                this.setState({errorMessage: "Incorrect Username & Password"})
            }
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.setState({submitted: true})
        this.props.dispatch(ActionCreators.formSubmittionStatus(true))
        const {data} = this.state;
        data.productName = e.target.productName.value;
        data.productDescription = e.target.productDescription.value;
        data.productQuantity = e.target.productQuantity.value;
        data.productPrice = e.target.productPrice.value;
        data.productPromoId = e.target.productPromoId.value;

        if(this.state.isProductCreated === true) {
            this.props.dispatch(ActionCreators.addProduct(data));
            this.sendProductCreateData();
        } else {
            this.props.dispatch(ActionCreators.updateProduct(data));
            this.sendProductUpdateData();
        }
    }

    render() {
        return (
            <div>
                <div>

                </div>
                <form onSubmit={this.handleSubmit}>
                    <label>Product Name</label>
                    <input type={"text"} name={"productName"} placeholder={"Product Name"} />
                    <label>Product Description</label>
                    <input type={"text"} name={"productDescription"}  placeholder={"Product Description"}/>
                    <label>Product Quantity</label>
                    <input type={"number"} name={"productQuantity"} placeholder={"Product Quantity"}/>
                    <label>Product Price</label>
                    <input type={"number"} name={"productPrice"} placeholder={"Product Price"}/>
                    <label>Promotion</label>
                    <input type={"text"} name={"productPromoId"} placeholder={"Address"}/>
                    <button>Submit</button>
                </form>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        productData: state.product.productData,
        userTokens: state.user.authTokens
    }
}

export default connect(mapStateToProps)(withRouter(ProductManage));
