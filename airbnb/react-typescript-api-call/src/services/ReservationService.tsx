import axios from "axios";
const EMPLOYEE_API_BASE_URL = "http://localhost:8085/api/reservations";

class ReservationService{
    create(data:any){
        return axios.post(EMPLOYEE_API_BASE_URL, data)
    }
    getByHost(id:any){
        return axios.get(EMPLOYEE_API_BASE_URL + "/host/" + id)
    }
    updateStatus(data:any){
        return axios.put(EMPLOYEE_API_BASE_URL, data)
    }
    delete(id:any){
        return axios.delete(EMPLOYEE_API_BASE_URL + "/" + id)
    }
}

export default new ReservationService();