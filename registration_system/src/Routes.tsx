import Produto from './Components/Produto'
import Fornecedores from './Components/Fornecedores'
import React from 'react'
import { Redirect } from 'react-router';
import { BrowserRouter, Switch, Route } from 'react-router-dom';


export default () =>
    <BrowserRouter>
        <Switch>
            <Route exact path='/' component={Produto} />
            <Route path='/Produtos' component={Produto} />
            <Route path='/Fornecedor' component={Fornecedores} />
            <Redirect from='*' to='/' />
        </Switch>
    </BrowserRouter>