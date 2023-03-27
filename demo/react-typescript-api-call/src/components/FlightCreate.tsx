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
    const[email, setEmail] = useState("")
    const[password, setPassword] = useState("")
    const[repeatPassword, setRepeatPassword] = useState("")
    const navigate = useNavigate()

    const [value, setValue] = React.useState<Dayjs | null>(dayjs());
    const [value1, setValue1] = React.useState<Dayjs | null>(dayjs());
    const datum = value
    const datum1 = value1


    const [user, setUser] = useState({
        where: "",
        from: "",
        takeoffDate: datum,
        landingDate: datum1,
        price: "",
        remainingTickets: ""
    })

    const handleChange = (event:any) => {
        setValue(event.target.value);
    };

    const handleChange1 = (event:any) => {
        setValue1(event.target.value);
    };

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
                    <DatePicker
                        label="Take off date"
                        value={value}
                        onChange={(newValue) => setValue(newValue)}
                    />
                </LocalizationProvider>

                <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DatePicker
                        label="Landing date"
                        value={value1}
                        onChange={(newValue) => setValue1(newValue)}
                    />
                </LocalizationProvider>

                <TextField id="outlined-basic" label="Price" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="price" onChange={(e) => handleChange2(e)}/>
                <TextField id="outlined-basic" label="Num Of Tickets" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="remainingTickets" onChange={(e) => handleChange2(e)}/>
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={saveUser}>Create</Button>
            </Stack>
        </Container>
    );
}
