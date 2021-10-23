
import './App.css';
import Register from "./components/user/register";
import Login from "./components/user/login";
import { BrowserRouter, Route, Switch} from "react-router-dom";
import React from "react";
import Dashboard from "./components/dashboard";

function App() {

    // const routes = [
    //     {
    //         path: "/login",
    //         component: Login
    //     },
    //     {
    //         path: "/login",
    //         component: Login
    //     },
    //     // {
    //     //     path: "/l",
    //     //     component: Tacos,
    //     //     routes: [
    //     //         {
    //     //             path: "/tacos/bus",
    //     //             component: Bus
    //     //         },
    //     //         {
    //     //             path: "/tacos/cart",
    //     //             component: Cart
    //     //         }
    //     //     ]
    //     // }
    // ];


  return (
    <div className="App">
      {/*<Register/>*/}
        {/*<header className="App-header">*/}
        {/*    <img src={logo} className="App-logo" alt="logo" />*/}
        {/*    <p>*/}
        {/*        Edit <code>src/App.js</code> and save to reload.*/}
        {/*    </p>*/}
        {/*    <a*/}
        {/*        className="App-link"*/}
        {/*        href="https://reactjs.org"*/}
        {/*        target="_blank"*/}
        {/*        rel="noopener noreferrer"*/}
        {/*    >*/}
        {/*        Learn React*/}
        {/*    </a>*/}
        {/*</header>*/}

        <BrowserRouter>
            <Switch>
                <Route exact path="/home" component={Login} />
                <Route path="/login" component={Login} />
                <Route path = "/register" component={Register} />
                <Route path = "/dashboard" component={Dashboard} />
            </Switch>
        </BrowserRouter>
    </div>
  );
}

export default App;
