import React, {Component} from "react";
import axios from "axios";
import {Link, withRouter} from 'react-router-dom';
import {ActionCreators} from "../../actions/userAction";
import {connect} from "react-redux";

class Login extends Component {

    state = {
        isUserLogged: false,
        accessToken: "",
        refreshToken: "",
        data: {},
        errorMessage: "",
        responseData: {}
    };

    sendUserLoginData = () => {
        axios.post("http://localhost:8080/api/user/login", this.props.userLoginData).then(res => {
            if (res.status === 200) {
                this.state.isUserLogged(true);
                const {responseData} = this.state;
                responseData.accessToken = res.data.accessToken;
                responseData.refreshToken = res.data.refreshToken;
                this.props.dispatch(ActionCreators.getUserAuthTokens(responseData));
            } else if(res.status === 403) {
                this.setState({errorMessage: "Incorrect Username & Password"})
            }
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.dispatch(ActionCreators.formSubmittionStatus(true))
        const {data} = this.state;
        data.email = e.target.email.value;
        data.password = e.target.password.value;

        this.setState({data});
        this.props.dispatch(ActionCreators.login(data));
        this.sendUserLoginData();
    }

    // handleSubmit = (e) => {
    //     e.preventDefault();
    //     const {data} = this.state;
    //     data.email = e.target.email.value;
    //     data.password = e.target.password.value;
    //
    //     this.setState({data});
    //     this.sendUserLoginData();
    // }



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

// export default Login;
const mapStateToProps = (state) => {
    console.log(state)
    return {
        userLoginData: state.user.profile
        //auth token connection should be here
    }
}

export default connect(mapStateToProps)(withRouter(Login));
