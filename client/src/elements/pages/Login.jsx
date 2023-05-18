import React, { useState, useEffect } from 'react'
import {useLocation} from "react-router-dom";
import axios, { formToJSON } from "axios";


function Login(){
    const [error, setError] = useState(false);
    const [email, setEmail] = useState([]);
    const [password, setPassword] = useState([]);
    const [query, setQuery] = useState(false);
    const location = useLocation();
    const params = new URLSearchParams(location.search);
    const emailPar = params.get('email');
    const passwordPar = params.get('password');
    //window.location.replace("/")

    useEffect(() => {
      async function postRequest(data = {}){
        let config = {
          method: 'post',
          maxBodyLength: Infinity,
          url: 'http://localhost:8080/login',
          headers: { 
            'Content-Type': 'application/json'
          },
          data: data
        };
        axios.request(config).then(response => {
            console.log(response.data);
            localStorage.token = response.data.token;
            window.location.replace("/")
            //console.log(response.data);
          })
          .catch(error => {
            setError(true)
            if (error.response) {
              // сервер ответил сообщением за пределами 2xx
              console.log(error.response.data);
              console.log(error.response.status);
              console.log(error.response.headers);
            } else if (error.request) {
              // запрос был выполнен, но нет ответа
              console.log(error.request);
            } else {
              // что-то другое случилось
              console.log('Error', error.message);
            }
            console.log(error.config);
          })
      };
      if ("/login" == location.pathname.substring(0,6)){
      postRequest({"email": emailPar, "password": passwordPar})};
    },[query]);
    return(
        <div>
          <form id='my_form'>
            <h1 class="h3 mb-3 fw-normal">Вход</h1>
            <div class="form-floating">
              <input required type="email" name="email" class="form-control" value={email} onInput={e => setEmail(e.target.value)} placeholder="name@example.com"/>
              <label for="floatingInput">Ваша почта</label>
            </div>
            <div class="form-floating">
              <input required type="password" name="password" class="form-control" value={password} onInput={e => setPassword(e.target.value)} minlength="8" placeholder="Password"/>
              <label for="floatingPassword">Пароль</label>
            </div>
            {error == true ? <p>Неверная почта или пароль</p>: null}

            <button class="w-100 btn btn-lg btn-primary" onClick={() => setQuery(!query)}>Войти</button>
          </form>
        </div>
    )
}

export default Login;