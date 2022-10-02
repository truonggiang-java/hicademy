import * as React from 'react';
import Stack from '@mui/material/Stack';
import Container from '@mui/material/Container';
import { Link } from 'react-router-dom';
import { useFormik } from 'formik';
import '../App.css';
import '../assets/style/cfInfo.css';
import { useState } from 'react';
import * as Yup from 'yup';
import axios from '../utils/axios';

function ConfirmPass() {
    const email_register = localStorage.getItem("email_register")
    const formik = useFormik({
        initialValues:{
            password: "",
            confirm: "",
            name: "",
            address: "",
            phone: "",
            gender: "",
            birthday: "",
        },
        validationSchema: Yup.object({
            password: Yup.string().required('Required').matches(/^.{6,}$/, 
            'Password must more than 6 characters'),
            confirm: Yup.string().required('Required').oneOf([Yup.ref('password'),null],'Password must match'),
            name: Yup.string().required('Required'),
            address: Yup.string().required('Required'),
            phone: Yup.string().required('Required').matches(/^0([1-9]{1}[0-9]{8,9})$/, 'Please check your phone number'),
            gender: Yup.string().required('Required'),
            birth: Yup.string().required('Required'),
        })
    });  

    const submit = async () => {
        console.log( 'submit',formik.values)
        const {password, name, address, confirm, phone, gender, birthday} = formik.values
        if ((!password || password.trim() === '') || (!name || name.trim() === '') || (!address || address.trim() === '') || (!confirm || confirm.trim() === '') || (!phone || phone.trim() === '') || (!gender || gender.trim() === '') || (!birthday || birthday.trim() === '')) {
            return alert('Please enter full information!')
        }
        try {
            const res = await axios.post(`/api/v2/customer/insert`, {...formik.values, telephone: phone, dateOfBirth: birthday,  email: email_register});
            if(res) {
                window.location.href = 'http://localhost:3000/home'
            }
        } catch(err) 
        {
            console.log('ERR submit form register', err.response.data)
        }
    }
    return(
        <React.Fragment>
                <form onSubmit={formik.handleSubmit}>
                    <Container maxWidth="lg"  style={{textAlign:'center',minHeight:'calc(100vh - 100px - 133px - 60px)', marginTop:'20px'}}>
                        <Stack
                            component=""
                            sx={{
                                width: '768px',
                                margin: '0 auto'
                            }}
                            spacing={2}
                        >
                            <span style={{fontFamily:'initial', fontWeight:'bold', fontSize:'30px', textAlign:'center'}}>
                                Information
                            </span> 
                            <div className="grid grid-cols-2 gap-4">
                                <div>
                                    <input
                                        disabled
                                        className='inputInfo'
                                        title='This information cannot be edited'
                                        type='email'
                                        id = 'email'
                                        name = 'emal'
                                        required
                                        value={email_register}> 
                                    </input>
                                </div>
                                <div>
                                    <input 
                                        placeholder='Name'
                                        type='text'
                                        id = 'name'
                                        name = 'name'
                                        required    
                                        onChange={formik.handleChange}
                                        className="inputInfo"                                        >
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
                                        className="inputInfo"   
                                    >
                                    </input>
                                    {formik.errors.address &&(
                                        <p id='errorMsg'>{formik.errors.address}</p>
                                    )}
                                </div>
                                <div>
                                    <input 
                                        placeholder='Phonenumber'
                                        type='text'
                                        id = 'phone'
                                        name = 'phone'
                                        required    
                                        onChange={formik.handleChange}
                                        className="inputInfo">
                                    </input>
                                    {formik.errors.phone &&(
                                        <p id='errorMsg'>{formik.errors.phone}</p>
                                    )}
                                </div>
                                <div>
                                    <select id= 'gender' name = 'gender' onChange={formik.handleChange} className="inputInfo">
                                        <option value={''}>-Select gender-</option>
                                        <option value={'MALE'}>Male</option>
                                        <option value={'FEMALE'}>Female</option>
                                    </select>
                                    {formik.errors.gender &&(
                                        <p id='errorMsg'>{formik.errors.gender}</p>
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
                                        className="inputInfo">
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
                                        className="inputInfo"   
                                    >
                                    </input>
                                    {formik.errors.confirm &&(
                                        <p id='errorMsg'>{formik.errors.confirm}</p>
                                    )}
                                </div>
                                <div>
                                    <input 
                                        placeholder='Birthday'
                                        type='date'
                                        id = 'birthday'
                                        name = 'birthday'
                                        required    
                                        onChange={formik.handleChange}
                                        className="inputInfo">
                                    </input>
                                    {formik.errors.birthday &&(
                                        <p id='errorMsg'>{formik.errors.birthday}</p>
                                    )}
                                </div>
                            </div> 
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
                                onClick={() => { submit()}}
                            >
                                SUBMIT
                            </button>
                        </Stack>
                    </Container>
                </form>
        </React.Fragment>
    )
}

export default ConfirmPass