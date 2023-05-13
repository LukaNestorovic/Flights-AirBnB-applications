import {useState} from "react";
import {TableCell, TableRow} from "@mui/material";
import ReservationService from "../services/ReservationService";

// @ts-ignore
const ReservationGuest = ({reservation}) => {

    const remove = (e:any) => {
        ReservationService.delete(reservation.id).then((response) => {
            console.log(response);
        })
            .catch((error) => {
                console.log(error);
            });
        window.location.reload();
    }
    return(
        <TableRow key={reservation.id}>
            <TableCell align={"center"}>
                <div>{reservation.id}</div>
            </TableCell >
            <TableCell align={"center"}>
                <div>{reservation.suiteId}</div>
            </TableCell>
            <TableCell align={"center"}>
                <div >{reservation.startDate}</div>
            </TableCell>
            <TableCell align={"center"}>
                <div >{reservation.endDate}</div>
            </TableCell>
            <TableCell align={"center"}>
                <div >{reservation.number}</div>
            </TableCell>
            <TableCell align={"center"}>
                <div><button onClick={remove}>Delete</button></div>
            </TableCell>
        </TableRow>
    );
}

export default ReservationGuest;