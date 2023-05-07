import React, { useState, useEffect } from 'react'
import axios from 'axios'
import "./books.css"
import noImg from "./images/no.png"

function Books(){
    const [books, setBooks] = useState([]);

    useEffect(() => {
      async function fetchData() {
        setTimeout(async() =>
        await axios.get("http://localhost:8080/books").then(response => {
            console.log(response.data);
            setBooks(response.data.books);
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
      fetchData();
    });
    return(
        <div class="row my-row" >
            <div class="container my-container">
            {books ? 
                (
                <div>
                    {books.map(book => (
                    <div class="my-book">
                        <div>
                            <div>
                                {book.img ? (<img src={book.img} alt="Тут должна быть картинка, но её нет"/>): 
                                    (<img src={noImg}  alt="Тут должна быть картинка, но её нет"/>)
                                }
                            </div>
                            <h3>{book.title}</h3>
                        </div>
                    </div>))}
                </div>
                ): 
                (
                    <p>Loading...</p>
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
        </div>
    )
}

export default Books;