import React, {Component} from "react";
import axios from "axios";
import {Link} from 'react-router-dom';

class Register extends Component {

    state = {
        isUserRegistered: false,
        data: {}
    };

    sendUserData = () => {
        axios.post("http://localhost:8080/api/user/register", this.state.data).then(res => {
            if (res.status === 200) {
                this.state.isUserRegistered(true);
            } else {

            }
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        const {data} = this.state;
        data.name = e.target.name.value;
        data.email = e.target.email.value;
        data.password = e.target.password.value;
        data.age = e.target.age.value;
        data.address = e.target.address.value;

        this.setState({data});
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
//             console.log(res);
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

export default Register;
