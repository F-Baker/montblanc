import React from "react";
import {Route, Router, Switch} from "react-router";
import Signin from "../pages/Signin";
import {default as history} from "./History";
import ScrollIntoView from "../hooks/ScrollIntoView";
import Signup from "../pages/Signup";
// import Home from "../pages/home/Home";

function RouteWrapper() {
    return (
        <Router history={history}>
            <Switch>
                {/* <ScrollIntoView>
                    
                </ScrollIntoView> */}
                <Route path="/mbe/signin" component={Signin}/>
                <Route path="/mbe/signup" component={Signup}/>
                <Route path="/mbe/home" component={Home}/>
                <Route path="/mbe/admin" component={Admin}/>
                <Route>
                    <Signin />
                </Route>

            </Switch>
        </Router>
    );
}

export default RouteWrapper;