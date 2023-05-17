import {useNavigate} from "react-router-dom";
import {useEffect} from "react";
import {Button, Container, Stack} from "@mui/material";

export default function HomeGuest() {
    const navigate = useNavigate()
    const roles = localStorage.getItem("roles")
    const logout = (e:any) => {
        e.preventDefault();
        localStorage.clear()
        navigate("/")
    };
    useEffect(() => {
        if(roles == null){
            console.error("Access denied")
            navigate("/")
        }
        else if(!roles.includes("ROLE_GUEST")){
            console.error("Access denied")
            navigate("/")
        }
    },[])

    return(
        <Container>
            <Stack direction="column" spacing={1}>
                <h1 style={{alignSelf:'center'}}>Home</h1>
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={() => navigate("/profile")}>Profile</Button>
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={() => navigate("/suitesguest")}>Suites</Button>
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={() => navigate("/reservationsguest")}>Reservations</Button>
                <Button variant="contained" style={{width:200, alignSelf:'center', background:'red'}} onClick={logout}>Log out</Button>
            </Stack>
        </Container>
    );
}