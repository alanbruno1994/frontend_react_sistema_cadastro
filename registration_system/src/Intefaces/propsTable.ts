import { Fornecedor, Produto } from './statesAplication';
export interface PropsTable {
    col1: string;
    col2: string;
    col3: string;
    col4: string;
    col5?: Fornecedor[] | Produto[] | undefined;
}




