import * as React from 'react';
import Container from '@mui/material/Container';
import Stack from '@mui/material/Stack';
import {Link} from 'react-router-dom';
import * as Yup from 'yup';
import { useFormik } from 'formik';
import '../App.css'
import { useState } from 'react';

function LogIn(){
    const [link, setLink] =useState()

    const formik = useFormik({
        initialValues:{
            email: '',
            password: '',
        },
        validationSchema: Yup.object({
            // name: Yup.string().required('Required').min(4, 'Must be 4 characters or more'),
            email: Yup.string().required('Required').matches(/^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/, 'Please enter a valid'),
            password: Yup.string().required('Required').matches(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/, 
            'Password must more than 8 characters and must contain a capital letter and a special character '),
        }),
        onSubmit: (values) => {
            console.log(values)
        },
    });

    function check(){
        return(
            ((formik.values.email) !== "" & (formik.values.password) !== "") ? (
                setLink('/home')
            ) : ( alert('Please enter full information!')),
            console.log(link)
        )
    }

    return(
        <React.Fragment>
            <form style={{height: 'calc(100vh - 100px - 193px)'}} onSubmit={formik.handleSubmit}>
                <Container maxWidth="sm" style={{textAlign:'center'}}>
                    <Stack
                        component=""
                        sx={{
                            width: '350px',
                            margin: '0 auto'
                        }}
                        spacing={2}
                    >
                        <span style={{fontFamily:'initial', fontWeight:'bold', fontSize:'30px', textAlign:'center'}}>
                            Log In
                        </span>
                    
                        <div>
                            <input 
                                type='email'
                                id = 'email'
                                name = 'email'
                                value={formik.values.email}
                                onChange={formik.handleChange} 
                                placeholder='Email'
                                style={{border: '1px solid black', borderRadius:'20px', padding:'8px 12px',width:'350px',}}>
                            </input>
                            {formik.errors.email &&(
                                <p id='errorMsg'>{formik.errors.email}</p>
                            )}
                        </div>

                        <div>
                            <input 
                                type='password'
                                id = 'password'
                                name = 'password'
                                required
                                value={formik.values.password}
                                onChange={formik.handleChange}
                                placeholder='Password' 
                                style={{border: '1px solid black', borderRadius:'20px', padding:'8px 12px',width:'350px',}}>
                            </input>
                            {formik.errors.password &&(
                                <p id='errorMsg'>{formik.errors.password}</p>
                            )}
                        </div>
                        
                        <Link to={link||""}>
                            <button 
                            onClick={() => { check()}}
                            type = "submit"
                            style={{
                                color:'white',
                                backgroundColor:'#1CB0F6',
                                border: '1px solid black', 
                                width:'350px', 
                                height:'42px',
                                borderRadius:'27px',
                            }}>
                                Go !
                            </button>
                        </Link>
                    </Stack>
                </Container>
            </form>
        </React.Fragment>   
    )
}

export default LogIn
