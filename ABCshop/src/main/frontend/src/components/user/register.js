import React, {Component} from "react";
import axios from "axios";
import {Link, withRouter} from 'react-router-dom';
import { connect } from 'react-redux';
import {ActionCreators} from "../../actions/userAction";

class Register extends Component {

    state = {
        isUserRegistered: false,
        data: {},
        submitted: false
    };

    sendUserData = () => {
        axios.post("http://localhost:8080/api/user/register", this.props.userRegisterData).then(res => {
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

        this.props.dispatch(ActionCreators.addProfile(data));

        // this.setState({data});
        console.log(this.props.userRegisterData)
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


// const UserRegister = () => {
//
//     const [userProfiles, setUserProfiles] = userState([]);
//
//     const sendUserData = () => {
//         axios.post("sdfsdfsdfsdfsdf").then(res => {
//             const data = res.data;
//             setUserProfiles(data);
//         })
//     }
//
//     userEffect(() => {
//         sendUserData();
//     }, []);
//
//
//     return <h1>sdfsddfsdfd</h1>
//
// }
//
// function Register() {
//
//     return (
//         <div className="App">
//             <UserRegister/>
//         </div>
//     );
// }

const mapStateToProps = (state) => {
    return {
        userRegisterData: state.user.profile
    }
}

export default connect(mapStateToProps)(withRouter(Register));
