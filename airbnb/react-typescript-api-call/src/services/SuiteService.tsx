import axios from "axios";
import authHeader from "./auth-header";
const EMPLOYEE_API_BASE_URL = "http://localhost:8085/api/suites";

class SuiteService {
    create(data: any){
        return axios.post(EMPLOYEE_API_BASE_URL, data);
    }
    get(){
        return axios.get(EMPLOYEE_API_BASE_URL);
    }
    update(data: any, id: any){
        return axios.put(EMPLOYEE_API_BASE_URL + "/" + id, data)
    }
    getSuite(id: any){
        return axios.get(EMPLOYEE_API_BASE_URL + "/" + id)
    }
    getSearch(data: any){
        return axios.post(EMPLOYEE_API_BASE_URL + "/search", data)
    }
    getSuiteById(id: any){
        return axios.get(EMPLOYEE_API_BASE_URL + "/host/" + id)
    }
}

export default new SuiteService();