import React from 'react';
import {DataGrid, GridCellParams} from '@material-ui/data-grid';
import Background from '../assets/mb.jpg';
import PeopleIcon from '@material-ui/icons/People';
import {
    Avatar,
    Button,
    CssBaseline,
    Grid,
    Paper,
    Typography,
    withStyles,
    TextField,
    Dialog,
    DialogActions,
    DialogContent,
    DialogContentText,
    DialogTitle,
    Select,
    FormControl,
    MenuItem
} from "@material-ui/core";

import AdminService from "../services/AdminService";

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
    formControl: {
        margin: theme.spacing(1),
        minWidth: 150,
    },
});

class Admin extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            users: [],
            statusList: [],
            dialogOpen: false,
            selectedUserId: undefined,
            selectedEnrollId: 1,
            // loading: false,
            // message: "List is empty !",

        };
    }

    openDialog = (id) => {
        this.setState({dialogOpen: true});
        this.setState({selectedUserId: id});
    };
    handleDialogClose = () => {
        this.setState({dialogOpen: false});
    };
    getEnrollmentStatusById = (statusList, id) => {
        let status = statusList.filter((status) => status.enrollmentStatusId === id);
        return status[0].name;
    };
    handleEnrollment = () => {
        AdminService.changeStatus(this.state.selectedUserId, this.state.selectedEnrollId).then((res) => {
            console.log(res.data);
            this.setState({dialogOpen: false});
            this.setState({
                users: this.state.users.map((u) => {
                    u.enrollmentStatus = this.getEnrollmentStatusById(this.state.statusList, this.state.selectedEnrollId);
                    return u;
                })
            });
        });
    };
    columns = () => {
        return [
            {field: 'id', headerName: 'ID', width: 100},
            {field: 'firstName', headerName: 'First name', width: 150},
            {field: 'lastName', headerName: 'Last name', width: 150},
            {field: 'email', headerName: 'Email', width: 130},
            {field: 'phoneNumber', headerName: 'Phone Number', width: 170},
            {field: 'address', headerName: 'Address', width: 130},
            {field: 'postalCode', headerName: 'Postal Code', width: 170},
            {field: 'city', headerName: 'City', width: 130},
            {field: 'enrollmentStatus', headerName: 'Enrollment Status', width: 170},
            {
                field: 'enrollment', headerName: 'Action', width: 120,
                renderCell: (params) => (
                    <strong>
                        <Button
                            variant="contained"
                            color="primary"
                            size="small"
                            onClick={() => this.openDialog(params.id)}
                            disabled={params.enrollmentStatus === 'ENROLLED'}
                            style={{marginLeft: 16}}
                        >
                            Enroll Student
                        </Button>
                    </strong>),
            },
        ];
    };

    render() {

        const {classes} = this.props;
        return (
            <Grid container component="main" className={classes.root}>
                <CssBaseline/>
                {/* <Grid item xs={false} sm={4} md={4} className={classes.image}/> */}
                <Grid item xs={12} sm={12} md={12} component={Paper} elevation={6} square>
                    <div className={classes.paper}>

                        <Avatar className={classes.avatar}>
                            <PeopleIcon/>
                        </Avatar>

                        <Typography component="h1" variant="h5">
                            Students List
                        </Typography>
                    </div>
                    <div style={{height: 500, width: '100%'}}>
                        <DataGrid rows={this.state.users} columns={this.columns()} pageSize={5}/>
                    </div>
                </Grid>
                <Dialog open={this.state.dialogOpen} onClose={this.handleDialogClose}
                        aria-labelledby="form-dialog-title">
                    <DialogTitle id="form-dialog-title">Change Enrollment Status</DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            Choose Enrollment Status
                        </DialogContentText>
                        <FormControl className={classes.formControl}>
                            <Select
                                labelId="demo-simple-select-label"
                                id="demo-simple-select"
                                value={this.state.selectedEnrollId}
                                onChange={(event) => this.setState({selectedEnrollId: event.target.value})}
                            >
                                {this.state.statusList.map((status) => (<MenuItem id={status.enrollmentStatusId}
                                                                                  value={status.enrollmentStatusId}>{status.name}</MenuItem>))}
                            </Select>
                        </FormControl>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={this.handleDialogClose} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={this.handleEnrollment} color="primary">
                            Change Enrollment Status
                        </Button>
                    </DialogActions>
                </Dialog>
            </Grid>
        );
    }

    componentDidMount() {
        AdminService.getStudents().then((res) => {
            this.setState({users: res.data});
            console.log(res.data);
        }, (err) => {
            console.log(err);
        });
        AdminService.getEnrollmentStatus().then((res) => {
            this.setState({statusList: res.data});
            console.log(res.data);
        }, (err) => {
            console.log(err);
        });
    }
}

export default withStyles(useStyles)(Admin);