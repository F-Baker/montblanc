import React from "react";

import AuthService from "../auth/AuthService";
import Signin from "./Signin";
import User from "./User";
import Admin from "./Admin";
import { BrowserRouter, Redirect, Route, Switch } from "react-router-dom";
import Signup from "./Signup";
import { Button, Typography, Link } from "@material-ui/core";

//neat little bidule for showing the site date
function Copyright() {
    return (
        <Typography variant="body2" color="textSecondary" align="center">

            <Link color="inherit" href="http://localhost:3000/mbe/signin">
                MBE
            </Link>{' '}
            {new Date().getFullYear()}
        </Typography>
    );
}
function LogoutButton(props) {
    return (
        <Button size="small" onClick={props.logout}>logout</Button>
    );
}

export default class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            currentUser: {}
        }
    }

    setCurrentUser = (user) => {
        console.log(user);
        this.setState({ currentUser: user })
    }

    logOut = () => {
        AuthService.signout();
        this.setState({ currentUser: {} });
    }

    render() {
        return (
            <div>
                <BrowserRouter>
                    {(AuthService.isUser() || AuthService.isAdmin()) && <div style={{textAlign: "right"}}><LogoutButton logout={this.logOut}/></div>}
                    <Switch>
                        <Route path="/mbe/signin" render={(props)=> (<Signin {...props} setCurrentUserCallback={this.setCurrentUser} />)}/>
                        <Route path="/mbe/signup" component={Signup}/>
                        {AuthService.isUser() && <Route path="/mbe/user" render={(props)=> (<User {...props} logoutCallback={this.logOut} />)}/>}
                        {AuthService.isAdmin() && <Route path="/mbe/admin" render={(props)=> (<Admin {...props} logoutCallback={this.logOut} />)}/>}
                        <Redirect to="/mbe/signin"/>
                        {/* <Route>
                            <Signin {...this.props} setCurrentUserCallback={this.setCurrentUser}/>
                        </Route> */}
                    </Switch>
                    <Copyright />
                    
                </BrowserRouter>
            </div>
        );
    }
}