import {useNavigate} from "react-router-dom";
import {useEffect} from "react";
import {Button, Container, Stack} from "@mui/material";

export default function HomeHost(){
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
        else if(!roles.includes("ROLE_HOST")){
            console.error("Access denied")
            navigate("/")
        }
    },[])
    return(
        <Container>
            <Stack direction="column" spacing={1}>
                <h1 style={{alignSelf:'center'}}>Home</h1>
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={() => navigate("/profile")}>Profile</Button>
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={() => navigate("/createsuites")}>Create Suite</Button>
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={() => navigate("/reservationshost")}>Reservations</Button>
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={() => navigate("/suites")}>Suites</Button>
                <Button variant="contained" style={{width:200, alignSelf:'center', background:'red'}} onClick={logout}>Log out</Button>
            </Stack>
        </Container>
    );
}