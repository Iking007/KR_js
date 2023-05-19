import React, { useState, useEffect } from 'react'
import {Link} from "react-router-dom";
import axios from 'axios'
import "./css/books.css"
import noImg from "./images/no.png"

function Books(){
    const [page, setPage] = useState([]);
    const [numPage, setNumPage] = useState(1);


    useEffect(() => {
    async function fetchData() {
        await axios.get("http://localhost:8080/books/" + numPage).then(response => {
            setPage(response.data);
          })
          .catch(error => {
            console.log(error.config);
          })
    }
    fetchData();
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
        </div>
    )
}

export default Books;