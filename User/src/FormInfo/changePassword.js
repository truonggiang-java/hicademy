import * as React from 'react';
import Stack from '@mui/material/Stack';
import Container from '@mui/material/Container';
// import { Link } from 'react-router-dom';
import { useFormik } from 'formik';
import '../App.css'
import { useState } from 'react';
import * as Yup from 'yup';

function ChangePass() {

    const formik = useFormik({
        initialValues:{
            oldpass:'',
            password: '',
            confirm: '',
        },
        validationSchema: Yup.object({
            oldpass: Yup.string().required('Required').matches(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/, 
            'Password must more than 8 characters and must contain a capital letter and a special character '),
            password: Yup.string().required('Required').matches(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/, 
            'Password must more than 8 characters and must contain a capital letter and a special character '),
            confirm: Yup.string().required('Required').oneOf([Yup.ref('password'),null],'Password must match')
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
                    <Container maxWidth="sm" style={{textAlign:'center',minHeight:'calc(100vh - 100px - 133px - 48px)', marginTop:'10px'}}>
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
                                    placeholder='Current password'
                                    type='password'
                                    id = 'oldpass'
                                    name = 'oldpass'
                                    required
                                    onChange={formik.handleChange}
                                    style={{border: '1px solid black', borderRadius:'20px', padding:'8px 12px',width:'350px'}}>
                                </input>
                                {formik.errors.oldpass &&(
                                    <p id='errorMsg'>{formik.errors.oldpass}</p>
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
                                    style={{border: '1px solid black', borderRadius:'20px', padding:'8px 12px',width:'350px'}}>
                                </input>
                                {formik.errors.confirm &&(
                                    <p id='errorMsg'>{formik.errors.confirm}</p>
                                )}
                            </div>

                            {/* <Link to={link||""}> */}
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
                            {/* </Link> */}
                        </Stack>
                    </Container>
                </form>
        </React.Fragment>
    )
}

export default ChangePass