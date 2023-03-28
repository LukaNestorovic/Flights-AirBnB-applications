import {useEffect, useState} from "react";
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import Ticket from "./Ticket";
import TicketService from "../services/TicketService"
import {useNavigate} from "react-router-dom";

export default function Tickets(){
    const [loading, setLoading] = useState(true);
    const [tickets, setTickets] = useState<any>();
    const navigate = useNavigate()
    const roles = localStorage.getItem("roles")

    useEffect(() => {
        if(roles == null){
            console.error("Access denied")
            navigate("/")
        }
        else if(!roles.includes("ROLE_USER")){
            console.error("Access denied")
            navigate("/")
        }
        const fetchData = async () => {
            setLoading(true);
            try {
                const response = await TicketService.getTickets(localStorage.getItem("userId"));
                setTickets(response.data);
            } catch (error) {
                console.log(error);
            }
            setLoading(false);
        };
        fetchData();
    }, []);

    return(
        <TableContainer component={Paper}>
            <h1 style={{textAlign: 'center',
                alignSelf: 'center'}}>Tickets</h1>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell align={"center"}>Id</TableCell>
                        <TableCell align={"center"}>Where</TableCell>
                        <TableCell align={"center"}>From</TableCell>
                        <TableCell align={"center"}>Take Off Date</TableCell>
                        <TableCell align={"center"}>Landing Date</TableCell>
                        <TableCell align={"center"}>Price</TableCell>
                    </TableRow>
                </TableHead>
                {!loading && (
                    <TableBody>
                        {tickets.map((ticket:any) => (
                            <Ticket
                                ticket={ticket}
                                key={ticket.id}/>
                        ))}
                    </TableBody>
                )}
            </Table>
        </TableContainer>
    );
};

