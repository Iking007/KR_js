import React, { useState, useEffect } from 'react'
import {Link, Route, Routes} from "react-router-dom";
import axios from 'axios'
import "./books.css"
import noImg from "./images/no.png"

function Book(bookId){
    const [book, setBook] = useState([]);


    useEffect(() => {
      async function fetchData(bookId) {
        setTimeout(async() =>
        await axios.get("http://localhost:8080/book/" + bookId).then(response => {
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
      fetchData(bookId);
    });
    return(
        <div>
            {book.book ? 
                (
                    <div class="my-row">
                        <div class="my-container">
                            {page.books.map(book => (
                            <div class="my-book">
                                <Link to={`/book/${book.id}`}>
                                    {book.img ? (<img src={book.img} alt="Тут должна быть картинка, но её нет"/>): 
                                        (<img src={noImg}  alt="Тут должна быть картинка, но её нет"/>)
                                    }
                                    <h3>{book.title}</h3>
                                </Link>
                            </div>))}
                        </div>                        
                    </div>
                ): 
                (
                    <>Loading...</>
                )
            }            
        </div>
    )
}

export default Books;