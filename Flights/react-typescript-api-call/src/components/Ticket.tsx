import {TableCell, TableRow} from "@mui/material";
import FlightService from "../services/FlightService";
import {useState} from "react";


// @ts-ignore
const Ticket = ({ticket}) => {


    return (
        <TableRow key={ticket.id}>
            <TableCell align={"center"}>
                <div>{ticket.id}</div>
            </TableCell >
            <TableCell align={"center"}>
                <div>{ticket.where}</div>
            </TableCell>
            <TableCell align={"center"}>
                <div >{ticket.from}</div>
            </TableCell>
            <TableCell align={"center"}>
                <div >{ticket.takeoffDate}</div>
            </TableCell>
            <TableCell align={"center"}>
                <div >{ticket.landingDate}</div>
            </TableCell>
            <TableCell align={"center"}>
                <div >{ticket.price}</div>
            </TableCell>

        </TableRow>
    );
};

export default Ticket;