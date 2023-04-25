import axios from "axios";
const EMPLOYEE_API_BASE_URL = "http://localhost:8081/api/auth/";

class UserService {
    logIn(user: any){
        return axios.post(EMPLOYEE_API_BASE_URL + "signin", user)
    }
    saveUser(user: any) {
        return axios.post(EMPLOYEE_API_BASE_URL + "signup", user);
    }
}

export default new UserService();