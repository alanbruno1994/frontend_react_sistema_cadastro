import React, { Component } from 'react'
import '../CSS/Global.css'
import { PropsTable } from '../Intefaces/propsTable';
import { Fornecedor } from '../Intefaces/stateFornecedores';


export default class Table extends Component<PropsTable> {
    constructor(props: PropsTable) {
        super(props);
        this.generatingTable = this.generatingTable.bind(this);
        this.instanceOfFornecedor = this.instanceOfFornecedor.bind(this);

    }

    instanceOfFornecedor(data: any): data is Fornecedor {
        if (data == null) return false;
        return 'name' in data && 'cnpj' in data && 'category' in data;
    }

    generatingBody(): any {
        let item: JSX.Element[] = [];
        if (this.props.col5 != null) {
            let teste: Fornecedor = this.props.col5[0];
            if (this.instanceOfFornecedor(teste)) {
                let fornecedores: Fornecedor[] = this.props.col5;
                fornecedores.forEach((x: Fornecedor) => item.push(<tr className="text_center">
                    <td>{x.name}</td>
                    <td>{x.cnpj}</td>
                    <td>{x.category}</td>
                    <td>{0}</td>
                </tr>));

            }
        }

        return item;
    }

    generatingTable() {
        let stylesThead = {
            height: '35px'
        };
        let stylesTable = {
            margin: '10px'
        };
        return <table className="width100 separetedTableNot" style={stylesTable}>
            <tr className="background_cefaed" style={stylesThead} >
                <th>{this.props.col1}</th>
                <th>{this.props.col2}</th>
                <th>{this.props.col3}</th>
                <th>{this.props.col4}</th>
            </tr>
            {this.generatingBody()}

        </table>

    }

    render() {
        return this.generatingTable();
    }

}