import axios from "axios";
import authHeader from "./auth-header";
const EMPLOYEE_API_BASE_URL = "http://localhost:8085/api/reservations";

class ReservationService{
    create(data:any){
        return axios.post(EMPLOYEE_API_BASE_URL, data, { headers: authHeader() })
    }
    getByHost(id:any){
        return axios.get(EMPLOYEE_API_BASE_URL + "/host/" + id, { headers: authHeader() })
    }
    updateStatus(data:any){
        return axios.put(EMPLOYEE_API_BASE_URL, data, { headers: authHeader() })
    }
    delete(id:any){
        return axios.delete(EMPLOYEE_API_BASE_URL + "/" + id, { headers: authHeader() })
    }
    getByUser(id:any){
        return axios.get(EMPLOYEE_API_BASE_URL + "/" + id, { headers: authHeader() })
    }
    getByUserStatus(id:any){
        return axios.get(EMPLOYEE_API_BASE_URL + "/user/" + id, { headers: authHeader() })
    }
    getByHostStatus(id:any){
        return axios.get(EMPLOYEE_API_BASE_URL + "/hoststatus/" + id, { headers: authHeader() })
    }
    deleteAllById(id:any){
        return axios.delete(EMPLOYEE_API_BASE_URL + "/user/" + id, { headers: authHeader() })
    }
    getBySuite(id:any){
        return axios.get(EMPLOYEE_API_BASE_URL + "/suite/" + id, { headers: authHeader() })
    }
    check(data:any){
        return axios.post(EMPLOYEE_API_BASE_URL + "/check", data, { headers: authHeader() })
    }
}

export default new ReservationService();