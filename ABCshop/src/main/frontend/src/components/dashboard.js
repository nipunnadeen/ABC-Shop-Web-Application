import React, {Component} from "react";
import axios from "axios";
import {Link, withRouter} from 'react-router-dom';
import { connect } from 'react-redux';
import {ActionCreators} from "../actions/userAction";
// import {ActionCreators} from "../../actions/userAction";

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

    handleSubmit = (e) => {
        e.preventDefault();
        this.setState({submitted: true})
        const {searchData} = this.state;
        searchData.search = e.target.search.value;

        this.sendUserData();
    }

    handleEmailChange = (e) => {
        // this.setState({email: e.target.value});
    }

    handlePasswordChange = (e) => {
        // this.setState({email: e.target.value});
    }

    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <label>Name</label>
                    <input type={"text"} name={"name"} placeholder={"Name"} />
                    <label>Email</label>
                    <input type={"text"} name={"email"} onChange={this.handleEmailChange} placeholder={"Email"}/>
                    <label>Password</label>
                    <input type={"password"} name={"password"} onChange={this.handlePasswordChange}
                           placeholder={"Password"}/>
                    <label>Age</label>
                    <input type={"number"} name={"age"} placeholder={"Age"}/>
                    <label>Address</label>
                    <input type={"text"} name={"address"} placeholder={"Address"}/>
                    <button> Register</button>
                </form>
                <Link to="/login">
                    Already have an account ? Sign in here
                </Link>
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
