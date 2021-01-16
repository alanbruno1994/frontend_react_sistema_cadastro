import axios from 'axios';
const teste=[{"valor":"ok"}]
const api = axios.create({ 
  baseURL: 'https://app-backendjava.herokuapp.com/' 
})

export default api;