import React, { useState, useEffect} from 'react'
import {useLocation} from "react-router-dom";
import axios from 'axios'
import "./css/books.css"
import "./css/addBooks.css"

function AddBook(){
    const [genres, setGenres] = useState([]);
    const [authors, setAuthors] = useState([]);
    const [title, setTitle] = useState();
    const [author, setAuthor] = useState([]);
    const [genre, setGenre] = useState([]);
    const [img, setImg] = useState();
    const [download, setDownload] = useState();
    const [str, setStr] = useState();

    const location = useLocation();
    const url = location.pathname;
    
    useEffect(() => {
    async function fetchData(url) {
      console.log(url);
      await axios.get("http://localhost:8080/genres").then(response => {
            setGenres(response.data.genres);
        })
        .catch(error => {
          console.log(error.config);
        })
        await axios.get("http://localhost:8080/authors").then(response => {
            setAuthors(response.data.authors);
        })
        .catch(error => {
          console.log(error.config);
        })
    }
    if ("/add/book" == url) {
    fetchData(url)};
    },["/add/book" == url ? true: false]);

    const post = (data = {}) => {
        console.log(data);
        let config = {
          method: 'post',
          maxBodyLength: Infinity,
          url: 'http://localhost:8080/addbook',
          headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('token')}`
          },
          data: data
        };
        axios.request(config).then(response => {
            console.log(response.data);
            window.location.href = "/prof"
            //console.log(response.data);
          })
          .catch(error => {
            console.log(error.config);
          })
      };

    var MakeItemGenre = function(X) {
        return <option value={X.id}>{X.name}</option>;
    };

    var MakeItemAuthor = function(X) {
        return <option value={X.id}>{X.name}</option>;
    };
    return(
        <div>
            Добавление книги
            <form>
                <input type="text" required
                    name="title" placeholder="Введите название"
                    class="form-control" value={title} onInput={e => setTitle(e.target.value)} autocomplete="off"/><br/>
                <select type="text" name="author" required placeholder="Выберите жанр" class="form-control" onChange={e => setGenre(e.target.value)}>
                    <option value="" selected disabled>Выберите жанр</option>
                    {genres.map(MakeItemGenre)}
                </select><br/>
                <select type="text" name="author" required placeholder="Выберите автора" class="form-control" onChange={e => setAuthor(e.target.value)}>
                    <option value="">Выберите автора</option>
                    {authors.map(MakeItemAuthor)}
                </select><br/>
                <input type="text"
                    name="img" placeholder="Введите ссылку на картинку"
                    class="form-control" value={img} onInput={e => setImg(e.target.value)} autocomplete="off"/><br/>
                <input type="text"
                    name="download" placeholder="Введите ссылку на скачивание"
                    class="form-control" value={download} onInput={e => setDownload(e.target.value)} autocomplete="off"/><br/>
                <textarea name="str" required placeholder="Введите опиcание"
                        class="form-control" value={str} onInput={e => setStr(e.target.value)}></textarea><br/>
                <button class="my-button" onClick={() => post({
                    'title': title,
                    'author_id': author,
                    'genre_id': genre,
                    'img': img,
                    'download': download,
                    'str': str
                })}>Добавить книгу</button>
            </form>
        </div>
    )
}

export default AddBook;