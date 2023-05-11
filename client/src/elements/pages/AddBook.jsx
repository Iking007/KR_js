import "./css/addBooks.css"

function AddBook(){
    return(
        <div>
            Добавление книги
            <form action="/menu/form" method="post">
                <input type="text" required
                    name="title" placeholder="Введите название"
                    class="form-control" autocomplete="off"/><br/>
                <input type="text"
                    name="writer" required placeholder="Введите автора"
                    class="form-control" autocomplete="off"/><br/>
                <input type="text"
                    name="img" placeholder="Введите ссылку на картинку"
                    class="form-control" autocomplete="off"/><br/>
                <input type="text"
                    name="download" placeholder="Введите ссылку на скачивание"
                    class="form-control" autocomplete="off"/><br/>
                <textarea name="str" required placeholder="Введите опиcание"
                        class="form-control"></textarea><br/>
                <button type="submit" class=" my-button">Добавить книгу</button>
            </form>
        </div>
    )
}

export default AddBook;