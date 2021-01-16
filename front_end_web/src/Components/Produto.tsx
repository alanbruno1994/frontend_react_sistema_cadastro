import React, { Component } from 'react';
import '../CSS/Global.css'
import Table from './Table';
import { Produto, statesProduto } from '../Intefaces/statesAplication';
import '../CSS/FornecedoresStyle.css'
import { AiOutlineBars } from "react-icons/ai";
import api from '../Services/Api';
import Header from './Header';



export default class Fornecedores extends Component {
    state: statesProduto =
        {
            visable: "none",
            visableFilter: "none",
            name: "",
            category: "",
            code: "",
            fornecedor: "",//e o cnpj
            filtrer: "",//e o cnpj para filtrar
            erro: ""
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
        this.filtrerOperation = this.filtrerOperation.bind(this);
        this.erroCaptured = this.erroCaptured.bind(this);
        this.erroForm = this.erroForm.bind(this);
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

    erroForm(valor: string) {
        this.state.visable = "none";
        this.state.erro = valor;
        this.setState(this.state);
    }
    erroCaptured(e: any) {
        if (e.response.status = 423) {
            sessionStorage.setItem("tk", "")
            sessionStorage.setItem("invalid", "Sua conta foi experida, entre novamente!")
            window.location.href = "http://app-frontendreact.herokuapp.com/";
        } else {
            this.state.erro = ""; this.state.visable = "none"; this.setState(this.state);
        }
    }

    generateRegistration() {
        this.state.visable = "flex";
        this.setState(this.state);
    }

    closeRegistration() {
        api.defaults.headers.common['Authorization'] = "" + sessionStorage.getItem("tk");
        api.get("produtos").then(resp => resp.data).then((e: Produto[]) => {
            this.state.visable = "none";
            this.state.list = [...e];
            this.state.erro = "";
            this.setState(this.state);

        }).catch((e: any) => {
            this.erroCaptured(e);
        }
        );

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

    updateFiltrer(e: any) {
        this.setState({ filtrer: e.target.value })
    }

    Cadastrar() {
        if (this.state.name.length > 0 && this.state.code.length > 0 && this.state.category.length > 0 && this.state.fornecedor.length > 0) {
            if (this.state.code.length == 13) {
                if (this.state.name.length >= 2) {
                    if (this.state.name.length >= 2) {
                        if (this.state.fornecedor.length == 14) {
                            const post = {
                                name: this.state.name,
                                code: this.state.code,
                                category: this.state.category

                            }
                            api.defaults.headers.common['Authorization'] = "" + sessionStorage.getItem("tk");
                            api.post("produtos" + "/" + this.state.fornecedor, post).then(e => this.closeRegistration()).catch((erro) => {
                                api.get("produtos").then(resp => resp.data).then((e: Produto[]) => {
                                    this.state.visable = "none"; this.state.list = [...e];
                                    if (erro.response) {
                                        this.state.erro = erro.response.data.message
                                    }                                
                                    this.setState(this.state);
                                    return
                                }).catch((e: any) => {
                                    this.erroCaptured(e);
                                }
                                );
                            });
                        } else {
                            this.erroForm("O CNPJ deve ter 14 números!")                           
                        }
                    } else {
                        this.erroForm("A categoria deve ter pelo menos 2 caracteres!")                        
                    }
                } else {
                    this.erroForm("O nome do produto deve ter pelo menos 2 caracteres!")                
                }
            } else {
                this.erroForm("O código de barras deve ter 13 dígitos!")                
            }
        } else {        
            this.erroForm("Por favor, para realizar o cadastro você deve preencher todos os campos!")           
        }


    }

    filtrerOperation() {
        api.defaults.headers.common['Authorization'] = "" + sessionStorage.getItem("tk");
        api.get("produtos/cnpj/" + this.state.filtrer).then(resp => resp.data).then((e: Produto[]) => {
            this.state.visableFilter = "none";
            this.state.list = [...e];
            this.state.erro = "";
            this.setState(this.state);

        }).catch((e: any) => {
            this.erroCaptured(e);
        }
        );

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


        return <React.Fragment>
            <Header></Header>
            <div className="grid-container_table relative_position">
                <div />
                <div>
                    <div style={styles} ><span className="text_Title_pag font_Montserrat">Produtos</span><button className="buttonPags background_green font_Montserrat text_color_withe" onClick={e => this.generateRegistration()}>Cadastrar produtos</button><AiOutlineBars className="icon" onClick={e => this.generateFilter()} /></div>
                    <div><Table col1="Nome" col2="Código" col3="Estado" col4="Fornecedor" col5={this.state.list} /> <div><h3>{this.state.erro}</h3></div></div> </div>
                <div />
            </div>
            <div className="width100 heigth100 background_transparent absolute_position flex-container_fornecedor" style={styleRegister}>

                <div className="background_white areaRegisterFornecedor font_Montserrat">
                    <div><h3>Cadastrar Produto</h3></div>
                    <div><b>Nome do produto</b></div>
                    <div><input type="text" className="width100" onChange={e => this.updateName(e)}></input></div>
                    <div><b>Fornecedor</b></div>
                    <div><input type="text" className="width100" onChange={e => this.updateFornecedor(e)} placeholder="Insira o CNPJ"></input></div>
                    <div className="grid-container_ThreeColun"><div><b>Código do produto</b></div><div /><div><b>Categoria</b></div></div>
                    <div className="grid-container_ThreeColun"><input type="text" className="width100 marginRight10px" onChange={e => this.updateCode(e)}></input><div /><input type="text" className="width100" onChange={e => this.updateCategory(e)}></input></div>
                    <div><button className="background_green text_color_withe buttonOperation" onClick={e => this.closeRegistration()}>Cancelar</button><button className="background_green text_color_withe buttonOperation margin10pxright" onClick={e => this.Cadastrar()}>Cadastrar</button></div>
                </div>

            </div>
            <div className="width100 heigth100 background_transparent absolute_position flex-container_fornecedor" style={styleFilter}>
                <div className="background_white areaRegisterFornecedor font_Montserrat">
                    <div><h3>Filtrar por fornecedor</h3></div>
                    <div><b>Fornecedor</b></div>
                    <div><input type="text" style={style300px} placeholder="Insira o CNPJ!" onChange={e => this.updateFiltrer(e)}></input></div>
                    <div><button className="background_green text_color_withe buttonOperation" onClick={e => this.closeFilter()}>Cancelar</button><button className="background_green text_color_withe buttonOperation margin10pxright" onClick={e => this.filtrerOperation()}>Filtrar</button></div>
                </div>
            </div>

        </React.Fragment>
    }

}