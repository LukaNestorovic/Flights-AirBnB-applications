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

interface State {
    password: string;
    email: string;
    showPassword: boolean;
}

export default function Register() {
    const [values, setValues] = React.useState<State>({
        password: '',
        email: '',
        showPassword: false,
    });
    const[email, setEmail] = useState("")
    const[password, setPassword] = useState("")
    const[repeatPassword, setRepeatPassword] = useState("")
    const navigate = useNavigate()

    const [user, setUser] = useState({
        email: "",
        password: "",
        repeatPassword: "",
        name: "",
        surname: "",
        username: "",
        phone: "",
        roles: ["user"]
    })

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
        const value = e.target.value;
        setUser({ ...user, [e.target.name]: value });
    };

    const delay = (ms:any) => new Promise(
        resolve => setTimeout(resolve, ms)
    );

    const saveUser = async (e: any) => {
        e.preventDefault();
        if (user.password === user.repeatPassword) {
            UserService.saveUser(user)
                .then((response) => {
                    console.log(response);
//                    localStorage.setItem("enable", null)
//                    localStorage.setItem("id", response.data.id)

//                    navigate("/profile");
                })
                .catch((error) => {
                    console.log(error);
                });
        } else alert("Password not matching!")
    };

    const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
    };
    return (
        <Container>
            <Stack direction="column" spacing={1}>
                <h1 style={{alignSelf:'center'}}>Register</h1>
                <TextField id="outlined-basic" label="Email" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="email" onChange={(e) => handleChange2(e)} type="text"/>
                <FormControl variant="filled" style={{width:'60ch', alignSelf:'center'}}>
                    <InputLabel htmlFor="filled-adornment-password" >Password</InputLabel>
                    <FilledInput
                        id="filled-adornment-password"
                        type={values.showPassword ? 'text' : 'password'}
                        name="password"
                        onChange={e => {handleChange('password'); handleChange2(e)}}
                        endAdornment={
                            <InputAdornment position="end" >
                                <IconButton
                                    aria-label="toggle password visibility"
                                    onClick={handleClickShowPassword}
                                    onMouseDown={handleMouseDownPassword}
                                    edge="end"
                                >
                                    {values.showPassword ? <VisibilityOff /> : <Visibility />}
                                </IconButton>
                            </InputAdornment>
                        }
                    />
                </FormControl>
                <FormControl variant="filled" style={{width:'60ch', alignSelf:'center'}}>
                    <InputLabel htmlFor="filled-adornment-password">Repeat password</InputLabel>
                    <FilledInput
                        id="filled-adornment-password"
                        type={values.showPassword ? 'text' : 'password'}
                        name="repeatPassword"
                        onChange={e => {handleChange('password'); handleChange2(e)}}
                        endAdornment={
                            <InputAdornment position="end" >
                                <IconButton
                                    aria-label="toggle password visibility"
                                    onClick={handleClickShowPassword}
                                    onMouseDown={handleMouseDownPassword}
                                    edge="end"
                                >
                                    {values.showPassword ? <VisibilityOff /> : <Visibility />}
                                </IconButton>
                            </InputAdornment>
                        }
                    />
                </FormControl>
                <TextField id="outlined-basic" label="Name" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="name" onChange={(e) => handleChange2(e)}/>
                <TextField id="outlined-basic" label="Surname" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="surname" onChange={(e) => handleChange2(e)}/>
                <TextField id="outlined-basic" label="Username" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="username" onChange={(e) => handleChange2(e)}/>
                <TextField id="outlined-basic" label="Phone" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="phone" onChange={(e) => handleChange2(e)}/>
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={saveUser}>Register</Button>
            </Stack>
        </Container>
    );
}
