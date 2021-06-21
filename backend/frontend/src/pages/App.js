import React from "react";

import AuthService from "../auth/AuthService";
import Signin from "./Signin";
import User from "./User";
import Admin from "./Admin";
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import Signup from "./Signup";
import {Button, Typography, Link} from "@material-ui/core";
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    menuButton: {
        marginRight: theme.spacing(2),
    },
    title: {
        flexGrow: 1,
    },
}));

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

function Navbar(props) {
    const classes = useStyles();
    return (
        <div className={classes.root}>
            <AppBar position="static">
                <Toolbar>
                    <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
                        <MenuIcon />
                    </IconButton>
                    <Typography variant="h6" className={classes.title}>
                        Menu
                    </Typography>
                    <Button color="inherit" onClick={props.logout}>Sign Out</Button>
                </Toolbar>
            </AppBar>
        </div>
    );
}

export default class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            currentUser: {}
        };
    }

    setCurrentUser = (user) => {
        console.log(user);
        this.setState({currentUser: user});
    };

    logOut = () => {
        AuthService.signout();
        this.setState({currentUser: {}});
    };

    render() {
        return (
            <div>
                <BrowserRouter>
                    {(AuthService.isUser() || AuthService.isAdmin()) &&
                    <div ><Navbar logout={this.logOut}/></div>}
                    <Switch>
                        <Route path="/mbe/signin"
                               render={(props) => (<Signin {...props} setCurrentUserCallback={this.setCurrentUser}/>)}/>
                        <Route path="/mbe/signup" component={Signup}/>
                        {AuthService.isUser() &&
                        <Route path="/mbe/user" render={(props) => (<User {...props} logoutCallback={this.logOut}/>)}/>}
                        {AuthService.isAdmin() && <Route path="/mbe/admin" render={(props) => (
                            <Admin {...props} logoutCallback={this.logOut}/>)}/>}
                        <Redirect to="/mbe/signin"/>
                    </Switch>
                    <Copyright/>
                </BrowserRouter>
            </div>
        );
    }
}