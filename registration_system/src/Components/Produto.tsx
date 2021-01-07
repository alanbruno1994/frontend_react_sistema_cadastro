import React, { Component } from 'react';
import '../CSS/Global.css'
import Table from './Table';
import { Produto } from '../Intefaces/statesAplication';
import '../CSS/FornecedoresStyle.css'
import { AiOutlineBars } from "react-icons/ai";





export default class Fornecedores extends Component {
    state: Produto =
        {
            name: "",
            code: "",
            category: "",
            fornecedor:"",
            visable: "none",
            visableFilter:"none"
        }

    constructor(props: any) {
        super(props);
        this.generateRegistration = this.generateRegistration.bind(this);
        this.closeRegistration=this.closeRegistration.bind(this);
        this.generateFilter = this.generateFilter.bind(this);
        this.closeFilter=this.closeFilter.bind(this);
    }

    generateRegistration() {
        this.state.visable = "flex";
        this.setState(this.state);
    }

    closeRegistration() {
        this.state.visable = "none";
        this.setState(this.state);
    }

    generateFilter() {
        this.state.visableFilter = "flex";
        this.setState(this.state);
    }

    closeFilter() {
        this.state.visableFilter = "none";
        this.setState(this.state);
    }


    render() {
        let list: Produto[] =
            [
                {
                    name: "Biscoito",
                    code: "44064530000179",
                    category: "Teste1",
                    fornecedor:"Alan"
                },
                {
                    name: "Arroz",
                    fornecedor: "Marcelo",
                    code: "44064530000179",
                    category: "Teste2"
                },
                {
                    name: "Milho",
                    fornecedor: "Pedro",
                    code: "44064530000179",
                    category: "Teste3"
                },
                {
                    name: "Grama",
                    fornecedor: "Carlos",
                    code: "44064530000179",
                    category: "Teste4"
                },
                {
                    name: "Limao",
                    fornecedor: "Silva",
                    code: "44064530000179",
                    category: "Teste5"
                },
                {
                    name: "Pera",
                    fornecedor: "Joao",
                    code: "44064530000179",
                    category: "Teste6"
                },
                {
                    name: "Mamao",
                    fornecedor: "Pedro",
                    code: "44064530000179",
                    category: "Teste7"
                },
                {
                    name: "Tomate",
                    fornecedor: "Marcus",
                    code: "44064530000179",
                    category: "Teste8"
                },
                {
                    name: "Cebola",
                    fornecedor: "Felipe",
                    code: "44064530000179",
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

        const styleFilter =
        {
            display: this.state.visableFilter
        }
        const style300px =
        {
            width:"300px"
        }
        const padding=
        {
            ["padding-left"]: "10px"
        }

        let availableTags = [
            "ActionScript",
            "AppleScript",
            "Asp",
            "BASIC",
            "C",
            "C++",
            "Clojure",
            "COBOL",
            "ColdFusion",
            "Erlang",
            "Fortran",
            "Groovy",
            "Haskell",
            "Java",
            "JavaScript",
            "Lisp",
            "Perl",
            "PHP",
            "Python",
            "Ruby",
            "Scala",
            "Scheme"
          ];

        return <React.Fragment>
            <div className="grid-container_table relative_position">
                <div />
                <div>
                    <div style={styles} ><span className="text_Title_pag font_Montserrat">Produtos</span><button className="buttonPags background_green font_Montserrat text_color_withe" onClick={e => this.generateRegistration()}>Cadastrar produtos</button><AiOutlineBars className="icon" onClick={e=>this.generateFilter()}/></div>
                    <div><Table col1="Nome" col2="Código" col3="Estado" col4="Fornecedor" col5={list} /></div> </div>
                <div />
            </div>
            <div className="width100 heigth100 background_transparent absolute_position flex-container_fornecedor" style={styleRegister}>
                
                <div className="background_white areaRegisterFornecedor font_Montserrat">
                    <div><h3>Cadastrar Produto</h3></div>
                    <div><b>Nome do produto</b></div>
                    <div><input type="text" className="width100"></input></div>
                    <div><b>Fornecedor</b></div>
                    <div><input type="text" className="width100"></input></div>
                    <div className="grid-container_ThreeColun"><div><b>Código do produto</b></div><div/><div><b>Categoria</b></div></div>
                    <div className="grid-container_ThreeColun"><input type="text" className="width100 marginRight10px"></input><div/><input type="text" className="width100"></input></div>
                    <div><button className="background_green text_color_withe buttonOperation" onClick={e=>this.closeRegistration()}>Cancelar</button><button className="background_green text_color_withe buttonOperation margin10pxright">Cadastrar</button></div>
                </div>
              
            </div>
            <div className="width100 heigth100 background_transparent absolute_position flex-container_fornecedor" style={styleFilter}>                
                <div className="background_white areaRegisterFornecedor font_Montserrat">
                    <div><h3>Filtrar por fornecedor</h3></div>
                    <div><b>Fornecedor</b></div>                    
                    <div><input type="text" style={style300px} ></input></div>                    
                    <div><button className="background_green text_color_withe buttonOperation" onClick={e=>this.closeFilter()}>Cancelar</button><button className="background_green text_color_withe buttonOperation margin10pxright">Filtrar</button></div>
                </div>              
            </div>
        </React.Fragment>
    }

}