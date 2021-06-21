import React from 'react';
import Avatar from '@material-ui/core/Avatar';
import CssBaseline from '@material-ui/core/CssBaseline';
import Link from '@material-ui/core/Link';
import Paper from '@material-ui/core/Paper';
import Box from '@material-ui/core/Box';
import Grid from '@material-ui/core/Grid';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import Background from '../assets/mb.jpg';
import {TextField, withStyles} from "@material-ui/core";

import AuthService from "../auth/AuthService";

import CheckButton from "react-validation/build/button";
import Form from "react-validation/build/form";
import Button from "@material-ui/core/Button";

const required = value => {
    if (!value) {
        return (
            <div className="alert alert-danger" role="alert" id="alert-email-password">
                Please enter your username and password
            </div>
        );
    }
};

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

//inline styling for the page
const useStyles = theme => ({
    root: {
        height: '100vh',
    },
    image: {
        backgroundImage: `url(${Background})`,
        backgroundRepeat: 'no-repeat',
        backgroundColor:
            theme.palette.type === 'light' ? theme.palette.grey[50] : theme.palette.grey[900],
        backgroundSize: 'cover',
        backgroundPosition: 'right',
    },
    paper: {
        margin: theme.spacing(8, 4),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.primary.dark,
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing(1),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
});

class Signin extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            email: "",
            password: "",
            loading: false,
            message: ""
        };
    }

    onChangeEmail = (e) => {
        this.setState({
            email: e.target.value
        });
    };

    onChangePassword = (e) => {
        this.setState({
            password: e.target.value
        });
    };

    handleLogin = (e) => {
        e.preventDefault();

        this.setState({
            message: "",
            loading: true
        });

        this.form.validateAll();

        if (this.checkBtn.context._errors.length === 0) {
            AuthService.signin(this.state.email, this.state.password).then(
                (user) => {
                    console.log(user);
                    this.props.setCurrentUserCallback(user);
                    if (user.roles.includes("ROLE_ADMIN")) {
                        this.props.history.push("/mbe/admin");
                    } else if (user.roles.includes("ROLE_STUDENT")) {
                        this.props.history.push("/mbe/user");
                    }
                    // window.location.reload();
                },
                error => {
                    let resMessage = "";
                    if (error.response) {
                        // Request made and server responded with a status code(400, 401,...)
                        if (error.response.status === 400) {
                            resMessage = "Email or password incorrect";
                        }
                    } else if (error.request) {
                        // Request made but no response received
                        console.log(error.message);
                        resMessage = "the server might be offline";
                    } else {
                        // Something happened in setting up the request that triggered an Error
                        console.log(error.message);
                        resMessage = "something weird happened with the configurations";
                    }
                    this.setState({
                        loading: false,
                        message: resMessage
                    });
                }
            );
        } else {
            this.setState({
                loading: false
            });
        }
    };

    render() {
        const {classes} = this.props;
        return (
            <div id="signin-page">
                <Grid container component="main" className={classes.root}>
                    <CssBaseline/>
                    <Grid item xs={false} sm={4} md={7} className={classes.image}/>
                    <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
                        <div className={classes.paper}>

                            <Avatar className={classes.avatar}>
                                <LockOutlinedIcon/>
                            </Avatar>

                            <Typography component="h1" variant="h5">
                                Sign in
                            </Typography>

                            <Form
                                className={classes.form}
                                onSubmit={this.handleLogin}
                                ref={c => {
                                    this.form = c;
                                }}>

                                <TextField
                                    variant="outlined"
                                    margin="normal"
                                    required
                                    fullWidth
                                    id="email"
                                    label="Email Address"
                                    name="email"
                                    autoComplete="email"
                                    autoFocus
                                    value={this.state.email}
                                    onChange={this.onChangeEmail}
                                    validations={[required]}
                                />


                                <TextField
                                    variant="outlined"
                                    margin="normal"
                                    required
                                    fullWidth
                                    name="password"
                                    label="Password"
                                    type="password"
                                    id="password"
                                    autoComplete="current-password"
                                    value={this.state.password}
                                    onChange={this.onChangePassword}
                                    validations={[required]}
                                />

                                <Button
                                    type="submit"
                                    fullWidth
                                    variant="contained"
                                    color="primary"
                                    className={classes.submit}
                                >
                                    Sign In
                                </Button>

                                {this.state.message && (
                                    <div className="form-group">
                                        <div className="alert alert-danger" role="alert" id="alert">
                                            {this.state.message}
                                        </div>
                                    </div>
                                )}
                                <CheckButton
                                    style={{display: "none"}}
                                    ref={c => {
                                        this.checkBtn = c;
                                    }}
                                />

                                <Grid container>
                                    <Grid item>
                                        <Link href="/mbe/signup" variant="body2">
                                            {"Don't have an account? Sign Up"}
                                        </Link>
                                    </Grid>
                                </Grid>
                                <Box mt={5}>
                                    <Copyright/>
                                </Box>
                            </Form>
                        </div>
                    </Grid>
                </Grid>
            </div>
        );
    }
}

export default withStyles(useStyles)(Signin);