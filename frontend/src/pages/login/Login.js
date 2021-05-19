import React from "react";
import ContainerRoot from "../../components/containers/ContainerRoot";
import LoginForm from "../../components/forms/LoginForm";

function Login(){
    return(
      <ContainerRoot>
          <div className="auth_wrapper">
              <LoginForm/>

          </div>
      </ContainerRoot>
    );
}

export default Login;