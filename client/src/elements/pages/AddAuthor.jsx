import React, { useState, useEffect, Component } from 'react'
import {useLocation} from "react-router-dom";
import axios from 'axios'
import "./css/books.css"
import "./css/addBooks.css"

function AddAuthor(){
    const [name, setName] = useState();
    const [surname, setSurname] = useState();
    const [middle_name, setDownload] = useState();
    const [str, setStr] = useState();

    const location = useLocation();
    const url = location.pathname;
    
    const post = (data = {}) => {
        console.log(data);
        let config = {
          method: 'post',
          maxBodyLength: Infinity,
          url: 'http://localhost:8080/addauthor',
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
      };
    return(
        <div>
            Добавление книги
            <form>
                <input type="text" required
                    name="name" placeholder="Введите Имя"
                    class="form-control" value={name} onInput={e => setName(e.target.value)} autocomplete="off"/><br/>
                <input type="text"
                    name="surname" placeholder="Введите Фамилию"
                    class="form-control" value={surname} onInput={e => setSurname(e.target.value)} autocomplete="off"/><br/>
                <input type="text"
                    name="middle_name" placeholder="Введите Псевдоним"
                    class="form-control" value={middle_name} onInput={e => setDownload(e.target.value)} autocomplete="off"/><br/>
                <textarea name="str" required placeholder="Введите Описание"
                        class="form-control" value={str} onInput={e => setStr(e.target.value)}></textarea><br/>
                <button class="my-button" onClick={() => post({
                    'name': name,
                    'surname': surname,
                    'middle_name': middle_name,
                    'str': str
                })}>Добавить Автора</button>
            </form>
        </div>
    )
}

export default AddAuthor;