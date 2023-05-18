import Header from "./elements/blooks/Header";
import {Link, Route, Routes } from "react-router-dom";
import Books from "./elements/pages/Books";
import Book from "./elements/pages/Book";
import Index from "./elements/pages/Index";
import Query from "./elements/pages/Query";
import Genres from "./elements/pages/Genres";
import Login from "./elements/pages/Login";
import AddBook from "./elements/pages/AddBook";
import Authors from "./elements/pages/Authors";
import Profile from "./elements/pages/Profile";

function App() {
  return (
    <div>
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"/>
      <link rel="icon" href="https://digitalreviewboss.com/wp-content/uploads/2020/08/7-1.png"/>
      <Header/>
      <Routes>
        <Route path="/" element={Index()}/>
        <Route path="/books" element={Books()}/>
        <Route path="/book/:id" element={Book()}/>
        <Route path="/books/query" element={Query()}/>
        <Route path="/genres" element={Genres()}/>
        <Route path="/authors" element={Authors()}/>
        <Route path="/login" element={Login()}/>
        <Route path="/prof" element={Profile()}/>
        <Route path="/add/book" element={AddBook()}/>
      </Routes>
    </div>
  );
}

export default App;
