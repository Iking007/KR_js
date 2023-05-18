import React, { useState, useEffect } from 'react';

function Profile(){

    const exit = () => { window.location.replace("/"); localStorage.clear();}
    return(
        <div>
            <button onClick={exit}>Выйти из аккаунта</button>
        </div>
    )
}

export default Profile;