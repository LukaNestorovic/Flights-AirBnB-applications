import {useEffect, useState} from "react";
import {Button, Paper, Stack, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField} from "@mui/material";
import Suite from "./Suite";
import SuiteService from "../services/SuiteService"
import {useNavigate} from "react-router-dom";
import * as React from "react";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DateTimePicker } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import SuiteGuest from "./SuiteGuest";

export default function SuitesGuest(){
    const [loading, setLoading] = useState(true);
    const [suites, setSuites] = useState<any>();
    const navigate = useNavigate()
    const roles = localStorage.getItem("roles")
    const [user, setUser] = useState({
        location: "",
        startDate: "",
        endDate: "",
        guests: "",
    })

    useEffect(() => {
        const roles = localStorage.getItem("roles");
        if(roles == null){
            console.error("Access denied")
            navigate("/")
        }
        else if(!roles.includes("ROLE_GUEST")){
            console.error("Access denied")
            navigate("/")
        }
        const fetchData = async () => {
            setLoading(true);
            try {
                const response = await SuiteService.get();
                setSuites(response.data);
            } catch (error) {
                console.log(error);
            }
            setLoading(false);
        };
        fetchData();
    }, []);

    const handleChange2 = (e: any) => {
        const value = e.target.value;
        setUser({ ...user, [e.target.name]: value });
    };

    const saveUser = async (e: any) => {
        e.preventDefault();
        localStorage.setItem("endDate", user.endDate)
        localStorage.setItem("startDate", user.startDate)
        localStorage.setItem("number", user.guests)
        if (user.startDate >= user.endDate){
            alert("Start date can't be after end date")
        }
        else {
            if (user.location == null || user.location === "" || user.endDate == null || user.endDate === ""
                || user.startDate == null || user.startDate === "" || user.guests == null || user.guests === "") {
                alert("Input all parameters for search!")
            } else {
                SuiteService.getSearch(user)
                    .then((response) => {
                        console.log(response);
                        setSuites(response.data);

                    })
                    .catch((error) => {
                        console.log(error);
                    });
            }
        }
        ;
    }
    var nesto = new Date(user.endDate)
    var nesto2= new Date(user.startDate)

    const dani = (nesto.getTime() - nesto2.getTime()) / (24 * 60 * 60 * 1000)



    return(
        <TableContainer component={Paper}>
            <Stack direction="column">
                <h1 style={{textAlign: 'center', alignSelf: 'center'}}>Available Suites</h1>
                <Stack direction="row">
                    <TextField id="outlined-basic" label="Location" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="location" onChange={(e) => handleChange2(e)}/>
                    <TextField id="outlined-basic" label="Guests" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="guests" onChange={(e) => handleChange2(e)}/>
                    <LocalizationProvider style={{width:'60ch'}} dateAdapter={AdapterDayjs}>
                        <DateTimePicker
                            disablePast
                            label="Start date"
                            value={user.startDate}
                            onChange={(newValue:any) => setUser({
                                ...user,
                                startDate: newValue
                            })}
                        />
                    </LocalizationProvider>

                    <LocalizationProvider style={{width:'60ch'}} dateAdapter={AdapterDayjs} >
                        <DateTimePicker
                            disablePast
                            label="End date"
                            value={user.endDate}
                            onChange={(newValue:any) => setUser({
                                ...user,
                                endDate: newValue
                            })}
                        />
                    </LocalizationProvider>

                    <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={saveUser}>Find</Button>
                </Stack>
                <Table sx={{ minWidth: 650 }} aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell align={"center"}>Id</TableCell>
                            <TableCell align={"center"}>Name</TableCell>
                            <TableCell align={"center"}>Location</TableCell>
                            <TableCell align={"center"}>Features</TableCell>
                            <TableCell align={"center"}>Minimum guests</TableCell>
                            <TableCell align={"center"}>Maximum guests</TableCell>
                            <TableCell align={"center"}>Price</TableCell>
                            <TableCell align={"center"}>Price per</TableCell>
                            <TableCell align={"center"}>Start date</TableCell>
                            <TableCell align={"center"}>End date</TableCell>
                            <TableCell align={"center"}>Price</TableCell>
                            <TableCell align={"center"}>Reserve</TableCell>
                        </TableRow>
                    </TableHead>
                    {!loading && (
                        <TableBody>
                            {suites.map((suite:any,days:any,gosti:any) => (
                                <SuiteGuest
                                    suite={suite}
                                    days = {dani}
                                    gosti = {user.guests}
                                    key={suite.id}/>
                            ))}
                        </TableBody>
                    )}
                </Table>
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={() => navigate("/homeguest")}>Home</Button>
            </Stack>
        </TableContainer>

    );
};

