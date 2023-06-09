import React, { useState, useEffect } from 'react'
import {Link, useLocation} from "react-router-dom";
import axios from 'axios'
import "./css/genres.css"
import address from '../..';

function Genres(){
    const [page, setPage] = useState([]);
    const location = useLocation();
    const url = location.pathname;


    useEffect(() => {
    let controller = new AbortController();
    async function fetchData(url) {
      //console.log(url);
      await axios.get(`http://${address}:8080` + url).then(response => {
          setPage(response.data);
        })
        .catch(error => {
          console.log(error.config);
        })
    }
    if ("/genres" == url) {
    fetchData(url)};
    return() => controller.abort
    },["/genres" == url ? true: false]);
    
    const del = (id) => {
      console.log(id);
      let config = {
        method: 'get',
        maxBodyLength: Infinity,
        url: `http://${address}:8080/delgenre?genre_id=${id}`,
        headers: { 
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        }
      };
      axios.request(config).then(response => {
        setPage(response.data);
        })
        .catch(error => {
          console.log(error.config);
        })
    };
    return(
        <div>
        {page.genres ? 
          (
            <div>
              {localStorage.role == "ADMIN" || localStorage.role == "MODER"? <h4>При удалении удалятся и все книги жанра</h4>: null}
              <div class="my-genre">
                {page.genres.map(genre => (
                  <div>
                  <Link to={`/books/query?genre=${genre.id}&page=1`}>
                      <h3>{genre.name}</h3>
                  </Link>
                  {localStorage.role == "ADMIN" || localStorage.role == "MODER" ? <div onClick={() => del(genre.id)}>❌</div>: null}
                </div>
                ))}
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

export default Genres;