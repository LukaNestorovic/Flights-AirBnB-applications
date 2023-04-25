import axios from "axios";
import authHeader from "./auth-header";

const TICKETS_API_BASE_URL = "http://localhost:8081/api/";

class TicketService{
    getTickets(id:any){
        return axios.get(TICKETS_API_BASE_URL + "tickets/" + id, { headers: authHeader() })
    }
}

export default new TicketService();