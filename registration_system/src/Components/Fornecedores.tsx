import React, { Component } from 'react';
import '../CSS/Global.css'
import Table from './Table';

interface State {
    name: string;
    cnpj: string;
    category: string;
}

export default class Fornecedores extends Component {
    state: State =
        {
            name: "",
            cnpj: "",
            category: ""
        }

    constructor(props: any) {
        super(props);
    }



    render() {

        return <div className="grid-container_table">
            <div />
            <   div> <Table col1="Nome" col2="CNPJ" col3="Categoria" col4="Total de produtos" /> </div>
            <div />
        </div>
    }

}