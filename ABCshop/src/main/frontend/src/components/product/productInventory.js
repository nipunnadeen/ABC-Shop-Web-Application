import React, {Component} from "react";
import axios from "axios";
import {withRouter} from 'react-router-dom';
import { connect } from 'react-redux';
import {ActionCreators} from "../../actions/productAction";
// import {ActionCreators} from "../../actions/userAction";

class ProductInventory extends Component {

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
            } else if(res.status === 500) {
                this.setState({errorMessage: "Something went wrong!"})
            } else if(res.status === 404) {
                this.setState({errorMessage: "Access Denied!"})
            }
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.setState({submitted: true})
        const {searchData} = this.state;
        searchData.search = e.target.search.value;

        this.getProductData();
    }

    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <input type={"text"} name={"search"} placeholder={"Search"} />
                    <button>Search</button>
                </form>
                <div className={"all-products-view"}>
                    {this.props.allProducts.map(productData => (
                        <div className={"product-card"}>
                            <div className={"product-image"}>

                            </div>
                            <div className={"product-details"}>
                                <div className={"product-name"}>
                                    {productData.productName}
                                </div>
                                <div className={"product-description"}>
                                    {productData.productDescription}
                                </div>
                                <div className={"product-price"}>
                                    {productData.productPrice}
                                </div>
                                <div className={"product-qty"}>
                                    {productData.productQuantity}
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        allProducts: state.products.allProducts.content
    }
}

export default connect(mapStateToProps)(withRouter(ProductInventory));
// export default Register;
