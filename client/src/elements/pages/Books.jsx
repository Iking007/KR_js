import React, { useState, useEffect } from 'react'
import axios from 'axios'
const cors = require('cors');
//const axios = require('axios');


function Books(){
    // const books = await axios.get('https://localhost:8080/books');
    // console.log(books)
    const [books, setBooks] = useState(null);

    useEffect(() => {
      async function fetchData() {
        const response = await axios.get("http://localhost:8080/books",  { timeout: 5000 }).then(response => {
            console.log(response.data);
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
          });
        // console.log(response);
        // const books = await response.json();
        // console.log(books)
        // setBooks(books);
      }
      fetchData();
      console.log(books);
    });
    return(
        <div>
            <h1>Книги</h1>
            {books}
        </div>
        
    )
}

export default Books;