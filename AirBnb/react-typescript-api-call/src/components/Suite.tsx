import {TableCell, TableRow} from "@mui/material";
import SuiteService from "../services/SuiteService";
import {useState} from "react";
import {useNavigate}           from "react-router-dom";


// @ts-ignore
const Suite = ({suite,days,gosti}) => {

    const navigate = useNavigate()

    const updateSuite = (e: any) => {
        navigate("/updatesuite")
        localStorage.setItem("suiteId", suite.id);
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
                    <div><button onClick={updateSuite}>Update</button></div>
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
                    <div><button onClick={updateSuite}>Update</button></div>
                </TableCell>
            </TableRow>
        );
    }
};

export default Suite;