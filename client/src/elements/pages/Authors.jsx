import React, { useState, useEffect } from 'react'
import {Link, useLocation} from "react-router-dom";
import axios from 'axios'

function Authors(){
    const [page, setPage] = useState([]);
    const location = useLocation();
    const url = location.pathname;


    useEffect(() => {
    async function fetchData(url) {
      //console.log(url);
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
    if ("/authors" == url){
    fetchData(url)};
    },["/authors" == url ? true: false]);
    return(
        <div>
        {page.authors ? 
            (
                <div>
                    {page.authors.map(author => (
                    <Link to={`/books/query?author=${author.id}&page=1`}>
                        <h3>{author.name}</h3>
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

export default Authors;