import React, {Component} from "react";
import axios from "axios";
import {Link} from 'react-router-dom';

class Login extends Component {

    state = {
        isUserLogged: false,
        accessToken: "",
        refreshToken: "",
        data: {},
        errorMessage: ""
    };

    // sendUserLoginData = () => {
    //     axios.post("http://localhost:8080/api/user/login", this.state.data,
    //         { headers: {"Authorization" : `Bearer ${config.accessToken}`} }).then(res => {
    //         if (res.status === 200) {
    //             this.state.isUserLogged(true);
    //         } else {
    //
    //         }
    //     })
    // }

    sendUserLoginData = () => {
        axios.post("http://localhost:8080/api/user/login", this.state.data).then(res => {
            if (res.status === 200) {
                this.state.isUserLogged(true);
                this.setState({accessToken: res.data.accessToken})
                this.setState({refreshToken: res.data.refreshToken})
            } else if(res.status === 403) {
                this.setState({errorMessage: "Incorrect Username & Password"})
            }
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        const {data} = this.state;
        data.email = e.target.email.value;
        data.password = e.target.password.value;

        this.setState({data});
        this.sendUserLoginData();
    }

    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <label>Email</label>
                    <input type={"text"} name={"email"} placeholder={"Email"}/>
                    <label>Password</label>
                    <input type={"password"} name={"password"} placeholder={"Password"}/>
                    <button> Login</button>
                </form>
                <p>{this.state.errorMessage}</p>
                <Link to="/register">
                        Not have an account ? Sign up here
                </Link>
            </div>
        );
    }
}

export default Login;
