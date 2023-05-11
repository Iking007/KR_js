import React, { useState, useEffect, Component } from 'react'
import {Link, useLocation} from "react-router-dom";
import axios from 'axios'
import noImg from "./images/no.png"
import "./css/books.css"

function Query(){
    const [page, setPage] = useState([]);
    const [numPage, setNumPage] = useState(1);
    const location = useLocation();
    const params = new URLSearchParams(location.search);
    const filter = params.get('filter');
    const genre_id = params.get('genre');
    //console.log(filter); // значение параметра "myQueryParam" из URL
    

    useEffect(() => {
        function fetchData() {
            //console.log("http://localhost:8080/query?"+"page=" + numPage + ((filter) ? ("&filter=" + filter):"") + ((genre_id) ? ("&genre_id=" + genre_id):""));
            axios.get("http://localhost:8080/query?"+"page=" + numPage + ((filter) ? ("&filter=" + filter): "") + ((genre_id) ? ("&genre_id=" + genre_id):"")).then(response => {
                //console.log(response.data) // значение параметра "myQueryParam" из URL
                setPage(response.data);
            })
            .catch(error => {
                if (error.response) {
                  // сервер ответил сообщением за пределами 2xx
                //   console.log(error.response.data);
                //   console.log(error.response.status);
                //   console.log(error.response.headers);
                } else if (error.request) {
                  // запрос был выполнен, но нет ответа
                //   console.log(error.request);
                } else {
                  // что-то другое случилось
                //   console.log('Error', error.message);
                }
                console.log(error.config);
            })
        }
        fetchData();
        },["/books/query" == location.pathname.substring(0,12) ? true: false]);
  
    
    return(
        <div>
            {page.books ? 
                (
                    <div class="my-row">
                        {page.query ? <p>По запросу ничего не найдено</p>: null}
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
                        <div class="my-numPage">
                            {numPage > 1 ?
                                (<a href="#head"><button onClick={() => setNumPage(numPage - 1)}>←</button></a>):
                                (null)
                            }  
                            {page.page} 
                            {page.maxPage == page.page ? (null): (<>...{page.maxPage}<a href="#head"><button onClick={() => setNumPage(numPage + 1)}>→</button></a></>)
                            }
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
export default Query;