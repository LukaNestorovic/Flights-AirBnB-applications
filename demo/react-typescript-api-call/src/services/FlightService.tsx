import axios from "axios";

const FLIGHTS_API_BASE_URL = "http://localhost:8081/api/";

class FlightService {
    getFlights(){
        return axios.get(FLIGHTS_API_BASE_URL + "flights")
    }

    buyTicket(dto:any){
        return axios.post(FLIGHTS_API_BASE_URL + "tickets", dto)
    }
}

export default new FlightService();
