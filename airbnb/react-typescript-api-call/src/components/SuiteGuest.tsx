import {TableCell, TableRow} from "@mui/material";
import SuiteService from "../services/SuiteService";
import {useState} from "react";
import {useNavigate}           from "react-router-dom";
import ReservationService from "../services/ReservationService";


// @ts-ignore
const SuiteGuest = ({suite,days,gosti}) => {

    const navigate = useNavigate()
    const [data, setData] = useState({
        suiteId: "",
        startDate: "",
        endDate: "",
        number: "",
        status: "",
        userId: "",
        hostId: ""
    })

    const reserveSuite = (e: any) => {
        const startDate = localStorage.getItem("startDate")
        const endDate = localStorage.getItem("endDate")
        const number = localStorage.getItem("number")
        const userId = localStorage.getItem("userId")
        data.suiteId = suite.id
        data.startDate = startDate!
        data.endDate = endDate!
        data.number = number!
        data.userId = userId!
        data.hostId = suite.userId
        if(suite.automated.toString() === "true") { data.status = "true" }
        else data.status = "false"
        ReservationService.create(data).then((response) => {
            console.log(response);
        })
            .catch((error) => {
                console.log(error);
            });
    };

    if(suite.selected === "guest"){
        return (
            <TableRow key={suite.id}>
                <TableCell align={"center"}>
                    <div>{suite.id}</div>
                </TableCell >
                <TableCell align={"center"}>
                    <div>{suite.name}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div >{suite.location}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div >{suite.features}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div >{suite.minGuests}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div >{suite.maxGuests}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div >{suite.normalPrice}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div >{suite.selected}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div >{suite.startDate}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div >{suite.endDate}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div >{gosti * days * suite.normalPrice}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div><button onClick={reserveSuite}>Reserve</button></div>
                </TableCell>
            </TableRow>
        );
    }
    else{
        return (
            <TableRow key={suite.id}>
                <TableCell align={"center"}>
                    <div>{suite.id}</div>
                </TableCell >
                <TableCell align={"center"}>
                    <div>{suite.name}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div >{suite.location}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div >{suite.features}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div >{suite.minGuests}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div >{suite.maxGuests}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div >{suite.normalPrice}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div >{suite.selected}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div >{suite.startDate}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div >{suite.endDate}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div >{days * suite.normalPrice}</div>
                </TableCell>
                <TableCell align={"center"}>
                    <div><button onClick={reserveSuite}>Reserve</button></div>
                </TableCell>
            </TableRow>
        );
    }
};

export default SuiteGuest;