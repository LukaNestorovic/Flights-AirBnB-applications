import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import {
    Alert,
    Button,
    Container,
    FilledInput,
    FormControl,
    IconButton,
    InputAdornment,
    InputLabel, MenuItem,
    OutlinedInput,
    Paper, Select, SelectChangeEvent, Stack
}                              from "@mui/material";
import Visibility              from '@mui/icons-material/Visibility';
import VisibilityOff           from '@mui/icons-material/VisibilityOff';
import {ChangeEvent, useState} from "react";
import {useNavigate}           from "react-router-dom";
import UserService             from "../services/FlightService"
import {wait}                  from "@testing-library/user-event/dist/utils";
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import {DatePicker, DateTimePicker, LocalizationProvider} from "@mui/x-date-pickers";
import FlightService from "../services/FlightService";
import {Query} from "@testing-library/react";
import dayjs, {Dayjs} from "dayjs";

interface State {
    password: string;
    email: string;
    showPassword: boolean;
}

export default function FlightCreate() {
    const [values, setValues] = React.useState<State>({
        password: '',
        email: '',
        showPassword: false,
    });


    const [user, setUser] = useState({
        where: "",
        from: "",
        takeoffDate: "",
        landingDate: "",
        price: "",
        remainingTickets: ""
    })


    const handleClickShowPassword = () => {
        setValues({
            ...values,
            showPassword: !values.showPassword,
        });
    };

    const handleChange2 = (e: any) => {
        const value = e.target.value;
        setUser({ ...user, [e.target.name]: value });
    };

    const delay = (ms:any) => new Promise(
        resolve => setTimeout(resolve, ms)
    );

    const saveUser = async (e: any) => {
        e.preventDefault();
            FlightService.create(user)
                .then((response) => {
                    console.log(response);
//                    localStorage.setItem("enable", null)
//                    localStorage.setItem("id", response.data.id)

//                    navigate("/profile");
                })
                .catch((error) => {
                    console.log(error);
                });
    };

    return (
        <Container>
            <Stack direction="column" spacing={1}>
                <h1 style={{alignSelf:'center'}}>Register</h1>
                <TextField id="outlined-basic" label="Where" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="where" onChange={(e) => handleChange2(e)} type="text"/>
                <TextField id="outlined-basic" label="From" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="from" onChange={(e) => handleChange2(e)}/>
                <LocalizationProvider dateAdapter={AdapterDayjs}>
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

                <LocalizationProvider dateAdapter={AdapterDayjs} >
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

                <TextField id="outlined-basic" label="Price" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="price" onChange={(e) => handleChange2(e)}/>
                <TextField id="outlined-basic" label="Num Of Tickets" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="remainingTickets" onChange={(e) => handleChange2(e)}/>
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={saveUser}>Create</Button>
            </Stack>
        </Container>
    );
}
