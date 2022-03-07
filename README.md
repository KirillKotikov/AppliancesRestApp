### Appliances

---
Описание:
В проекте реализован регистр бытовой техники (телевизоры, пылесосы, холодильники, смартфоны и компьютеры) с общими атрибутами и моделей к ним. 
Возможно отображение списков всей техники и каждых групп и моделей по отдельности (выдается отсортированный спиок по имени и цене с отображением 
только моделей в наличии). Реализован поиск по названию, цвету, цене (от/до).

---
Использованные технологии:
- Язык программирования Java (JDK 17);
- Frameworks: Spring Boot Starter, Spring Web, Spring JPA, Sprong Lombok;
- База данных: Postgres SQL;

---
Инструкция по запуску:
1) Создать базу данных PostgreSQL с названием _appliances_;
2) В проекте по пути _appliances/src/main/resources/db/DDL_script_for_appliances.sql_ находится DDL-скрипт для добавления по 3 группы каждой техники 
с 3-мя моделями для каждой группы;
3) После установки проекта, необходимо поключить к нему вышеуказанную базу данных с адресом - _jdbc:postgresql://localhost:5432/appliances_;
4) Адрес в браузере для отображения информации из приложения - _http://localhost:8080_;
5) В видах техники возожно использование следующих запросов:
           а) GET request: _http://localhost:8080/_ - отображение всех видов с моделями техники, отсортированные по наименованию и цене,
           с исключением моделей, которых нет в наличии;
           b) Список путей для выбора определенного вида техники:
                           _http://localhost:8080/televisions_
                           _http://localhost:8080/hoovers_
                           _http://localhost:8080/fridges_          
                           _http://localhost:8080/smartphones_       
                           _http://localhost:8080/computers_
          c) GET запросы:
                         _/get-all_ - отображение списка техники отдельно по виду с моделями техники, 
                                    отсортированные по наименованию и цене, с исключением моделей, которых нет в наличии;
                         _?id={id}_ - отображение вида техники с id = {id};
                         _/search-by-name?name={name}_ - поиск по наименованию группы;
          d) POST запрос без добавления к пути с наличием JSON объекта в теле запроса с заполненными параметрами сохранит в базу данных вид техники;
          e) PUT запрос без добавления к пути с наличием JSON объекта в теле запроса с заполненными параметрами обновит в базе данных вид техники;
          f) DELETE запрос _/{id}_ - удалает вид техники вместе с моделями, в котором id = {id).
          
6) В моделях техники возможно использование следующих запросов:
           а) Список путей для выбора определенного вида техники:
                           _http://localhost:8080/televisions-models_
                           _http://localhost:8080/hoovers-models_
                           _http://localhost:8080/fridges-models_          
                           _http://localhost:8080/smartphones-models_       
                           _http://localhost:8080/computers-models_
          3) GET запросы:
                         _/get-all_ - отображение списка всех моделей определенного вида техники, 
                                    отсортированных по наименованию и цене, с исключением тех моделей, которых нет в наличии;
                         _?id={id}_ - отображение модели техники с id = {id};
                         _/search-by-name?name={name}_ - поиск по наименованию модели;
                         _/search-by-color?color={color}_ - поиск по цвету;
                         _/search-by-price?low={lowPrice}&high={highPrice} - поиск по цене от и до;
          4) POST запрос _?id={id}_ к пути с наличием JSON объекта в теле запроса с заполненными параметрами сохранит в базу данных модель техники в вид с id = {id};
          5) PUT запрос без добавления к пути с наличием JSON объекта в теле запроса с заполненными параметрами обновит в базе данных модель техники;
          6) DELETE запрос _/{id}_ - удалает модель техники в которой id = {id).
                         
---
Проект не закончен. Планируется доработка проекта, а именно реализация фильтров с зависимостью от выбора вида техники и фильтровать по атрибутам моделей, 
а также генерация Open API v3 документации в виде страницы swagger.  Продолжение работы над проектом планируется примерно в конце марта - начале апреля 2022.

