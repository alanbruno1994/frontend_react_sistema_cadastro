export interface Fornecedor {
    name: string;
    cnpj: string;
    state: string;
    visable?: string | undefined 
    
}

export interface Produto {
    name: string;
    code: string;
    category: string;
    fornecedor: string;
    visable?: string | undefined 
    visableFilter?: string | undefined 
}