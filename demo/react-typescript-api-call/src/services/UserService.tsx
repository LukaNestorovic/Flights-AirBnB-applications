import axios from "axios";
const EMPLOYEE_API_BASE_URL = "http://localhost:8081/api/";

class UserService {
    logIn(user: any){
        return axios.post(EMPLOYEE_API_BASE_URL + "login", user)
    }
    saveUser(user: any) {
        return axios.post(EMPLOYEE_API_BASE_URL + "users", user);
    }
}

export default new UserService();