import axios from "axios";

const FLIGHTS_API_BASE_URL = "http://localhost:8081/api/";

class FlightService {
    getFlights(){
        return axios.get(FLIGHTS_API_BASE_URL + "flights")
    }

    buyTicket(dto:any){
        return axios.post(FLIGHTS_API_BASE_URL + "tickets", dto)
    }

    async searchFlights(from: string, where: string, date: string, number: number) {
        try {
            const response = await axios.get(`${FLIGHTS_API_BASE_URL}search?from=${from}&where=${where}&date=${date}&passengers=${number}`);
            return response.data;
        } catch (error) {
            throw error;
        }
    }


}

export default new FlightService();
