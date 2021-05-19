import React from "react";
import {Route, Router, Switch} from "react-router";
import Login from "../pages/login/Login";
import {default as history} from "./History";
import ScrollIntoView from "../hooks/ScrollIntoView";
import Home from "../pages/home/Home";
import {useSelector} from "react-redux";

function RouteWrapper() {
    const {
        RX_AUTH: {role, isAuthenticated},
    } = useSelector((state) => state);

    return (
        <Router history={history}>
            <Switch>

                <ScrollIntoView>
                    <Route path="/mbe/signin" component={Login}/>
                </ScrollIntoView>

                {role === "ADMIN" && isAuthenticated && (
                    <ScrollIntoView>
                        <Route path="/" component={Home}/>
                    </ScrollIntoView>
                )}

                {/*{role === "PROF" && isAuthenticated && (*/}
                {/*    <ScrollIntoView>*/}
                {/*        <Route path="/" component={Home}/>*/}
                {/*    </ScrollIntoView>*/}
                {/*)}*/}

                {/*{role === "STUDENT" && isAuthenticated && (*/}
                {/*    <ScrollIntoView>*/}
                {/*        <Route path="/" component={Home}/>*/}
                {/*    </ScrollIntoView>*/}
                {/*)}*/}

                {role === "USER" && (
                    <ScrollIntoView>
                        <Route exact path="/mbe/home" component={Home}/>
                    </ScrollIntoView>
                )}

            </Switch>
        </Router>
    );
}

export default RouteWrapper;