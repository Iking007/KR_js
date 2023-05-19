import React, { useState, useEffect } from 'react'
import {Link,useLocation} from "react-router-dom";
import axios from "axios";
import "../blooks/header.css";

function Profile(){
    const [page, setPage] = useState(false);
    const location = useLocation();
    const url = location.pathname;
    //const params = new URLSearchParams(location.search);

    useEffect(() => {
        //console.log(localStorage.getItem('token'))
        async function postRequest(){
          let config = {
            headers: { 
              'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
          };
          axios.get('http://localhost:8080/prof',config).then(response => {
              console.log(response.data);
              localStorage.role = response.data.role;
              setPage(response.data);
              //window.location.replace("/")
              //console.log(response.data);
            })
            .catch(error => {
              console.log(error.config);
            })
        };
        if ("/prof" == location.pathname){
        postRequest()};
      },["/prof" == url ? true: false]);

    const exit = () => { window.location.replace("/"); localStorage.clear();}
    return(
        <div>
            {page.name ? 
              (
                <div>
                    <h2>{page.name}</h2>
                    <h3>Ваша роль: {page.role}</h3>
                    {page.role == "ADMIN" || page.role == "MODER" ? 
                      (<>
                        <Link to="/add/genre" class="my_button">Добавить Жанр</Link>
                        <Link to="/add/author" class="my_button">Добавить Автора</Link>
                        <Link to="/add/book" class="my_button">Добавить Книгу</Link>
                      </>): 
                      null
                    }
                </div>
              ): 
              (
                  <>Loading...</>
              )
            }        
            <button onClick={exit}>Выйти из аккаунта</button>
        </div>
    )
}

export default Profile;