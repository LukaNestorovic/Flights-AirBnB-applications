import {useEffect, useState} from "react";
import {Button, Paper, Stack, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import UserFlight from "./UserFlight";
import FlightService from "../services/FlightService"
import TextField from "@mui/material/TextField";
import * as React from "react";
import {DateTimePicker, LocalizationProvider} from "@mui/x-date-pickers";
import {AdapterDayjs} from "@mui/x-date-pickers/AdapterDayjs";
import {useNavigate} from "react-router-dom";

export default function UserFlights(){
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate()
    const [userFlights, setUserFlights] = useState<any>();
    const roles = localStorage.getItem("roles")
    const [user, setUser] = useState({
        where: "",
        from: "",
        takeoffDate: "",
        landingDate: "",
        remainingTickets: "",
    })
    const handleChange2 = (e: any) => {
        const value = e.target.value;
        setUser({ ...user, [e.target.name]: value });
    };

    const saveUser = async (e: any) => {
        e.preventDefault();
        FlightService.getSearch(user)
            .then((response) => {
                console.log(response);
                setUserFlights(response.data);


//                    navigate("/profile");
            })
            .catch((error) => {
                console.log(error);
            });
    };

    useEffect(() => {
        if(roles == null){
        }
        else if(!roles.includes("ROLE_USER")){
            console.error("Access denied")
            navigate("/")
        }
        const fetchData = async () => {
            setLoading(true);
            try {
                    const response = await FlightService.getFlights();
                    setUserFlights(response.data);
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
                <Stack direction="row">
                    <TextField id="outlined-basic" label="Where" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="where" onChange={(e) => handleChange2(e)}/>
                    <TextField id="outlined-basic" label="From" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="from" onChange={(e) => handleChange2(e)}/>
                    <LocalizationProvider style={{width:'60ch'}} dateAdapter={AdapterDayjs}>
                        <DateTimePicker
                            disablePast
                            label="Take off date"
                            value={user.takeoffDate}
                            onChange={(newValue:any) => setUser({
                                ...user,
                                takeoffDate: newValue
                            })}
                        />
                    </LocalizationProvider>

                    <LocalizationProvider style={{width:'60ch'}} dateAdapter={AdapterDayjs} >
                        <DateTimePicker
                            disablePast
                            label="Landing date"
                            value={user.landingDate}
                            onChange={(newValue:any) => setUser({
                                ...user,
                                landingDate: newValue
                            })}
                        />
                    </LocalizationProvider>

                    <TextField id="outlined-basic" label="Num Of Tickets" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="remainingTickets" onChange={(e) => handleChange2(e)}/>
                    <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={saveUser}>Find</Button>
                </Stack>
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
                        <TableCell align={"center"} >Number of tickets</TableCell>
                        <TableCell align={"center"} >Buy</TableCell>
                        <TableCell align={"center"} >Price in total</TableCell>
                    </TableRow>
                </TableHead>
                {!loading && (
                    <TableBody>
                        {userFlights.map((userFlight:any, price:any) => (
                            <UserFlight
                                userFlight={userFlight}
                                price={user.remainingTickets}
                                key={userFlight.id}/>
                        ))}
                    </TableBody>
                )}
            </Table>
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={() => navigate("/userhome")}>Home</Button>
            </Stack>
        </TableContainer>
    );
};

