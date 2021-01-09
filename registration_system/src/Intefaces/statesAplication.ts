export interface Fornecedor {
    id?:number | undefined;
    name: string;
    cnpj: string;
    state: string;
    totalProdutos: number;
    visable?: string | undefined 
    list?: Fornecedor[] | undefined
    
}

export interface Produto
{
    id?:number | undefined;
    name: string;
    code: string;
    category: string;
    fornecedor: 
    {
        name:string;
        id:number;
    }
}


export interface statesProduto {
    
    visable?: string | undefined; 
    visableFilter?: string | undefined;
    list?: Produto[] | undefined;
    name: string;
    code: string;
    category: string;
    fornecedor:string;
}