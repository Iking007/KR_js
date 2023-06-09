import React, { useState} from 'react'
import {useLocation} from "react-router-dom";
import axios from 'axios'
import "./css/books.css"
import "./css/addBooks.css"
import address from '../..';

function AddGenre(){
    const [name, setName] = useState();
    
    const post = (data = {}) => {
        console.log(data);
        let config = {
          method: 'post',
          maxBodyLength: Infinity,
          url: `http://${address}:8080/addgenre`,
          headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('token')}`
          },
          data: data
        };
        axios.request(config).then(response => {
            //console.log(response.data);
            window.location.href = "/prof"
            //console.log(response.data);
          })
          .catch(error => {
            console.log(error.config);
          })
      };
    return(
        <div>
            Добавление Жанра
            <form>
                <input type="text" required
                    name="name" placeholder="Введите жанр"
                    class="form-control" value={name} onInput={e => setName(e.target.value)} autocomplete="off"/><br/>
                <button class="my-button" onClick={() => post({
                    'name': name
                })}>Добавить жанр</button>
            </form>
        </div>
    )
}

export default AddGenre;