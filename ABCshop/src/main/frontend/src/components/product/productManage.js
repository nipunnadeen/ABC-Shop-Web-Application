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
        submitted: false
    };

    sendProductCreateData = () => {
        axios.post("http://localhost:8080/api/user/createProd", this.props.userUpdateData,
            { headers: {"Authorization" : `Bearer ${this.props.userTokens}`} }).then(res => {
            if (res.status === 200) {
                this.state.isUserRegistered(true);
            } else {

            }
        })
    }

    sendProductUpdateData = () => {
        axios.post("http://localhost:8080/api/user/updateProduct", this.props.userUpdateData,
            { headers: {"Authorization" : `Bearer ${this.props.userTokens}`} }).then(res => {
            if (res.status === 200) {
                this.state.isUserRegistered(true);
            } else {

            }
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.setState({submitted: true})
        this.props.dispatch(ActionCreators.formSubmittionStatus(true))
        const {data} = this.state;
        data.name = e.target.name.value;
        data.email = e.target.email.value;
        data.password = e.target.password.value;
        data.age = e.target.age.value;
        data.address = e.target.address.value;

        if(this.state.isProductCreated === true) {
            this.props.dispatch(ActionCreators.createProduct(data));
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
                    <label>Name</label>
                    <input type={"text"} name={"name"} placeholder={"Name"} />
                    <label>Email</label>
                    <input type={"text"} name={"email"}  placeholder={"Email"}/>
                    <label>Password</label>
                    <input type={"password"} name={"password"}
                           placeholder={"Password"}/>
                    <label>Age</label>
                    <input type={"number"} name={"age"} placeholder={"Age"}/>
                    <label>Address</label>
                    <input type={"text"} name={"address"} placeholder={"Address"}/>
                    <button> Register</button>
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
