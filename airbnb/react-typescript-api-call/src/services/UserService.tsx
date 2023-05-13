import axios from "axios";
import authHeader from "./auth-header";
const EMPLOYEE_API_BASE_URL = "http://localhost:8085/api/profile";

class UserService {
    logIn(user: any){
        return axios.post(EMPLOYEE_API_BASE_URL + "/signin", user)
    }
    saveUser(user: any) {
        return axios.post(EMPLOYEE_API_BASE_URL + "/signup", user);
    }
    getUser(id: any) {
        return axios.get(EMPLOYEE_API_BASE_URL + "/" + id, { headers: authHeader() });
    }
    updateUser(user: any){
        return axios.put(EMPLOYEE_API_BASE_URL, user, { headers: authHeader() })
    }
    delete(id: any){
        return axios.delete(EMPLOYEE_API_BASE_URL + "/" + id, { headers: authHeader() })
    }
}

export default new UserService();