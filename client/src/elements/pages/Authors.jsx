import React, { useState, useEffect } from 'react'
import {Link, useLocation} from "react-router-dom";
import axios from 'axios'
import "./css/authors.css";
import address from '../..';

function Authors(){
    const [page, setPage] = useState([]);
    const location = useLocation();
    const url = location.pathname;


    useEffect(() => {
    async function fetchData(url) {
      //console.log(url);
      await axios.get(`http://${address}:8080` + url).then(response => {
          setPage(response.data);
          //console.log(url);
        })
        .catch(error => {
          console.log(error.config);
        })
    }
    if ("/authors" == url){
    fetchData(url)};
    },["/authors" == url ? true: false]);

    const del = (id) => {
      console.log(id);
      let config = {
        method: 'get',
        maxBodyLength: Infinity,
        url: `http://${address}:8080/delauthor?author_id=${id}`,
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
        {page.authors ? 
            (
              <div>
                {localStorage.role == "ADMIN" || localStorage.role == "MODER" ? <h4>При удалении удалятся и все книги Автора</h4>: null}
                <div class="my-author"> 
                  {page.authors.map(author => (
                    <div class="my-author">
                      <Link to={`/books/query?author=${author.id}&page=1`}>
                          <h3>{author.name} {author.surname}</h3>
                      </Link>
                      {localStorage.role == "ADMIN" || localStorage.role == "MODER" ? <div onClick={() => del(author.id)}>❌</div>: null}
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

export default Authors;