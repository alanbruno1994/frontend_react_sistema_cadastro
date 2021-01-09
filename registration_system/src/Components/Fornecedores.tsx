import React, { Component } from 'react';
import '../CSS/Global.css'
import Table from './Table';
import { Fornecedor } from '../Intefaces/statesAplication';
import '../CSS/FornecedoresStyle.css'
import api from '../Services/Api';


export default class Fornecedores extends Component {
    list:Fornecedor[]=[];

    state: Fornecedor =
        {            
            name: "",
            cnpj: "",
            state: "",
            totalProdutos:0,
            visable: "none"
        }

    constructor(props: any) {
        super(props);
        this.generateRegistration = this.generateRegistration.bind(this);
        this.closeRegistration=this.closeRegistration.bind(this);
        this.update=this.update.bind(this);
        this.capturedList=this.capturedList.bind(this);
        this.updateCNPJ=this.updateCNPJ.bind(this);
        this.updateName=this.updateName.bind(this);
        this.updateState=this.updateState.bind(this);
        this.Cadastrar=this.Cadastrar.bind(this);
        this.update();
    }

    capturedList(elements:Fornecedor[])
    {
        this.list=[...elements];
    }

    update()
    {      
        api.get("fornecedores").then(resp =>resp.data).then((e:Fornecedor[])=> 
        {this.state.visable="none";this.state.list=[...e]; this.setState(this.state);
         });
      
    }

    generateRegistration() {
        this.state.visable = "flex";
        this.setState(this.state);
    }

    closeRegistration() {
        this.state.visable = "none";
        this.setState(this.state);
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
        console.log(this.state);
        if (this.state.name.length > 0 && this.state.cnpj.length > 0 && this.state.state.length > 0) {
            const post = {
                name: this.state.name,
                cnpj: this.state.cnpj,
                state: this.state.state               
            }           
            api.post("fornecedores", post);
        }
        this.setState({ visable: "noVisible" });
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
            <div className="grid-container_table relative_position">
                <div />
                <div>
                    <div style={styles} ><span className="text_Title_pag font_Montserrat">Fornecedores</span> <button className="buttonPags background_green font_Montserrat text_color_withe" onClick={e => this.generateRegistration()}>Cadastrar fornecedor</button></div>
                    <div><Table col1="Nome" col2="CNPJ" col3="Estado" col4="Total de produtos" col5={this.state.list} /></div> </div>
                <div />
            </div>
            <div className="width100 heigth100 background_transparent absolute_position flex-container_fornecedor" style={styleRegister}>
                
                <div className="background_white areaRegisterFornecedor font_Montserrat">
                    <div><h3>Cadastrar Fornecedor</h3></div>
                    <div>Nome</div>
                    <div><input type="text" className="width100" onChange={e=>this.updateName(e)}></input></div>
                    <div className="grid-container_ThreeColun"><div>CNPJ</div><div/><div>Categoria</div></div>
                    <div className="grid-container_ThreeColun"><input type="text" className="width100 marginRight10px" onChange={e=>this.updateCNPJ(e)} ></input><div/><input type="text" className="width100" onChange={e=>this.updateState(e)}></input></div>
                    <div><button className="background_green text_color_withe buttonOperation" onClick={e=>this.closeRegistration()}>Cancelar</button><button className="background_green text_color_withe buttonOperation margin10pxright" onClick={e=>this.Cadastrar()}>Cadastrar</button></div>
                </div>
              
            </div>
        </React.Fragment>
    }

}