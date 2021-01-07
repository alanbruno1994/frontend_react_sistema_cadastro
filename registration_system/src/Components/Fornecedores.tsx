import React, { Component } from 'react';
import '../CSS/Global.css'
import Table from './Table';
import { Fornecedor } from '../Intefaces/statesAplication';
import '../CSS/FornecedoresStyle.css'

export default class Fornecedores extends Component {
    state: Fornecedor =
        {
            name: "",
            cnpj: "",
            category: "",
            visable: "none"
        }

    constructor(props: any) {
        super(props);
        this.generateRegistration = this.generateRegistration.bind(this);
        this.closeRegistration=this.closeRegistration.bind(this);
    }

    generateRegistration() {
        this.state.visable = "flex";
        this.setState(this.state);
    }

    closeRegistration() {
        this.state.visable = "none";
        this.setState(this.state);
    }


    render() {
        let list: Fornecedor[] =
            [
                {
                    name: "Alan",
                    cnpj: "44064530000179",
                    category: "Teste1"
                },
                {
                    name: "Marcelo",
                    cnpj: "44064530000179",
                    category: "Teste2"
                },
                {
                    name: "Pedro",
                    cnpj: "44064530000179",
                    category: "Teste3"
                },
                {
                    name: "Carlos",
                    cnpj: "44064530000179",
                    category: "Teste4"
                },
                {
                    name: "Silva",
                    cnpj: "44064530000179",
                    category: "Teste5"
                },
                {
                    name: "Joao",
                    cnpj: "44064530000179",
                    category: "Teste6"
                },
                {
                    name: "Pedro",
                    cnpj: "44064530000179",
                    category: "Teste7"
                },
                {
                    name: "Marcus",
                    cnpj: "44064530000179",
                    category: "Teste8"
                },
                {
                    name: "Felipe",
                    cnpj: "44064530000179",
                    category: "Teste9"
                }

            ];

        const styles =
        {
            height: '60px'
        }
        const styleRegister =
        {
            display: this.state.visable
        }
        const padding=
        {
            ["padding-left"]: "10px"
        }

        return <React.Fragment>
            <div className="grid-container_table relative_position">
                <div />
                <div>
                    <div style={styles} ><span className="text_Title_pag font_Montserrat">Fornecedores</span> <button className="buttonPags background_green font_Montserrat text_color_withe" onClick={e => this.generateRegistration()}>Cadastrar fornecedor</button></div>
                    <div><Table col1="Nome" col2="CNPJ" col3="Categoria" col4="Total de produtos" col5={list} /></div> </div>
                <div />
            </div>
            <div className="width100 heigth100 background_transparent absolute_position flex-container_fornecedor" style={styleRegister}>
                
                <div className="background_white areaRegisterFornecedor font_Montserrat">
                    <div><h3>Cadastrar Fornecedor</h3></div>
                    <div>Nome</div>
                    <div><input type="text" className="width100"></input></div>
                    <div className="grid-container_ThreeColun"><div>CNPJ</div><div/><div>Categoria</div></div>
                    <div className="grid-container_ThreeColun"><input type="text" className="width100 marginRight10px"></input><div/><input type="text" className="width100"></input></div>
                    <div><button className="background_green text_color_withe buttonOperation" onClick={e=>this.closeRegistration()}>Cancelar</button><button className="background_green text_color_withe buttonOperation margin10pxright">Cadastrar</button></div>
                </div>
              
            </div>
        </React.Fragment>
    }

}