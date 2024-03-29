import axios from "axios";
import authHeader from "./auth-header";

const FLIGHTS_API_BASE_URL = "http://localhost:8081/api/";

class FlightService {
    getFlights(){
        return axios.get(FLIGHTS_API_BASE_URL + "flights", { headers: authHeader() })
    }

    buyTicket(dto:any){
        return axios.post(FLIGHTS_API_BASE_URL + "tickets", dto, { headers: authHeader() })
    }

    create(dto:any){
        return axios.post(FLIGHTS_API_BASE_URL + "flights", dto, { headers: authHeader() })
    }

    delete(id:any){
        return axios.delete(FLIGHTS_API_BASE_URL + "flights/" + id, { headers: authHeader() })
    }
    getSearch(dto:any){
        return axios.post(FLIGHTS_API_BASE_URL + "flights/search", dto, { headers: authHeader() })
    }
}

export default new FlightService();
