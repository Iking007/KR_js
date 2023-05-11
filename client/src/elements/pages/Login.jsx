import React, { useState, useEffect } from 'react'
import axios from "axios";


function Login(){
    const [page, setPage] = useState([]);
    var form = { username: '', password: '' }
    async function postRequest(form){
        axios.post("http://localhost:8080/login?username=" + form.username + "&password=" + form.password).then(response => {
            setPage(response.data);
            console.log(form.email + " " + form.password);
          })
          .catch(error => {
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
    return(
        <div>
            <form>
            <h1 class="h3 mb-3 fw-normal">Вход</h1>
            <div class="form-floating">
                <input required type="email" name="email" class="form-control" value={form.email} placeholder="name@example.com"/>
                <label for="floatingInput">Ваша почта</label>
            </div>
            <div class="form-floating">
                <input required type="password" name="password" class="form-control" value={form.password} minlength="8" placeholder="Password"/>
                <label for="floatingPassword">Пароль</label>
            </div>
            {page.error == true ? <p>Неверная почта или пароль</p>: null}

            <button class="w-100 btn btn-lg btn-primary" type="submit" onClick={postRequest(form)}>Войти</button>
        </form>
        </div>
    )
}

export default Login;