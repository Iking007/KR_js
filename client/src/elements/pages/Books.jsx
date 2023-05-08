import React, { useState, useEffect } from 'react'
import {Link} from "react-router-dom";
import axios from 'axios'
import "./books.css"
import noImg from "./images/no.png"

function Books(){
    const [page, setPage] = useState([]);
    const [numPage, setNumPage] = useState(1);


    useEffect(() => {
    async function fetchData(numPage) {
        await axios.get("http://localhost:8080/books/" + numPage).then(response => {
            console.log(response.data);
            setPage(response.data);
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
    fetchData(numPage);
    },[numPage]);
    return(
        <div>
            {page.books ? 
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
        {/* <div class="container" style="display: flex;flex-wrap: wrap; width: 80vw">
            <div th:each="el : ${books}" style="margin-left: 1vw; width: 310px">
                <a th:href="'/books/' + ${el.id}" style="text-decoration:none; color: #000">
                    <div style="width: 280px; height: 420px">
                        <img th:if="${el.img} != '' " th:src="${el.img}" style="width: 280px; height: 420px"
                                alt="Тут должна быть картинка, но её нет"/>
                        <img th:if="${el.img} == '' " src="https://im.wampi.ru/2022/11/28/no.png"
                                style="width: 280px; height: 420px"
                                alt="Тут должна быть картинка, но её нет"/>
                    </div>
                    <h3 th:text="${el.title}"/>
                </a>
            </div>
        </div> */}
            
        </div>
    )
}

export default Books;