import {TableCell, TableRow} from "@mui/material";


// @ts-ignore
const Flight = ({flight}) => {

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
                <div >{flight.date}</div>
            </TableCell>
            <TableCell align={"center"}>
                <div >{flight.price}</div>
            </TableCell>
            <TableCell align={"center"}>
                <div >{flight.remainingTickets}</div>
            </TableCell>
        </TableRow>
    );
};

export default Flight;