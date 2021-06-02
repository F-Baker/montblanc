import React from "react";
import RouteWrapper from "../routes/RouteWrapper";
import AuthService from "../services/AuthService";




export default class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            currentUser: undefined
        }
    }

    setCurrentUser = (user) => {
        console.log(user);
        this.setState({ currentUser: user })
    }

    logOut = () => {
        AuthService.logout();
    }

    render() {
        return (
            <div>
                <RouteWrapper/>
            </div>
        );
    }
}