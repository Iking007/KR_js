import React, { useState, useEffect } from 'react'
import {Link, useLocation} from "react-router-dom";
import axios from 'axios'
import "./books.css"
import noImg from "./images/no.png"

function Book(){
    const [book, setBook] = useState([]);
    const location = useLocation();
    const url = location.pathname


    useEffect(() => {
    function fetchData(url) {
        setTimeout(() => axios.get("http://localhost:8080" + url).then(response => {
            console.log(response.data);
            setBook(response.data);
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
          }), 200
        )
    }
    fetchData(url);
    });
    return(
        <div>
            {book.book ? 
                ( 
                    <h1>{book.book.map((book) => (book.title))}</h1>
                
                    
                    // book.book.map(book => (
                    // <div class="my-book">
                    //     <Link to={`/book/${book.id}`}>
                    //         {book.img ? (<img src={book.img} alt="Тут должна быть картинка, но её нет"/>): 
                    //             (<img src={noImg}  alt="Тут должна быть картинка, но её нет"/>)
                    //         }
                    //         <h3>{book.title}</h3>
                    //     </Link>
                    // </div>))

                ): 
                (
                    <>Loading...</>
                )
            }            
        </div>
    )
}

export default Book;