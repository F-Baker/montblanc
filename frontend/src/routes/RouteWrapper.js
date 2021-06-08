import React from "react";
import {Route, Router, Switch} from "react-router";
import Signin from "../pages/Signin";
import {default as history} from "./History";
import Signup from "../pages/Signup";
import Admin from "../pages/Admin";
import User from "../pages/User";


function RouteWrapper() {
    return (
        <Router history={history}>
            <Switch>
                <Route path="/mbe/signin" component={Signin}/>
                <Route path="/mbe/signup" component={Signup}/>
                <Route path="/mbe/user" component={User}/>
                <Route path="/mbe/admin" component={Admin}/>
                <Route>
                    <Signin/>
                </Route>
            </Switch>
        </Router>
    );
}

export default RouteWrapper;