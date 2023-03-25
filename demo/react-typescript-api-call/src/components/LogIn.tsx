import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import {
    Button,
    Container,
    FilledInput,
    FormControl,
    IconButton,
    InputAdornment,
    InputLabel,
    OutlinedInput,
    Paper, Stack
} from "@mui/material";
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import {useState} from "react";
import {useNavigate} from "react-router-dom";
import UserService from "../services/UserService";

interface State {
    password: string;
    username: string;
    showPassword: boolean;
}

export default function LogIn() {
    const paperStyle={alignItems: 'center'}
    const [values, setValues] = React.useState<State>({
        password: '',
        username: '',
        showPassword: false,
    });
    const[username, setUsername] = useState("")
    const[password, setPassword] = useState("")
    const navigate = useNavigate()

    const [user, setUser] = useState({
        id: "",
        username: "",
        password: ""
    });


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

    const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
    };

    function sendLoginRequest() {
        const reqBody = {
            username: username,
            password: password
        }
    }

    const handleChange1 = (e:any) => {
        const value = e.target.value;
        setUser({ ...user, [e.target.name]: value });
    };

    const saveUser = (e:any) => {
        e.preventDefault();
        UserService.logIn(user)
            .then((response) => {
                console.log(response);
                localStorage.setItem("user", JSON.stringify(response.data));
                if(response.data.roles.includes("ROLE_USER"))
                    navigate("/userflights")
                else
                    navigate("/flights")
            })
            .catch((error) => {
                console.log(error);
                alert("Wrong username or password");
            });
    };

    return (
        <Container>
            <Stack direction="column" spacing={1}>
                <h1 style={{alignSelf:'center'}}>LogIn</h1>
                <TextField id="outlined-basic" label="Username" variant="filled" style={{width:'60ch', alignSelf:'center'}} value={username} onChange={(e) => {setUsername(e.target.value); handleChange1(e)}} name="username"/>
                <FormControl variant="filled" style={{width:'60ch', alignSelf:'center'}}>
                    <InputLabel htmlFor="filled-adornment-password" >Password</InputLabel>
                    <FilledInput
                        id="filled-adornment-password"
                        type={values.showPassword ? 'text' : 'password'}
                        value={password}
                        name="password"
                        onChange={e => {handleChange('password'); setPassword(e.target.value); handleChange1(e)}}
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
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={saveUser}>LogIn</Button>
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={() => navigate("/register")}>Register</Button>
            </Stack>
        </Container>
    );
}
