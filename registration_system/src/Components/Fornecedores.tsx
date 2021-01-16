import React, { Component } from 'react';
import '../CSS/Global.css'
import Table from './Table';
import { Fornecedor } from '../Intefaces/statesAplication';
import '../CSS/FornecedoresStyle.css'
import api from '../Services/Api';
import Header from './Header';


export default class Fornecedores extends Component {
    list: Fornecedor[] = [];

    state: Fornecedor =
        {
            name: "",
            cnpj: "",
            state: "",
            totalProdutos: 0,
            visable: "none"
        }

    constructor(props: any) {
        super(props);
        this.generateRegistration = this.generateRegistration.bind(this);
        this.closeRegistration = this.closeRegistration.bind(this);
        this.capturedList = this.capturedList.bind(this);
        this.updateCNPJ = this.updateCNPJ.bind(this);
        this.updateName = this.updateName.bind(this);
        this.updateState = this.updateState.bind(this);
        this.Cadastrar = this.Cadastrar.bind(this);
        this.erroCaptured=this.erroCaptured.bind(this);
        this.erroForm = this.erroForm.bind(this);
        this.closeRegistration();
    }

    erroCaptured(e: any) {
        if (e.response.status = 423) {
            sessionStorage.setItem("tk", "")
            sessionStorage.setItem("invalid", "Sua conta foi experida, entre novamente!")
            window.location.href = "http://localhost:3000";
        } else {
            this.state.erro = ""; this.state.visable = "none"; this.setState(this.state);
        }
    }

    capturedList(elements: Fornecedor[]) {
        this.list = [...elements];
    }

    erroForm(valor: string) {
        this.state.visable = "none";
        this.state.erro = valor;
        this.setState(this.state);
    }

    generateRegistration() {
        this.state.visable = "flex";
        this.setState(this.state);
    }

    closeRegistration() {
        console.log(1);
        api.defaults.headers.common['Authorization'] = "" + sessionStorage.getItem("tk");
        api.get("fornecedores").then(resp => resp.data).then((e: Fornecedor[]) => {
            console.log(2);
            this.state.visable = "none"; this.state.list = [...e]; this.setState(this.state);
        }).catch((e: any) => {
            console.log(3);
            this.erroCaptured(e);
        }
        );
    }



    updateName(e: any) {
        this.setState({ name: e.target.value })
    }

    updateCNPJ(e: any) {
        this.setState({ cnpj: e.target.value })
    }

    updateState(e: any) {
        this.setState({ state: e.target.value })
    }

    Cadastrar() {
        if (this.state.name.length > 0 && this.state.cnpj.length > 0 && this.state.state.length > 0) {
            if (this.state.cnpj.length == 14) {
                if (this.state.state.length >= 3) {
                    if (this.state.name.length >= 12) {
                        const post = {
                            name: this.state.name,
                            cnpj: this.state.cnpj,
                            state: this.state.state
                        }
                        api.defaults.headers.common['Authorization'] = "" + sessionStorage.getItem("tk");
                        api.post("fornecedores", post).catch((erro) => {
                            api.get("fornecedores").then(resp => resp.data).then((e: Fornecedor[]) => {
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
                        })
                        this.closeRegistration();
                    }else
                    {
                        this.erroForm("O nome deve ter pelo menos 12 dígitos!")                         
                    }
                } else {
                    this.erroForm("O estado deve ter pelo menos 3 dígitos!")                    
                }
            } else {
                this.erroForm("O CNPJ deve ter 14 números!")                
            }
        } else {
            this.erroForm("Por favor, para realizar o cadastro você deve preencher todos os campos!")             
        }
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


        return <React.Fragment>
            <Header></Header>
            <div className="grid-container_table relative_position">
                <div />
                <div>
                    <div style={styles} ><span className="text_Title_pag font_Montserrat">Fornecedores</span> <button className="buttonPags background_green font_Montserrat text_color_withe" onClick={e => this.generateRegistration()}>Cadastrar fornecedor</button></div>
                    <div><Table col1="Nome" col2="CNPJ" col3="Estado" col4="Total de produtos" col5={this.state.list} /> <div className="relative_position"><h3>{this.state.erro}</h3></div></div>
                </div>


                <div></div>
            </div>

            <div className="width100 heigth100 background_transparent absolute_position flex-container_fornecedor" style={styleRegister}>

                <div className="background_white areaRegisterFornecedor font_Montserrat">
                    <div><h3>Cadastrar Fornecedor</h3></div>
                    <div>Nome</div>
                    <div><input type="text" className="width100" onChange={e => this.updateName(e)}></input></div>
                    <div className="grid-container_ThreeColun"><div>CNPJ</div><div /><div>Estado</div></div>
                    <div className="grid-container_ThreeColun"><input type="text" className="width100 marginRight10px" onChange={e => this.updateCNPJ(e)} ></input><div /><input type="text" className="width100" onChange={e => this.updateState(e)}></input></div>
                    <div><button className="background_green text_color_withe buttonOperation" onClick={e => this.closeRegistration()}>Cancelar</button><button className="background_green text_color_withe buttonOperation margin10pxright" onClick={e => this.Cadastrar()}>Cadastrar</button></div>
                </div>

            </div>

        </React.Fragment>
    }

}