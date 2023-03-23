import {useEffect, useState} from "react";
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import Flight from "./Flight";
import FlightService from "../services/FlightService"

export default function Flights(){
    const [loading, setLoading] = useState(true);
    const [flights, setFlights] = useState<any>();

    useEffect(() => {
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
            <h1 style={{textAlign: 'center',
                alignSelf: 'center'}}>Flights</h1>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell align={"center"}>Id</TableCell>
                        <TableCell align={"center"}>Where</TableCell>
                        <TableCell align={"center"}>From</TableCell>
                        <TableCell align={"center"}>Date</TableCell>
                        <TableCell align={"center"}>Price</TableCell>
                        <TableCell align={"center"}>Remaining tickets</TableCell>
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
        </TableContainer>
    );
};

