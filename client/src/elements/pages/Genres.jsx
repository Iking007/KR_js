import React, { useState, useEffect } from 'react'
import {Link, useLocation} from "react-router-dom";
import axios from 'axios'

function Genres(){
    const [page, setPage] = useState([]);
    const location = useLocation();
    const url = location.pathname;
    console.log(url);


    useEffect(() => {
    async function fetchData(url) {
        await axios.get("http://localhost:8080" + url).then(response => {
            setPage(response.data);
            //console.log(url);
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
    }
    fetchData(url);
    },["/genres" == url ? true: false]);
    return(
        <div>
        {page.genres ? 
            (
                <div>
                    {page.genres.map(genre => (
                    <Link to={`/books/query?genre=${genre.id}&page=1`}>
                        <h3>{genre.name}</h3>
                    </Link>
                    ))}
                </div>
            ): 
            (
                <>Loading...</>
            )
        }
        </div>            
    )
}

export default Genres;