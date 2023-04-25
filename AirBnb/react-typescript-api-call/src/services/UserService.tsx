import axios from "axios";
import authHeader from "./auth-header";
const EMPLOYEE_API_BASE_URL = "http://localhost:8082/api/auth/";

class UserService {
    logIn(user: any){
        return axios.post(EMPLOYEE_API_BASE_URL + "signin", user)
    }
    saveUser(user: any) {
        return axios.post(EMPLOYEE_API_BASE_URL + "signup", user);
    }
    getUser(id: any) {
        return axios.get("http://localhost:8082/api/profile/" + id, { headers: authHeader() });
    }
    updateUser(user: any){
        return axios.put("http://localhost:8082/api/profile", user, { headers: authHeader() })
    }
    delete(id: any){
        return axios.delete("http://localhost:8082/api/profile/" + id, { headers: authHeader() })
    }
}

export default new UserService();