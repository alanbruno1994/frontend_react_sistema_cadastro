import React, { Component } from 'react';
import '../CSS/Global.css'
import '../CSS/LoginStyle.css'
import { LoginInteface } from '../Intefaces/InterfaceLogin';
import { Token } from '../Intefaces/Token';
import api from '../Services/Api';

export default class Login extends Component {

    constructor(props: any) {
        super(props);       
        this.updateName = this.updateName.bind(this);
        this.updatePassword = this.updatePassword.bind(this);
        this.logIn = this.logIn.bind(this);
        if(sessionStorage.getItem("tk")!=="")
        {
            window.location.href = "http://app-frontendreact.herokuapp.com/produtos";
        }
        
    }


    state:LoginInteface=
    {
        name:"root",
        password:"123456"
    }

    updateName(e: any) {
        this.setState({ name: e.target.value })
    }

    updatePassword(e: any) {
        this.setState({ password: e.target.value })
    }

    logIn()
    {
        const post = {
            name_user: this.state.name,
            password: this.state.password         

        }
        api.post("users",post).then(resp => resp.data).then((e: Token) => {
          sessionStorage.setItem("tk",e.token);
          window.location.href = "http://app-frontendreact.herokuapp.com/produtos";
        }).catch((e: any) => { })
    }


    render() {    
            
        return <React.Fragment>
            <div className="grid-container-login heigth100 font_Montserrat">
            <div/><div/><div/>
                <div />
                <div>
                    <div><h3>Nome de usuario:</h3></div>
                    <div><input type="text" value={this.state.name} className="width100" onChange={e => this.updateName(e)}></input></div>
                    <div><h3>Senha:</h3></div>
                    <div><input type="password" value={this.state.password} className="width100" onChange={e => this.updatePassword(e)}></input></div>
                    <div><button className="background_green text_color_withe buttonOperation" onClick={e=>this.logIn()}>Logar</button><b>{sessionStorage.getItem("invalid")}</b></div>
                    
                </div>
                <div />
                <div/><div/><div/>
            </div>
        </React.Fragment>
    }
}