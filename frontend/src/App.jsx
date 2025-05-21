import React from 'react';
import HomePage from "./ui/pages/HomePage/HomePage.jsx";
import BooksPage from "./ui/pages/BooksPage/BooksPage.jsx";
import BookDetails from "./ui/components/books/BookDetails/BookDetails.jsx";

import Layout from "./ui/components/layout/Layout/Layout.jsx";
import {BrowserRouter, Routes, Route} from "react-router";

import CountriesPage from "./ui/pages/CountriesPage/CountriesPage.jsx";
import CountryDetails from "./ui/components/countries/CountryDetails/CountryDetails.jsx";

import AuthorsPage from "./ui/pages/AuthorsPage/AuthorsPage.jsx";
import AuthorDetails from "./ui/components/authors/AuthorDetails/AuthorDetails.jsx";

import './App.css'


const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index element={<HomePage/>}/>
                    <Route path="books" element={<BooksPage/>}/>
                    <Route path="books/:id" element={<BookDetails/>}/>

                    <Route path="authors" element={<AuthorsPage/>}/>
                    <Route path="authors/:id" element={<AuthorDetails/>}/>

                    <Route path="countries" element={<CountriesPage/>}/>
                    <Route path="countries/:id" element={<CountryDetails/>}/>


                </Route>
            </Routes>
        </BrowserRouter>
    );
};

export default App;


