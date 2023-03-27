import {useEffect, useState} from "react";
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Button, TextField} from "@mui/material";
import UserFlight from "./UserFlight";
import FlightService from "../services/FlightService"


export default function UserFlights(){
    const [loading, setLoading] = useState(true);
    const [userFlights, setUserFlights] = useState<any>();

    const [searchResults, setSearchResults] = useState([]);

    const [searchData, setSearchData] = useState({
        from: "",
        where: "",
        date: "",
        passengers: "",
        flights: [],
    });

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
      const { name, value } = event.target;
      setSearchData((prevSearchData) => ({
        ...prevSearchData,
        [name]: value,
      }));
    };

    const handleSearch = async (event: React.FormEvent<HTMLFormElement>) => {
      event.preventDefault();
      const { from, where, date, passengers } = searchData;
      try {
        const response = await FlightService.searchFlights(
          from,
          where,
          date,
          parseInt(passengers)
        );
        setSearchData((prevSearchData) => ({
          ...prevSearchData,
          flights: response.data,
        }));
      } catch (error) {
        console.log(error);
      }
    };



    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            try {
                const response = await FlightService.getFlights();
                setUserFlights(response.data);
            } catch (error) {
                console.log(error);
            }
            setLoading(false);
        };
        fetchData();
    }, []);

    useEffect(() => {
      setSearchResults(searchData.flights);
    }, [searchData.flights]);

    return(
        <TableContainer component={Paper}>
            <h1 style={{textAlign: 'center',
                alignSelf: 'center'}}>Flights</h1>

            <form onSubmit={handleSearch}>
              <TextField
                label="Departure"
                name="departure"
                value={searchData.from}
                onChange={handleChange}
              />
              <TextField
                label="Arrival"
                name="arrival"
                value={searchData.where}
                onChange={handleChange}
              />
              <TextField
                label="Date"
                name="date"
                type="date"
                value={searchData.date}
                onChange={handleChange}
                InputLabelProps={{ shrink: true }}
              />
              <TextField
                label="Passengers"
                name="passengers"
                value={searchData.passengers}
                onChange={handleChange}
              />
              <Button type="submit" variant="contained">
                Search
              </Button>
            </form>


            <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell align={"center"}>Id</TableCell>
                        <TableCell align={"center"}>Where</TableCell>
                        <TableCell align={"center"}>From</TableCell>
                        <TableCell align={"center"}>Date</TableCell>
                        <TableCell align={"center"}>Price</TableCell>
                        <TableCell align={"center"}>Remaining tickets</TableCell>
                        <TableCell align={"center"} >Number of tickets</TableCell>
                        <TableCell align={"center"} >Buy</TableCell>
                    </TableRow>
                </TableHead>
                {!loading && (
                    <TableBody>
                        {userFlights.map((userFlight:any) => (
                            <UserFlight
                                userFlight={userFlight}
                                key={userFlight.id}/>
                        ))}
                    </TableBody>
                )}
            </Table>
        </TableContainer>
    );
};

