import {useEffect, useState} from "react";
import {Button, Paper, Stack, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import Flight from "./Flight";
import FlightService from "../services/FlightService"
import {useNavigate} from "react-router-dom";
import * as React from "react";

export default function Flights(){
    const [loading, setLoading] = useState(true);
    const [flights, setFlights] = useState<any>();
    const navigate = useNavigate()
    const roles = localStorage.getItem("roles")

    useEffect(() => {
        if(roles == null){
            console.error("Access denied")
            navigate("/")
        }
        else if(!roles.includes("ROLE_ADMIN")){
            console.error("Access denied")
            navigate("/")
        }
        const fetchData = async () => {
            setLoading(true);
            try {
                const response = await FlightService.getFlights();
                setFlights(response.data);
            } catch (error) {
                console.log(error);
            }
            setLoading(false);
        };
        fetchData();
    }, []);

    return(
        <TableContainer component={Paper}>
            <Stack direction="column">
            <h1 style={{textAlign: 'center',
                alignSelf: 'center'}}>Flights</h1>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell align={"center"}>Id</TableCell>
                        <TableCell align={"center"}>Where</TableCell>
                        <TableCell align={"center"}>From</TableCell>
                        <TableCell align={"center"}>Take Off Date</TableCell>
                        <TableCell align={"center"}>Landing Date</TableCell>
                        <TableCell align={"center"}>Price</TableCell>
                        <TableCell align={"center"}>Remaining tickets</TableCell>
                        <TableCell align={"center"}>Delete</TableCell>
                    </TableRow>
                </TableHead>
                {!loading && (
                    <TableBody>
                        {flights.map((flight:any) => (
                            <Flight
                                flight={flight}
                                key={flight.id}/>
                        ))}
                    </TableBody>
                )}
            </Table>
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={() => navigate("/adminhome")}>Home</Button>
            </Stack>
        </TableContainer>

    );
};

