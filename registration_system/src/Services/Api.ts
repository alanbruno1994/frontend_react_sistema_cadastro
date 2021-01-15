import axios from 'axios';
const teste=[{"valor":"ok"}]
const api = axios.create({ 
  baseURL: 'http://localhost:8080' 
})

export default api;