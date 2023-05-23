import React, { useState, useEffect} from 'react'
import {Link, useLocation} from "react-router-dom";
import axios from 'axios'
import "./css/books.css"
import noImg from "./images/no.png"
import address from '../..';

function Book(){
    const [book, setBook] = useState([]);
    const [page, setPage] = useState([]);
    const [favorite, setFavorite] = useState(true);
    const location = useLocation();
    const url = location.pathname;
    // console.log(url);


    useEffect(() => {
    async function fetchData(url) {
      let controller = new AbortController();
      //console.log(data);
      let config = {
        method: 'get',
        maxBodyLength: Infinity,
        url: `http://${address}:8080`+url,
        headers: { 
          'Content-Type': 'application/json',
        }
      };
      let configAuth = {
        method: 'get',
        maxBodyLength: Infinity,
        url: `http://${address}:8080/auth`+url,
        headers: { 
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        }
      };
      if (localStorage.token != null) {config = configAuth};
      axios.request(config).then(response => {
          console.log(response.data);
          setPage(response.data)
          setBook(response.data.book)
          setFavorite(response.data.favorite)
        })
        .catch(error => {
          console.log(error.config);
        })
      return controller.abort();
    }
    if ("/book/" == url.substring(0,6)){
    fetchData(url)};
    },["/book/" == url.substring(0,6) ? true: false,favorite]);

    const post = (data = {}) => {
      let controller = new AbortController();
      console.log(data);
      let config = {
        method: 'post',
        maxBodyLength: Infinity,
        url: `http://${address}:8080/newfavorite`,
        headers: { 
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        },
        data: data
      };
      axios.request(config).then(response => {
          console.log(response.data);
          setFavorite(true)
          //window.location.replace("/prof")
          //console.log(response.data);
        })
        .catch(error => {
          console.log(error.config);
        })
        controller.abort()
    };

    const post2 = (data = {}) => {
      let controller = new AbortController();
      console.log(data);
      let config = {
        method: 'post',
        maxBodyLength: Infinity,
        url: `http://${address}:8080/delfavorite`,
        headers: { 
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        },
        data: data
      };
      axios.request(config).then(response => {
          console.log(response.data);
          setFavorite(false)
          //window.location.replace("/prof")
          //console.log(response.data);
        })
        .catch(error => {
          console.log(error.config);
        })
        controller.abort()
    };
    const del = (id) => {
      let controller = new AbortController();
      let config = {
        method: 'get',
        maxBodyLength: Infinity,
        url: `http://${address}:8080/delbook?book_id=${id}`,
        headers: { 
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        },
      };
      axios.request(config).then(response => {
          window.location.reload("/")
        })
        .catch(error => {
          console.log(error.config);
        })
        controller.abort()
    };
    return(
      <div>
        {page.book ? 
          ( 
            <div>
              
              <div>
                <div class="pricing-header p-3 pb-md-4 mx-auto text-justify">
                  <h5 class="display-4 text-center">{book.title}</h5>
                  {book.img ? (<img src={book.img} alt="Тут должна быть картинка, но её нет"/>): 
                    (<img src={noImg} alt="Тут должна быть картинка, но её нет"/>)
                  }
                  <p class="fs-5 text-muted p-1">{book.str}</p>
                </div>
                <div class="pricing-header p-3 pb-md-4 mx-auto text-justify p-end">
                  {book.download != null ? <a class="btn btn-success" to={`${book.download}`}>Скачать</a>: null}
                  <p class="fs-5 text-muted mb-1" >Жанр: {book.genre? book.genre.name: "Не указан"}</p>
                  <p class="fs-5 text-muted mb-1" >Книгу написал: {book.author ? book.author.name: "Не указано"}</p>
                  {(localStorage.role == "MODER" || localStorage.role == "ADMIN") ? (<Link class="btn btn-success" to={`/update/${book.id}`}>Редактировать</Link>): null}<br/>
                  {
                    localStorage.role != null ? 
                    <button type='button' class="btn btn-success my-sm-3" onClick={() => del(book.id)}>Удалить</button>: null
                  }<br/>
                  {
                    localStorage.token != null && !page.favorite && !favorite ? 
                    <button type='button' class="btn btn-success my-sm-3" onClick={() => post({'book_id': book.id})}>Добавить в избранное</button>: null
                  }
                  {
                    localStorage.token != null && page.favorite && favorite ? 
                    <button type='button' class="btn btn-success my-sm-3" onClick={() => post2({'book_id': book.id})}>Удалить из избранного</button>: null
                  }
                </div>
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

export default Book;