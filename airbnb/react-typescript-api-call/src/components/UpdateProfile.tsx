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
import ReservationService from "../services/ReservationService";
import SuiteService from "../services/SuiteService";

interface State {
    password: string;
    email: string;
    showPassword: boolean;
}

export default function UpdateProfile() {
    const [values, setValues] = React.useState<State>({
        password: '',
        email: '',
        showPassword: false,
    });
    const[email, setEmail] = useState("")
    const[password, setPassword] = useState("")
    const[repeatPassword, setRepeatPassword] = useState("")
    const navigate = useNavigate()


    const guest = ['guest']
    const host = ['host']
    var nesto = []

    var [gender1, setGender1] = useState("")
    const handleChange1 = (event: SelectChangeEvent) => {
        setGender1(event.target.value as string);
    };
    if(gender1 === "guest" || gender1 == ""){
        nesto = guest
    }
    else{
        nesto = host
    }
    console.log(nesto)
    const [user, setUser] = useState({
        email: "",
        password: "",
        repeatPassword: "",
        name: "",
        surname: "",
        username: "",
        phone: "",
        roles: guest
    })

    const loadUser = () => {
        var storageId = localStorage.getItem("userId")

        if (storageId != null) {
            UserService.getUser(storageId)
                .then((response) => {
                    setUser({
                        email: response.data.email,
                        password: "",
                        repeatPassword: "",
                        name: response.data.name,
                        surname: response.data.surname,
                        username: response.data.username,
                        phone: response.data.phone,
                        roles: response.data.roles.name
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
        if (user.password === user.repeatPassword) {
            UserService.saveUser(user)
                .then((response) => {
                    console.log(response);
                    navigate("/");
                })
                .catch((error) => {
                    console.log(error);
                });
        } else alert("Password not matching!")
    };

    const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
    };

    const updateUser = (e: any) => {
        e.preventDefault();
        UserService.updateUser(user)
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
    const deleteUser = (e: any) => {
        e.preventDefault();
        const userId = localStorage.getItem("userId");
        const role = localStorage.getItem("roles")
        if(role!.includes("ROLE_GUEST")){
            ReservationService.getByUserStatus(userId)
                .then((response) => {
                    console.log(response);
                    if(response.data.length == 0){
                        ReservationService.deleteAllById(userId)
                            .then((response) => {
                                console.log(response);
                            }).catch((error) => {
                                console.log(error);
                        })
                        UserService.delete(userId)
                            .then((response) => {
                                console.log(response);
                                window.location.reload();
                            }).catch((error) => {
                            console.log(error);
                        })
                    }
                    else{
                        alert("You have active reservations!")
                    }
                }).catch((error) => {
                    console.log(error);
            })
        }
        else if(role!.includes("ROLE_HOST")) {
            ReservationService.getByHostStatus(userId)
                .then((response) => {
                    console.log(response);
                    if (response.data.length == 0) {
                        SuiteService.deleteAllById(userId)
                            .then((response) => {
                                console.log(response);
                                window.location.reload();
                            }).catch((error) => {
                            console.log(error);
                        })
                        UserService.delete(userId)
                            .then((response) => {
                                console.log(response);
                                window.location.reload();
                            }).catch((error) => {
                            console.log(error);
                        })
                    }
                    else{
                        alert("You have active reservations at your suites")
                    }
                }).catch((error) => {
                console.log(error);
            })
        }
    };
    return (
        <Container>
            <Stack direction="column" spacing={1}>
                <h1 style={{alignSelf:'center'}}>Update Profile</h1>
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
                <TextField id="outlined-basic" value={user.name} label="Name" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="name" onChange={(e) => handleChange2(e)}/>
                <TextField id="outlined-basic" value={user.surname} label="Surname" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="surname" onChange={(e) => handleChange2(e)}/>
                <TextField id="outlined-basic" value={user.username} label="Username" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="username" onChange={(e) => handleChange2(e)}/>  
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={updateUser}>Update</Button>
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={deleteUser}>Delete</Button>
            </Stack>
        </Container>
    );
}
