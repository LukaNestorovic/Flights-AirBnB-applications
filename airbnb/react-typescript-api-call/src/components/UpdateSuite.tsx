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
import UserService             from "../services/UserService"
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

export default function UpdateSuite() {
    const [values, setValues] = React.useState<State>({
        password: '',
        email: '',
        showPassword: false,
    });
    const[email, setEmail] = useState("")
    const[password, setPassword] = useState("")
    const[repeatPassword, setRepeatPassword] = useState("")
    const navigate = useNavigate()


   

    var [gender1, setGender1] = useState("")
    const handleChange1 = (event: SelectChangeEvent) => {
        setGender1(event.target.value as string);
    };

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
        reserved: false
    })

    const loadUser = () => {
        var storageId = localStorage.getItem("suiteId")

        if (storageId != null) {
            SuiteService.getSuite(storageId)
                .then((response) => {
                    setUser({
                        name: response.data.name,
                        location: response.data.location,
                        features: response.data.features,
                        minGuest: response.data.minGuest,
                        maxGuest: response.data.maxGuest,
                        normalPrice: response.data.normalPrice,
                        selected: response.data.selected,
                        startDate: response.data.startDate,
                        endDate: response.data.endDate,
                        reserved: false
                    });
                })
                .catch((error) => {
                    console.log(error);
                });
        }
        else {
            alert("You arent signed in!");
        }
    };


    const handleChange =
        (prop: keyof State) => (event: React.ChangeEvent<HTMLInputElement>) => {
            setValues({ ...values, [prop]: event.target.value });
        };

    const handleClickShowPassword = () => {
        setValues({
            ...values,
            showPassword: !values.showPassword,
        });
    };

    const handleChange2 = (e: any) => {
        setUser({
            ...user,
            [e.target.name]: e.target.value
        });
    };

    const delay = (ms:any) => new Promise(
        resolve => setTimeout(resolve, ms)
    );

    const saveUser = async (e: any) => {
        e.preventDefault();
            SuiteService.create(user)
                .then((response) => {
                    console.log(response);
                    navigate("/");
                })
                .catch((error) => {
                    console.log(error);
                });
    };

    const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
    };

    const updateUser = (e: any) => {
        e.preventDefault();
        var storageId = localStorage.getItem("suiteId")
        SuiteService.update(user, storageId)
            .then((response) => {
                console.log(response);
                alert("Successfully saved user data!");
                window.location.reload();
            })
            .catch((error) => {
                console.log(error);
            });
    };

    React.useEffect(() => {
        loadUser();
    }, []);
    
    return (
        <Container>
            <Stack direction="column" spacing={1}>
                <h1 style={{alignSelf:'center'}}>Update Suite</h1>
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
                <TextField id="outlined-basic" value={user.normalPrice} label="Price" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="normalPrice" onChange={(e) => handleChange2(e)}/>

                <LocalizationProvider style={{width:'60ch'}} dateAdapter={AdapterDayjs}>
                    <DateTimePicker
                        disablePast
                        label="Start date"
                        
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
                        
                        onChange={(newValue:any) => setUser({
                            ...user,
                            endDate: newValue
                        })}
                    />
                </LocalizationProvider>
                
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={updateUser}>Update</Button>
            </Stack>
        </Container>
    );
}
