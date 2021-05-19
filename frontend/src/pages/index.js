import React from "react";
import RouteWrapper from "../routes/RouteWrapper";
import {Provider} from "react-redux";
import {store} from "../redux";




function App(){
    return (
        <Provider store={store}>
            <RouteWrapper/>
        </Provider>
    );
}

export default App;