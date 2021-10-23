import React, {Component} from "react";
import axios from "axios";
import {withRouter} from 'react-router-dom';
import { connect } from 'react-redux';
import {ActionCreators} from "../../actions/userAction";

class Profile extends Component {

    state = {
        isUserUpdated: false,
        data: {},
        submitted: false
    };

    sendUserData = () => {
        axios.post("http://localhost:8080/api/user/update", this.props.userUpdateData,
            { headers: {"Authorization" : `Bearer ${this.props.userTokens}`} }).then(res => {
            if (res.status === 200) {
                this.state.isUserRegistered(true);
            } else {

            }
        })
    }

    sendUserProfileImage = () => {
        axios.post("http://localhost:8080/api/user/updateProfile", this.props.userUpdateData,
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

        this.props.dispatch(ActionCreators.updateProfile(data));

        // this.setState({data});
        console.log(this.props.userRegisterData)
        this.sendUserData();
    }

    handleProfileImage = (e) => {
        e.preventDefault();
        const {data} = this.state;

        this.props.dispatch(ActionCreators.updateProfileImage(data));

        this.sendUserProfileImage();
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
                <div>

                </div>
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
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        userUpdateData: state.user.profile,
        userTokens: state.user.authTokens
    }
}

export default connect(mapStateToProps)(withRouter(Profile));
