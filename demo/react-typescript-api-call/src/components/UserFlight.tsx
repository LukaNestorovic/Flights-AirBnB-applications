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

    const user = localStorage.getItem("userId")

    const [dto, setDto] = useState({
        where: userFlight.where,
        from: userFlight.from,
        takeoffDate: userFlight.takeoffDate,
        landingDate: userFlight.landingDate,
        price: userFlight.price,
        flightId: userFlight.id,
        userId: user,
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
            if(error.response.status == 406)
                alert("There is not enough tickets available!");
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
        <div >{userFlight.takeoffDate}</div>
        </TableCell>
            <TableCell align={"center"}>
                <div >{userFlight.landingDate}</div>
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