import React, { useState} from 'react'
import {useLocation} from "react-router-dom";
import axios from 'axios'
import "./css/books.css"
import "./css/addBooks.css"

function AddGenre(){
    const [name, setName] = useState();

    const location = useLocation();
    const url = location.pathname;
    
    const post = (data = {}) => {
        console.log(data);
        let config = {
          method: 'post',
          maxBodyLength: Infinity,
          url: 'http://localhost:8080/addgenre',
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
            Добавление жанра
            <form>
                <input type="text" required
                    name="name" placeholder="Введите жанр"
                    class="form-control" value={name} onInput={e => setName(e.target.value)} autocomplete="off"/><br/>
                <button class="my-button" onClick={() => post({
                    'name': name
                })}>Добавить Автора</button>
            </form>
        </div>
    )
}

export default AddGenre;