import React, { useState, useEffect } from 'react'
import {Link,useLocation} from "react-router-dom";
import axios from "axios";
import noImg from "./images/no.png"

function Favorites(){
    const [page, setPage] = useState([]);
    const [numPage, setNumPage] = useState(1);
    const location = useLocation();
    const url = location.pathname;

    useEffect(() => {
        //console.log(localStorage.getItem('token'))
        async function postRequest(){
          let config = {
            headers: { 
              'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
          };
          axios.get('http://localhost:8080/favorites',config).then(response => {
              console.log(response.data);
              localStorage.role = response.data.role;
              setPage(response.data);
              //window.location.replace("/")
              //console.log(response.data);
            })
            .catch(error => {
              console.log(error.config);
            })
        };
        if ("/prof" == location.pathname){
        postRequest()};
      },["/prof" == url ? true: false]);

    return(
        <div>
            {localStorage.token != null ?
                (
                    <div>
                    <h1>Favorites</h1>
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
                    </div>         
                ):
                null
            }
        </div>
    )
}
export default Favorites;
