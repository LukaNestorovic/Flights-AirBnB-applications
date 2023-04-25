import {TableCell, TableRow} from "@mui/material";
import FlightService from "../services/FlightService";
import {useState} from "react";


// @ts-ignore
const Flight = ({flight}) => {

    const deleteFlight = (e: any) => {
        e.preventDefault();
        FlightService.delete(flight.id)
            .then((response) => {
                console.log(response);
                window.location.reload();
            }).catch((error) => {
            console.log(error);
        })
    };

    return (
        <TableRow key={flight.id}>
            <TableCell align={"center"}>
                <div>{flight.id}</div>
            </TableCell >
            <TableCell align={"center"}>
                <div>{flight.where}</div>
            </TableCell>
            <TableCell align={"center"}>
                <div >{flight.from}</div>
            </TableCell>
            <TableCell align={"center"}>
                <div >{flight.takeoffDate}</div>
            </TableCell>
            <TableCell align={"center"}>
                <div >{flight.landingDate}</div>
            </TableCell>
            <TableCell align={"center"}>
                <div >{flight.price}</div>
            </TableCell>
            <TableCell align={"center"}>
                <div >{flight.remainingTickets}</div>
            </TableCell>
            <TableCell align={"center"}>
                <div><button onClick={deleteFlight}>Delete</button></div>
            </TableCell>
        </TableRow>
    );
};

export default Flight;