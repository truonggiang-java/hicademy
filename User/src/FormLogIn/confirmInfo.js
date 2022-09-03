import * as React from 'react';
import Stack from '@mui/material/Stack';
import Container from '@mui/material/Container';
import { Link } from 'react-router-dom';
import { useFormik } from 'formik';
import '../App.css';
import '../assets/style/cfInfo.css';
import { useState } from 'react';
import * as Yup from 'yup';

function ConfirmPass() {

    const formik = useFormik({
        initialValues:{
            password: '',
            confirm: '',
            name:'',
            address:'',
            phone:'',
            gender:'',
            birth:'',
        },
        validationSchema: Yup.object({
            password: Yup.string().required('Required').matches(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/, 
            'Password must more than 8 characters and must contain a capital letter and a special character '),
            confirm: Yup.string().required('Required').oneOf([Yup.ref('password'),null],'Password must match'),
            name: Yup.string().required('Required'),
            address: Yup.string().required('Required'),
            phone: Yup.string().required('Required'),
            gender: Yup.string().required('Required'),
        }),
        onSubmit: (values) => {
            console.log(values)
        },
    });

    const [link, setLink] =useState()

    function check(){
        return(
            ((formik.values.password) !== "" & (formik.values.confirm) !== "") ? (
                setLink('/home')
            ) : ( alert('Please enter full information!')),
            console.log(link)
        )
    }
    return(
        <React.Fragment>
                <form onSubmit={formik.handleSubmit}>
                    <Container maxWidth="sm" style={{textAlign:'center',minHeight:'calc(100vh - 100px - 133px - 60px)', marginTop:'20px'}}>
                        <Stack
                            component=""
                            sx={{
                                width: '350px',
                                margin: '0 auto'
                            }}
                            spacing={2}
                        >
                            <span style={{fontFamily:'initial', fontWeight:'bold', fontSize:'30px', textAlign:'center'}}>
                                Information
                            </span>  
                            <div>
                                <input 
                                    placeholder='Name'
                                    type='text'
                                    id = 'name'
                                    name = 'name'
                                    required    
                                    onChange={formik.handleChange}
                                    style={{border: '1px solid black', borderRadius:'20px', padding:'8px 12px',width:'350px'}}>
                                </input>
                                {formik.errors.name &&(
                                    <p id='errorMsg'>{formik.errors.name}</p>
                                )}
                            </div>
                            <div>
                                <input 
                                    placeholder='Address'
                                    type='text'
                                    id = 'address'
                                    name = 'address'
                                    required    
                                    onChange={formik.handleChange}
                                    style={{border: '1px solid black', borderRadius:'20px', padding:'8px 12px',width:'350px'}}>
                                </input>
                                {formik.errors.password &&(
                                    <p id='errorMsg'>{formik.errors.password}</p>
                                )}
                            </div>
                            <div>
                                <input 
                                    placeholder='Password'
                                    type='password'
                                    id = 'password'
                                    name = 'password'
                                    required
                                    value={formik.values.password}
                                    onChange={formik.handleChange}
                                    style={{border: '1px solid black', borderRadius:'20px', padding:'8px 12px',width:'350px'}}>
                                </input>
                                {formik.errors.password &&(
                                    <p id='errorMsg'>{formik.errors.password}</p>
                                )}
                            </div>

                            <div>
                                <input 
                                    placeholder='Confirm Password'
                                    type='password'
                                    id = 'confirm'
                                    name = 'confirm'
                                    required
                                    value={formik.values.confirm}
                                    onChange={formik.handleChange}
                                    style={{border: '1px solid black', borderRadius:'20px', padding:'8px 12px',width:'350px'}}
                                >
                                </input>
                                {formik.errors.confirm &&(
                                    <p id='errorMsg'>{formik.errors.confirm}</p>
                                )}
                            </div>

                            <Link to={link||""}>
                                <button 
                                    style={{
                                        color:'white',
                                        backgroundColor:'#1CB0F6',
                                        border: '1px solid black', 
                                        width:'350px', 
                                        height:'42px',
                                        borderRadius:'27px',
                                    }}
                                    type='submit'
                                    onClick={() => { check()}}
                                >
                                    SUBMIT
                                </button>
                            </Link>
                        </Stack>
                    </Container>
                </form>
        </React.Fragment>
    )
}

export default ConfirmPass