import React, { useState, useEffect, Component } from 'react'
import {Link, useLocation} from "react-router-dom";
import axios from 'axios'
import "./css/books.css"
import noImg from "./images/no.png"

function Book(){
    const [book, setBook] = useState([]);
    const location = useLocation();
    const url = location.pathname;
    // console.log(url);


    useEffect(() => {
    async function fetchData(url) {
        await axios.get("http://localhost:8080" + url).then(response => {
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
          })
    }
    if ("/book/" == url.substring(0,6)){
    fetchData(url)};
    },["/book/" == url.substring(0,6) ? true: false]);
    return(
      <div>
        {book.book ? 
          ( 
            <div>
              {book.book.map( book =>
              <div>
                <div class="pricing-header p-3 pb-md-4 mx-auto text-justify">
                  {book.img ? (<img src={book.img} alt="Тут должна быть картинка, но её нет"/>): 
                    (<img src={noImg}  alt="Тут должна быть картинка, но её нет"/>)
                  }
                  <h1 class="display-4 text-center">{book.title}</h1>
                  <p class="fs-5 text-muted"></p>
                </div>
                <div class="pricing-header p-3 pb-md-4 mx-auto text-justify">
                  {book.download != null ? <Link class="btn btn-success" to={`/update/${book.id}`}>Скачать</Link>: null}
                  <p class="fs-5 text-muted mb-5" >Жанр: {book.genre.name}</p>
                  <p class="fs-5 text-muted mb-5" >Книгу написал: {book.author.name}</p>
                  {(localStorage.role == "MODER" || localStorage.role == "ADMIN") ? <Link class="btn btn-success" to={`/update/${book.id}`}>Редактировать</Link>: null}
                </div>
              </div>
              )}
            </div>
          ): 
          (
              <>Loading...</>
          )
        }            
      </div>
    )
}

export default Book;