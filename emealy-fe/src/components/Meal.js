import * as React from 'react';
import {useEffect, useState} from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import {Button, Container, FormControl, InputLabel, Paper, Select} from '@mui/material';
import MenuItem from "@mui/material/MenuItem";

export default function Meal() {
    const paperStyle = {padding:'50px 20px', width:600, margin:"20px auto"}
    const[name, setName]=useState('')
    const[mealType, setType]=useState('')
    const[meals, setMeals]=useState([])

    const handleClick=(e)=>{
        e.preventDefault()
        const meal = {name, mealType}
        console.log(meal)
        fetch("http://localhost:8080/addMeal",{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify(meal)
        }).then(()=>{
        console.log("New Meal added!")
        })

    }

    useEffect(()=>{
            fetch("http://localhost:8080/allMeals")
                .then(res=>res.json())
                .then((result)=>{
                setMeals(result);
        }).catch((error) =>{
                      console.error('Error fetching data:', error);
                      alert('Failed to fetch. Please make sure backend server is running.');
                      })
    },[])

  return (
  <Container>
  <Paper elevation={3} style={paperStyle}>
    <h1 style={{color:"orange"}}>Add Meal</h1>
    <Box
      component="form"
      sx={{
        '& > :not(style)': { m: 1},
      }}
      noValidate
      autoComplete="off"
    >
      <TextField id="outlined-basic" label="Meal Name" variant="outlined" fullWidth
      value={name}
      onChange={(e)=>setName(e.target.value)}/>
        <FormControl fullWidth variant="outlined">
            <InputLabel id="meal-type-label">Meal Type</InputLabel>
            <Select
                labelId="meal-type-label"
                id="outlined-select-mealType"
                value={mealType}
                onChange={(e) => setType(e.target.value)}
                label="Meal Type"
                variant={"outlined"}
            >
                <MenuItem value="BREAKFAST">Breakfast</MenuItem>
                <MenuItem value="LUNCH">Lunch</MenuItem>
                <MenuItem value="DINNER">Dinner</MenuItem>
                <MenuItem value="SNACK">Snack</MenuItem>
                <MenuItem value="DESSERT">Dessert</MenuItem>
                <MenuItem value="DRINK">Drink</MenuItem>
            </Select>
        </FormControl>

      <Button variant="contained" color="inherit" onClick={handleClick}>Enter</Button>
    </Box>

    {name} for {mealType}
    </Paper>

      <h1>Meals</h1>
    <Paper elevation={3} style={paperStyle}>
            {meals.map(meal=>(
                <Paper elevation={6} style={{margin:"10px", padding:"15px", textAlign:"left"}} key={meal.name}>
                    Name : {meal.name}<br/>
                    Type : {meal.mealType}<br/>
                </Paper>
            ))
            }
    </Paper>

    </Container>
  );
}
