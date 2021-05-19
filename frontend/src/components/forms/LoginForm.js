import React, {useEffect, useState} from "react";
import {HiOutlineArrowNarrowRight} from "react-icons/hi";
import {useDispatch, useSelector} from "react-redux";
import RouterHistory from "../../routes/History";
import {SignIn} from "../../services/AuthService";
import InputBox from "../inputs/InputBox";


function LoginForm() {

    const dispatch = useDispatch();


    const {RX_AUTH: {signInStart, signInSuccess, signInFail}} = useSelector(state => state);

    const onSubmit = () => {
        dispatch(SignIn(request));
    };

    const [request, setrequest] = useState({
        email: "",
        password: "",
    });

    const setEmailValue = (value) => {
        setrequest({...request, email: value});
    };

    const setPassword = (value) => {
        setrequest({...request, password: value});
    };


    return (
        <div className="form_login">
            <h1>LOG IN</h1>
            {/*<div className="forget_link">Forgot Your Password?</div>*/}

            <InputBox
                name={"Email"}
                inputValue={request.email}
                setInputValue={setEmailValue}
                InputType={"email"}
                // errorMessage={"Please enter a valid e-mail address"}
                labelText={"Email"}
                required={true}
            />
            <InputBox
                name={"password"}
                inputValue={request.password}
                setInputValue={setPassword}
                InputType={"password"}
                errorMessage={"Incorrect email/password – please check and retry"}
                labelText={"Password"}
                required={true}
            />

            <div className="btn_wrapper">
                <button
                    type="button"
                    onClick={(e) => {
                        onSubmit();
                    }}
                    className="btn_primary"
                > {signInStart ? <div className="loader"/> :
                    <> <span>LOG IN </span>
                        <HiOutlineArrowNarrowRight size={25}/></>
                }
                </button>
            </div>

        </div>
    );
}

export default LoginForm;
