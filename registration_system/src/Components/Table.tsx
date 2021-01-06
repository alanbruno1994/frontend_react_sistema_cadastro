import React, { Component } from 'react'
import '../CSS/Global.css'
interface Props {
    col1: string;
    col2: string;
    col3: string;
    col4: string;
}

export default class Table extends Component<Props> {
    constructor(props: Props) {
        super(props);
        this.generatingTable=this.generatingTable.bind(this);
        
    }

    generatingTable() {
        return <table className="whidth100">
            <tr>
                <th>{this.props.col1}</th>
                <th>{this.props.col2}</th>
                <th>{this.props.col3}</th>
                <th>{this.props.col4}</th>
            </tr>
        </table>

    }

    render() {
        return this.generatingTable();
    }

}