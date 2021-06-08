import React from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container';
import {withStyles} from "@material-ui/core";
import AuthService from '../auth/AuthService';

import {isEmail} from "validator";

const required = value => {
    return value.trim().length > 0;
};
const isPassword = value => {
    //must have a capital, number, one of these @$!%*?&, and be 8 chars long
    return value.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/);
};

function Copyright() {
    return (
        <Typography variant="body2" color="textSecondary" align="center">
            {'Copyright © '}
            <Link color="inherit" href="https://material-ui.com/">
                Your Website
            </Link>{' '}
            {new Date().getFullYear()}
            {'.'}
        </Typography>
    );
}

const useStyles = theme => ({
    paper: {
        marginTop: theme.spacing(8),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing(3),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
});

class Signup extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            firstName: "",
            firstNameErrorText: "",
            lastName: "",
            lastNameErrorText: "",
            address: "",
            postalCode: "",
            city: "",
            phoneNumber: "",
            email: "",
            emailErrorText: "",
            password: "",
            passwordErrorText: "",
            loading: false,
            message: "",
            formValid: true
        };
    }

    handleFNameChange = (e) => {
        this.setState({
            firstName: e.target.value,
            firstNameErrorText: required(e.target.value) ? "" : "your first name is required!",
        });
    };

    handleChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        });
    };

    handleLNameChange = (e) => {
        this.setState({
            lastName: e.target.value,
            lastNameErrorText: required(e.target.value) ? "" : "your last name is required!",
        });
    };

    handleEmailChange = (e) => {
        this.setState({
            email: e.target.value,
            emailErrorText: isEmail(e.target.value) ? "" : "please enter a valid email",
        });
    };

    handlePasswordChange = (e) => {
        this.setState({
            password: e.target.value,
            passwordErrorText: isPassword(e.target.value) ? "" : "please enter a password with a capital letter, number, and one of these special characters: @$!%*?&",
        });
    };

    isValid = (user) => {
        let valid = true;
        if (!(required(user.firstName) && required(user.lastName))) valid = false;
        if (!isEmail(user.email)) valid = false;
        if (!isPassword(user.password)) valid = false;
        return valid;
    };

    handleSignup = (e) => {
        e.preventDefault();

        this.setState({
            message: "",
            loading: true
        });

        const {firstName, lastName, email, password, phoneNumber, address, postalCode, city} = this.state;

        const user = {
            firstName, lastName, email, password, phoneNumber, address, postalCode, city
        };

        if (this.isValid(user)) {
            AuthService.register(user).then(
                (res) => {
                    console.log(res);
                    const location = {
                        pathname: '/login',
                        state: {fromRegister: true, email: email}
                    };
                    this.props.history.replace(location);
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
                loading: false,
                formValid: false
            });
        }
    };

    render() {

        const {classes} = this.props;

        return (
            <Container component="main" maxWidth="xs">
                <CssBaseline/>
                <div className={classes.paper}>

                    <Avatar className={classes.avatar}>
                        <LockOutlinedIcon/>
                    </Avatar>

                    <Typography component="h1" variant="h5">
                        Sign up
                    </Typography>

                    <form className={classes.form}
                          noValidate
                          onSubmit={this.handleSignup}>
                        <Grid container spacing={2}>
                            <Grid item xs={12} sm={6}>
                                <TextField
                                    autoComplete="fname"
                                    name="firstName"
                                    variant="outlined"
                                    error={this.state.firstNameErrorText.length > 0}
                                    helperText={this.state.firstNameErrorText}
                                    required
                                    fullWidth
                                    id="firstName"
                                    label="First Name"
                                    autoFocus
                                    onChange={this.handleFNameChange}

                                />
                            </Grid>

                            <Grid item xs={12} sm={6}>
                                <TextField
                                    variant="outlined"
                                    required
                                    fullWidth
                                    error={this.state.lastNameErrorText.length > 0}
                                    helperText={this.state.lastNameErrorText}
                                    id="lastName"
                                    label="Last Name"
                                    name="lastName"
                                    autoComplete="lname"
                                    onChange={this.handleLNameChange}
                                />
                            </Grid>

                            <Grid item xs={12}>
                                <TextField
                                    variant="outlined"
                                    required
                                    fullWidth
                                    error={this.state.emailErrorText.length > 0}
                                    helperText={this.state.emailErrorText}
                                    id="email"
                                    label="Email Address"
                                    name="email"
                                    autoComplete="email"
                                    onChange={this.handleEmailChange}
                                />
                            </Grid>

                            <Grid item xs={12}>
                                <TextField
                                    variant="outlined"
                                    required
                                    fullWidth
                                    error={this.state.passwordErrorText.length > 0}
                                    helperText={this.state.passwordErrorText}
                                    name="password"
                                    label="Password"
                                    type="password"
                                    id="password"
                                    autoComplete="current-password"
                                    onChange={this.handlePasswordChange}
                                />
                            </Grid>

                            <Grid item xs={12} sm={6}>
                                <TextField
                                    autoComplete="address"
                                    name="address"
                                    variant="outlined"
                                    required
                                    fullWidth
                                    id="address"
                                    label="Address"
                                    onChange={this.handleChange}
                                />
                            </Grid>

                            <Grid item xs={12} sm={6}>
                                <TextField
                                    variant="outlined"
                                    required
                                    fullWidth
                                    id="postalCode"
                                    label="Postal code"
                                    name="postalCode"
                                    autoComplete="postalcode"
                                    onChange={this.handleChange}
                                />
                            </Grid>

                            <Grid item xs={12} sm={6}>
                                <TextField
                                    autoComplete="city"
                                    name="city"
                                    variant="outlined"
                                    required
                                    fullWidth
                                    id="city"
                                    label="City"
                                    onChange={this.handleChange}
                                />
                            </Grid>

                            <Grid item xs={12} sm={6}>
                                <TextField
                                    variant="outlined"
                                    required
                                    fullWidth
                                    id="phoneNumber"
                                    label="Phone Number"
                                    name="phoneNumber"
                                    autoComplete="phoneNumber"
                                    onChange={this.handleChange}
                                />
                            </Grid>
                        </Grid>

                        <Grid item xs={12}>
                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                color="primary"
                                className={classes.submit}
                                disabled={this.state.loading}
                            >
                                {this.state.loading && (
                                    <span className="spinner-border spinner-border-sm"/>
                                )}
                                <span>Sign Up</span>
                            </Button>
                        </Grid>

                        <Grid container justify="flex-end">
                            <Grid item>
                                <Link href="/mbe/signin" variant="body2">
                                    Already have an account? Sign in
                                </Link>
                            </Grid>
                        </Grid>
                        {!this.state.formValid && (
                            <div className="form-group">
                                <div className="alert alert-danger" role="alert">
                                    Please correct your information.
                                </div>
                            </div>
                        )}
                    </form>
                </div>
                <Box mt={5}>
                    <Copyright/>
                </Box>
            </Container>
        );
    }
}

export default withStyles(useStyles)(Signup);