import * as React from 'react';
import {useState, useEffect} from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import {Container, Paper, Button} from '@mui/material';

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
        })
    },[])

  return (
  <Container>
  <Paper elevation={3} style={paperStyle}>
    <h1 style={{color:"blue"}}><u>Add Meal</u></h1>
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
      <TextField id="outlined-basic" label="Meal Type" variant="outlined" fullWidth
      value={mealType}
            onChange={(e)=>setType(e.target.value)}/>

      <Button variant="contained" onClick={handleClick}>Enter</Button>
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