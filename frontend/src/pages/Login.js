import React from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Paper from '@material-ui/core/Paper';
import Box from '@material-ui/core/Box';
import Grid from '@material-ui/core/Grid';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import Background from '../assets/mb.jpg';
import {withStyles} from "@material-ui/core";
import AuthService from "../services/AuthService";

const required = value => {
    if (!value) {
        return (
            <div className="alert alert-danger" role="alert">
                This is required
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

class Login extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            username: "",
            password: "",
            loading: false,
            message: ""
        };
    }

    handleLogin = (e) =>{
        e.preventDefault();

        this.setState({
            message: "",
            loading: true
        });

        this.form.validateAll();

        if (this.checkBtn.context._errors.length === 0) {
            AuthService.login(this.state.username, this.state.password).then(
                (user) => {
                    console.log(user);
                    this.props.setCurrentUser(user);
                    if (user.roles.includes("ROLE_EMPLOYE")) {
                        this.props.history.push("/employe");
                    }
                    else if (user.roles.includes("ROLE_USER")) {
                        this.props.history.push("/home");
                    }
                    // window.location.reload();
                },
                error => {
                    let resMessage = "";
                    if (error.response) {
                        // Request made and server responded with a status code(400, 401,...)
                        if (error.response.status === 400) {
                            resMessage = "Username ou password incorrecte";
                        }
                    }
                    else if (error.request) {
                        // Request made but no response received
                        console.log(error.message);
                        resMessage = "Serveur HS";
                    }
                    else{
                        // Something happened in setting up the request that triggered an Error
                        console.log(error.message);
                        resMessage = "problème de configuration";
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
    }

   render() {
       const { classes } = this.props;
       return (
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

                       <form className={classes.form} noValidate>
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
                           />
                           {/*<FormControlLabel*/}
                           {/*    control={<Checkbox value="remember" color="primary"/>}*/}
                           {/*    label="Remember me"*/}
                           {/*/>*/}

                           <Button
                               type="submit"
                               fullWidth
                               variant="contained"
                               color="primary"
                               className={classes.submit}
                           >
                               Sign In
                           </Button>
                           <Grid container>
                               {/*<Grid item xs>*/}
                               {/*    <Link href="#" variant="body2">*/}
                               {/*        Forgot password?*/}
                               {/*    </Link>*/}
                               {/*</Grid>*/}
                               <Grid item>
                                   <Link href="#" variant="body2">
                                       {"Don't have an account? Sign Up"}
                                   </Link>
                               </Grid>
                           </Grid>
                           <Box mt={5}>
                               <Copyright/>
                           </Box>
                       </form>
                   </div>
               </Grid>
           </Grid>
       );
   }
}


export default withStyles(useStyles)(Login);