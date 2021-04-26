import React from "react";
import MbBackground from "./../assets/mb.jpg"

function App(){
    return (
        <div style={{
            backgroundImage: `url(${MbBackground})`
        }}>
            Test
        </div>
    );
}

export default App;