import React from "react";
import {Route, Router, Switch} from "react-router";
import Login from "../pages/login/Login";
import {default as history} from "./History";
import ScrollIntoView from "../hooks/ScrollIntoView";
// import Home from "../pages/home/Home";

function RouteWrapper() {

    return (
        <Router history={history}>
            <Switch>

                <ScrollIntoView>
                    <Route path="/mbe/signin" component={Login}/>
                </ScrollIntoView>


            </Switch>
        </Router>
    );
}

export default RouteWrapper;