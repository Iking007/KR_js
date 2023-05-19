import React, { useState, useEffect} from 'react'
import {Link, useLocation} from "react-router-dom";
import axios from 'axios'
import "./css/books.css"
import noImg from "./images/no.png"

function Book(){
    const [book, setBook] = useState([]);
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
        url: 'http://localhost:8080'+url,
        headers: { 
          'Content-Type': 'application/json',
        }
      };
      let configAuth = {
        method: 'get',
        maxBodyLength: Infinity,
        url: 'http://localhost:8080/auth'+url,
        headers: { 
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        }
      };
      if (localStorage.token != null) {config = configAuth};
      axios.request(config).then(response => {
          console.log(response.data);
          setBook(response.data)
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
      setFavorite(true)
      console.log(data);
      let config = {
        method: 'post',
        maxBodyLength: Infinity,
        url: 'http://localhost:8080/newfavorite',
        headers: { 
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        },
        data: data
      };
      axios.request(config).then(response => {
          console.log(response.data);
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
      setFavorite(false)
      console.log(data);
      let config = {
        method: 'post',
        maxBodyLength: Infinity,
        url: 'http://localhost:8080/delfavorite',
        headers: { 
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        },
        data: data
      };
      axios.request(config).then(response => {
          console.log(response.data);
          //window.location.replace("/prof")
          //console.log(response.data);
        })
        .catch(error => {
          console.log(error.config);
        })
        controller.abort()
    };
    return(
      <div>
        {book.book ? 
          ( 
            <div>
              {book.book.map( book =>
              <div>
                <div class="pricing-header p-3 pb-md-4 mx-auto text-justify">
                  <h5 class="display-4 text-center">{book.title}</h5>
                  {book.img ? (<img src={book.img} alt="Тут должна быть картинка, но её нет"/>): 
                    (<img src={noImg}  alt="Тут должна быть картинка, но её нет"/>)
                  }
                  
                  <p class="fs-5 text-muted">{book.str}</p>
                </div>
                <div class="pricing-header p-3 pb-md-4 mx-auto text-justify">
                  {book.download != null ? <Link class="btn btn-success" to={`/update/${book.id}`}>Скачать</Link>: null}
                  <p class="fs-5 text-muted mb-5" >Жанр: {book.genre? book.genre.name: "Не указан"}</p>
                  <p class="fs-5 text-muted mb-5" >Книгу написал: {book.author ? book.author.name: "Не указано"}</p>
                  {(localStorage.role == "MODER" || localStorage.role == "ADMIN") ? <Link class="btn btn-success" to={`/update/${book.id}`}>Редактировать</Link>: null}
                </div>
              </div>
              )}
              {
                localStorage.token != null && !book.favorite && !favorite ? 
                <div> 
                  {book.book.map( book => <button type='button' onClick={() => post({'book_id': book.id})}>Добавить в избранное</button>)}
                </div>: null
              }
              {
                localStorage.token != null && book.favorite && favorite ? 
                <div> 
                  {book.book.map( book => <button type='button' onClick={() => post2({'book_id': book.id})}>Удалить из избранного</button>)}
                </div>: null
              }
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