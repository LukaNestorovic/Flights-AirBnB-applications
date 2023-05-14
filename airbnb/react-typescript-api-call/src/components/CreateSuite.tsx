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
import {ChangeEvent, useEffect, useState} from "react";
import {useNavigate}           from "react-router-dom";
import {wait}                  from "@testing-library/user-event/dist/utils";
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import {DatePicker, DateTimePicker, LocalizationProvider} from "@mui/x-date-pickers";
import {Query} from "@testing-library/react";
import dayjs, {Dayjs} from "dayjs";
import SuiteService from '../services/SuiteService';

interface State {
    password: string;
    email: string;
    showPassword: boolean;
}

export default function CreateSuite() {
    const [values, setValues] = React.useState<State>({
        password: '',
        email: '',
        showPassword: false,
    });

    var [gender1, setGender1] = useState("")
    const handleChange1 = (event: SelectChangeEvent) => {
        setGender1(event.target.value as string);
    };

    var [gender2, setGender2] = useState("")
    const handleChange3 = (event: SelectChangeEvent) => {
        setGender2(event.target.value as string);
    };

    const id = localStorage.getItem("userId")

    const [user, setUser] = useState({
        name: "",
        location: "",
        features: "",
        minGuest: "",
        maxGuest: "",
        normalPrice: "",
        selected: "",
        startDate: "",
        endDate: "",
        automated: "",
        userId: id
    })

    const navigate = useNavigate()
    const roles = localStorage.getItem("roles")

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
            SuiteService.create(user)
                .then((response) => {
                    console.log(response);
                })
                .catch((error) => {
                    console.log(error);
                });
    };

    useEffect(() => {
        if(roles == null){
            console.error("Access denied")
            navigate("/")
        }
        else if(!roles.includes("ROLE_HOST")){
            console.error("Access denied")
            navigate("/")
        }
    },[])

    return (
        <Container>
            <Stack direction="column" spacing={1}>
                <h1 style={{alignSelf:'center'}}>Create Suite</h1>
                <TextField id="outlined-basic" label="Name" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="name" onChange={(e) => handleChange2(e)} type="text"/>
                <TextField id="outlined-basic" label="Location" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="location" onChange={(e) => handleChange2(e)}/>
                <TextField id="outlined-basic" label="Features" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="features" onChange={(e) => handleChange2(e)}/>
                <TextField id="outlined-basic" label="Minimum guests" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="minGuests" onChange={(e) => handleChange2(e)}/>
                <TextField id="outlined-basic" label="Maximum guests" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="maxGuests" onChange={(e) => handleChange2(e)}/>
                <FormControl style={{ width: '100ch', alignSelf: 'center' }}>
                    <InputLabel id="demo-simple-select-label">Price</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="selected"
                        id="demo-simple-select"
                        value={gender1}
                        label="Answer"
                        onChange={e => {
                            handleChange1(e);
                            handleChange2(e)
                        }}
                    >
                        <MenuItem value={'guest'}>Price per guest</MenuItem>
                        <MenuItem value={'suite'}>Price per suite</MenuItem>
                    </Select>
                </FormControl>
                <TextField id="outlined-basic" label="Price" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="normalPrice" onChange={(e) => handleChange2(e)}/>
                <FormControl style={{ width: '100ch', alignSelf: 'center' }}>
                    <InputLabel id="demo-simple-select-label">Automated reservation?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="automated"
                        id="demo-simple-select"
                        value={gender2}
                        label="Answer"
                        onChange={e => {
                            handleChange3(e);
                            handleChange2(e)
                        }}
                    >
                        <MenuItem value={'true'}>Yes</MenuItem>
                        <MenuItem value={'false'}>No</MenuItem>
                    </Select>
                </FormControl>

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
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={saveUser}>Create</Button>
            </Stack>
        </Container>
    );
}
