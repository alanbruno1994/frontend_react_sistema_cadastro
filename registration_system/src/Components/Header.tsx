import React, { Component } from "react";
import '../CSS/HeaderStyle.css';
import '../CSS/Global.css'

export default class Header extends Component {

    render() {      
        return <div className="background_green size font_Montserrat flex-container">
            <div className="text_color_withe padding_10_right_lefet"><a>Fornecedores</a></div>
            <div className=" text_color_withe padding_10_right_lefet"><a>Produtos</a></div>
        </div>

    }

}