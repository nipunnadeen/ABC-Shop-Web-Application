//
// import React,{userState, userEffect} from "react";
// import axios from "axios";
//
// const UserLogin = () => {
//
//     const [userProfiles, setUserProfiles] = userState([]);
//
//     const getUserData = () => {
//         axios.post("sdfsdfsdfsdfsdf").then(res=> {
//             console.log(res);
//             const data =  res.data;
//             setUserProfiles(data);
//         })
//     }
//
//     userEffect(() => {
//         getUserData();
//     }, []);
//
//
//
//     return <h1>sdfsddfsdfd</h1>
//
// }
//
// function Login() {
//     return (
//         <div className="App">
//             <UserLogin/>
//         </div>
//     );
// }
//
// export default Login;
