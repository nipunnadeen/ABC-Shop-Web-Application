import React, {Component} from "react";
import axios from "axios";
import {Link, withRouter} from 'react-router-dom';
import { connect } from 'react-redux';
import {ActionCreators} from "../actions/userAction";
import ProductInventory from "./product/productInventory";

class Dashboard extends Component {

    state = {
        isUserRegistered: false,
        searchData: {
            search: ''
        },
        submitted: false,
        products: [],
        errorMessage: ""
    };

    componentDidMount() {
        this.getProductData();
    }

    getProductData = () => {
        axios.get("http://localhost:8080/api/product",
            { headers: {"Authorization" : `Bearer ${this.state.searchData}`} }).then(res => {
            if (res.status === 200) {
                this.setState({products: res.data.content});
                this.props.dispatch(ActionCreators.getProducts(this.state.products));
            } else {
                this.setState({errorMessage: "Incorrect Username & Password"})
            }
        })
    }

    render() {
        return (
            <div>
                <ProductInventory/>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        profile: state.data
    }
}

export default connect(mapStateToProps)(withRouter(Dashboard));
// export default Register;
