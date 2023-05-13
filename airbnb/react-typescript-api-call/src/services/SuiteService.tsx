import axios from "axios";
import authHeader from "./auth-header";
const EMPLOYEE_API_BASE_URL = "http://localhost:8085/api/suites";

class SuiteService {
    create(data: any){
        return axios.post(EMPLOYEE_API_BASE_URL, data, { headers: authHeader() });
    }
    get(){
        return axios.get(EMPLOYEE_API_BASE_URL);
    }
    update(data: any, id: any){
        return axios.put(EMPLOYEE_API_BASE_URL + "/" + id, data, { headers: authHeader() })
    }
    getSuite(id: any){
        return axios.get(EMPLOYEE_API_BASE_URL + "/" + id)
    }
    getSearch(data: any){
        return axios.post(EMPLOYEE_API_BASE_URL + "/search", data)
    }
}

export default new SuiteService();