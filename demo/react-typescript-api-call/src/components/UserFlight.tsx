import {TableCell, TableRow, TextField} from "@mui/material";
import FlightService from "../services/FlightService";
import {useState} from "react";


// @ts-ignore
const UserFlight = ({userFlight}) => {

    const[numberOfTickets, setNumberOfTickets] = useState("")
    const[remainingTickets, setRemainingTickets] = useState()

    const handleChange1 = (e:any) => {
        const value = e.target.value;
        setDto({ ...dto, [e.target.name]: value });
    };

    const [dto, setDto] = useState({
        where: userFlight.where,
        from: userFlight.from,
        date: userFlight.date,
        price: userFlight.price,
        flightId: userFlight.id,
        userId: "641a3679b9d80171fedea732",
        numberOfTickets: ""
    })


    const update = (e: any) => {
        e.preventDefault();
        FlightService.buyTicket(dto)
            .then((response) => {
                console.log(response);
                window.location.reload();
            }).catch((error) => {
            console.log(error);
        })
    };
return (
        <TableRow key={userFlight.id}>
        <TableCell align={"center"}>
            <div>{userFlight.id}</div>
            </TableCell >
            <TableCell align={"center"}>
        <div>{userFlight.where}</div>
        </TableCell>
        <TableCell align={"center"}>
        <div >{userFlight.from}</div>
        </TableCell>
        <TableCell align={"center"}>
        <div >{userFlight.date}</div>
        </TableCell>
        <TableCell align={"center"}>
        <div >{userFlight.price}</div>
        </TableCell>
        <TableCell align={"center"}>
        <div >{userFlight.remainingTickets}</div>
        </TableCell>
            <TableCell align={"center"}>
                <div> <TextField id="outlined-basic" variant="outlined" value={numberOfTickets} onChange={(e) => {setNumberOfTickets(e.target.value); handleChange1(e)}} name="numberOfTickets" /> </div>
            </TableCell>
            <TableCell align={"center"}>
                <div><button onClick={update}>Buy</button></div>
            </TableCell>

        </TableRow>
);
};

export default UserFlight;