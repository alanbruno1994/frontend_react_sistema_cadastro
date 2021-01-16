import React, { Component } from "react";
import '../CSS/HeaderStyle.css';
import '../CSS/Global.css'

export default class Header extends Component {


    constructor(props: any) {
        super(props);       
        this.closeLogin=this.closeLogin.bind(this);
        console.log(sessionStorage.getItem("tk"));
        if(sessionStorage.getItem("tk")==="" || sessionStorage.getItem("tk")===undefined)
        {
            window.location.href = "http://app-frontendreact.herokuapp.com/"; 
            sessionStorage.setItem("invalid","")           
        }
        
    }

    closeLogin()
    {
        sessionStorage.setItem("tk","")
        window.location.href = "http://app-frontendreact.herokuapp.com/";
    }

    render() {   
        sessionStorage.setItem("teste","testando");   
        return <div className="background_green size font_Montserrat flex-container">
            <div className="text_color_withe padding_10_right_lefet"><a href="/Fornecedor" className="link">Fornecedores</a></div>
            <div className=" text_color_withe padding_10_right_lefet"><a href="/Produtos" className="link">Produtos</a></div>
            <div className=" text_color_withe padding_10_right_lefet"><a onClick={e=>this.closeLogin()} className="link">Sair</a></div>
        </div>

    }

}