import React from 'react';
import Avatar from '@material-ui/core/Avatar';
import CssBaseline from '@material-ui/core/CssBaseline';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import AccountBoxIcon from '@material-ui/icons/AccountBox';
import Typography from '@material-ui/core/Typography';
import Background from '../assets/mb.jpg';
import {Button, Card, CardActions, CardContent, withStyles} from "@material-ui/core";
import UserService from '../services/UserService';
import AuthService from '../auth/AuthService';

const useStyles = theme => ({
    root: {
        height: '100vh',
        minWidth: 275
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
    bullet: {
        display: 'inline-block',
        margin: '0 2px',
        transform: 'scale(0.8)',
    },
    title: {
        fontSize: 14,
    },
    pos: {
        marginBottom: 12,
    },
});

class User extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            user: {},
            currentUser: {},
        };
    }

    render() {
        const {classes} = this.props;
        return (
            <div id="user-page">
                <Grid container component="main" className={classes.root}>
                    <CssBaseline/>
                    <Grid item xs={false} sm={4} md={6} className={classes.image}/>
                    <Grid item xs={12} sm={8} md={6} component={Paper} elevation={6} square>
                        <div className={classes.paper}>
                            <Avatar className={classes.avatar}>
                                <AccountBoxIcon/>
                            </Avatar>
                            <Card className={classes.root} variant="outlined">
                                <CardContent>
                                    <Typography className={classes.title} color="textSecondary" gutterBottom>
                                        My Account
                                    </Typography>
                                    <Typography variant="h5" component="h2">
                                        {this.state.user.firstName} {this.state.user.lastName}
                                    </Typography>
                                    <Typography className={classes.pos} color="textSecondary">
                                        Enrollment status : {this.state.user.enrollmentStatus}
                                    </Typography>
                                    <Typography variant="body2" component="p">
                                        Email : {this.state.user.email}
                                        <br/>
                                        Phone Number : {this.state.user.phoneNumber}
                                        <br/>
                                        Address : {this.state.user.address}
                                        <br/>
                                        Postal Code : {this.state.user.postalCode}
                                        <br/>
                                        City : {this.state.user.city}
                                        <br/>
                                    </Typography>
                                </CardContent>
                                <CardActions>
                                    <Button size="small" onClick={this.props.logoutCallback}>logout</Button>
                                </CardActions>
                            </Card>
                        </div>
                    </Grid>
                </Grid>
            </div>
        );
    }

    componentDidMount() {
        let currentUser = AuthService.getCurrentUser();
        this.setState({currentUser: currentUser});
        UserService.getStudentById(currentUser.email).then((res) => {
            this.setState({user: res.data});
        }, (err) => {
            console.log(err);
        });
    }
}

export default withStyles(useStyles)(User);