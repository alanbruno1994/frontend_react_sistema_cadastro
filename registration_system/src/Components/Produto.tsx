import React, { Component } from 'react';
import '../CSS/Global.css'
import Table from './Table';
import { Fornecedor, Produto, statesProduto } from '../Intefaces/statesAplication';
import '../CSS/FornecedoresStyle.css'
import { AiOutlineBars } from "react-icons/ai";
import api from '../Services/Api';




export default class Fornecedores extends Component {
    state: statesProduto =
        {
            visable: "none",
            visableFilter: "none",
            name: "",
            category: "",
            code: "",
            fornecedor: ""
        }

    constructor(props: any) {
        super(props);
        this.generateRegistration = this.generateRegistration.bind(this);
        this.closeRegistration = this.closeRegistration.bind(this);
        this.generateFilter = this.generateFilter.bind(this);
        this.closeFilter = this.closeFilter.bind(this);     
        this.Cadastrar = this.Cadastrar.bind(this);
        this.updateCategory = this.updateCategory.bind(this);
        this.updateCode = this.updateCode.bind(this);
        this.updateFornecedor = this.updateFornecedor.bind(this);
        this.updateName = this.updateName.bind(this);
        this.closeRegistration();
    }


    generateFilter() {
        this.state.visableFilter = "flex";
        this.setState(this.state);
    }

    closeFilter() {
        this.state.visableFilter = "none";
        this.setState(this.state);
    }

  

    generateRegistration() {
        this.state.visable = "flex";
        this.setState(this.state);
    }

    closeRegistration() {       
        api.get("produtos").then(resp => resp.data).then((e: Produto[]) => {
            this.state.visable = "none";
            this.state.list = [...e]; 
            this.setState(this.state);           
        });
        
    }

    updateName(e: any) {
        this.setState({ name: e.target.value })
    }

    updateCode(e: any) {
        this.setState({ code: e.target.value })
    }

    updateFornecedor(e: any) {
        this.setState({ fornecedor: e.target.value })
    }

    updateCategory(e: any) {
        this.setState({ category: e.target.value })
    }

    Cadastrar() {

        if (this.state.name.length > 0 && this.state.code.length > 0 && this.state.category.length > 0 && this.state.fornecedor.length > 0) {
            const post = {
                name: this.state.name,
                code: this.state.code,
                category: this.state.category
                
            }
            let id: number = -1;
            this.state.list?.forEach(e => {
                if (e.fornecedor.name === this.state.fornecedor) {
                        id=e.fornecedor.id;
                }
            });
            if(id!=-1)
            {
                api.post("produtos" + "/"+id, post);
            }

           
        }
        this.closeRegistration();

    }


    render() {


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
            width: "300px"
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
                    <div style={styles} ><span className="text_Title_pag font_Montserrat">Produtos</span><button className="buttonPags background_green font_Montserrat text_color_withe" onClick={e => this.generateRegistration()}>Cadastrar produtos</button><AiOutlineBars className="icon" onClick={e => this.generateFilter()} /></div>
                    <div><Table col1="Nome" col2="Código" col3="Estado" col4="Fornecedor" col5={this.state.list} /></div> </div>
                <div />
            </div>
            <div className="width100 heigth100 background_transparent absolute_position flex-container_fornecedor" style={styleRegister}>

                <div className="background_white areaRegisterFornecedor font_Montserrat">
                    <div><h3>Cadastrar Produto</h3></div>
                    <div><b>Nome do produto</b></div>
                    <div><input type="text" className="width100" onChange={e => this.updateName(e)}></input></div>
                    <div><b>Fornecedor</b></div>
                    <div><input type="text" className="width100" onChange={e => this.updateFornecedor(e)}></input></div>
                    <div className="grid-container_ThreeColun"><div><b>Código do produto</b></div><div /><div><b>Categoria</b></div></div>
                    <div className="grid-container_ThreeColun"><input type="text" className="width100 marginRight10px" onChange={e => this.updateCode(e)}></input><div /><input type="text" className="width100" onChange={e => this.updateCategory(e)}></input></div>
                    <div><button className="background_green text_color_withe buttonOperation" onClick={e => this.closeRegistration()}>Cancelar</button><button className="background_green text_color_withe buttonOperation margin10pxright" onClick={e => this.Cadastrar()}>Cadastrar</button></div>
                </div>

            </div>
            <div className="width100 heigth100 background_transparent absolute_position flex-container_fornecedor" style={styleFilter}>
                <div className="background_white areaRegisterFornecedor font_Montserrat">
                    <div><h3>Filtrar por fornecedor</h3></div>
                    <div><b>Fornecedor</b></div>
                    <div><input type="text" style={style300px} ></input></div>
                    <div><button className="background_green text_color_withe buttonOperation" onClick={e => this.closeFilter()}>Cancelar</button><button className="background_green text_color_withe buttonOperation margin10pxright">Filtrar</button></div>
                </div>
            </div>
        </React.Fragment>
    }

}