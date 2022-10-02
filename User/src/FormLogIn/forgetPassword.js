import * as React from 'react';
import Container from '@mui/material/Container';
import Stack from '@mui/material/Stack';
import * as Yup from 'yup';
import { useFormik } from 'formik';
import '../App.css'
import axios from '../utils/axios';

function ForgetPassword(){
    const formik = useFormik({
        initialValues:{
            email: '',
            name: '',
            address: '',
            phone:'',
            birthday: ''
        },
        validationSchema: Yup.object({
            name: Yup.string().required('Required').min(2, 'Must be 2 characters or more'),
            email: Yup.string().required('Required').matches(/^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/, 'Please enter a valid'),
            address: Yup.string().required('Required'),
            phone: Yup.string().required('Required').matches(/^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$/),
            birthday: Yup.string().required('Required')
        }),
        onSubmit: (values) => {
            console.log(values)
        },
    });

    const check = async () => {
        if ((!formik.values.email || formik.values.email === "") || (!formik.values.password || formik.values.password === '')) {
            alert('Please enter full information!')
        }
       
        try {
            // const res = await axios.post('/api/v2/customer/signin', {email: formik.values.email, password: formik.values.password})
            // if (res && res.data.code === 200) {
            //     localStorage.setItem("Authorization", res.data.data.token)
            //     localStorage.setItem("user_id", res.data.data.id)
            //     window.location.href = "http://localhost:3000/home" 
            // }         
        }
       catch(err) {
            alert('Information does not match!');
        }
    }

    return(
        <React.Fragment>
            <form style={{height: 'calc(100vh - 100px - 193px)'}} onSubmit={formik.handleSubmit}>
                <Container maxWidth="sm" style={{textAlign:'center'}}>
                    <Stack
                        component=""
                        sx={{
                            width: '350px',
                            margin: '0 auto',
                            width: "768px",
                            margin: "0 auto",
                        }}
                        spacing={2}
                    >
                        <span style={{fontFamily:'initial', fontWeight:'bold', fontSize:'30px', textAlign:'center'}}>
                            Information
                        </span>
                        <div className="col-span-1">
                            <div className="col-span-1 mt-6">
                                <input
                                    placeholder="Email"
                                    className="input w-full"
                                    type="text"
                                    id="email"
                                    name="email"
                                    required
                                    onChange={formik.handleChange}
                                    value={formik.values.email}
                                    style={{
                                    border: "1px solid black",
                                    borderRadius: "20px",
                                    padding: "8px 12px",
                                    }}
                                ></input>
                            {formik.errors.email && (
                                <p id="errorMsg">{formik.errors.email}</p>
                            )}
                            </div>
                            <div className="col-span-1 mt-6">
                            <input
                                className="input w-full"
                                placeholder="Name"
                                type="text"
                                id="name"
                                name="name"
                                required
                                onChange={formik.handleChange}
                                value={formik.values.name}
                                style={{
                                border: "1px solid black",
                                borderRadius: "20px",
                                padding: "8px 12px",
                                }}
                            ></input>
                            {formik.errors.name && (
                                <p id="errorMsg">{formik.errors.name}</p>
                            )}
                            </div>
                            <div className="col-span-1 mt-6">
                                <input
                                    placeholder="Address"
                                    className="input w-full"
                                    type="text"
                                    id="address"
                                    name="address"
                                    required
                                    onChange={formik.handleChange}
                                    value={formik.values.address}
                                    style={{
                                    border: "1px solid black",
                                    borderRadius: "20px",
                                    padding: "8px 12px",
                                    }}
                                ></input>
                            {formik.errors.password && (
                                <p id="errorMsg">{formik.errors.password}</p>
                            )}
                            </div>
                        </div>
                        <div className="col-span-1">
                            <div className="col-span-1 mt-6">
                            <input
                                className="input w-full"
                                placeholder="Phonenumber"
                                type="text"
                                id="phone"
                                name="phone"
                                required
                                onChange={formik.handleChange}
                                value={formik.values.phone}
                                style={{
                                border: "1px solid black",
                                borderRadius: "20px",
                                padding: "8px 12px",
                                }}
                            ></input>
                            {formik.errors.phone && (
                                <p id="errorMsg">{formik.errors.phone}</p>
                            )}
                            </div>
                            <div className="col-span-1 mt-6">
                            <input
                                className="input w-full"
                                placeholder="Birthday"
                                type="date"
                                id="birthday"
                                name="birthday"
                                required
                                onChange={formik.handleChange}
                                value={formik.values.birthday}
                                style={{
                                border: "1px solid black",
                                borderRadius: "20px",
                                padding: "8px 12px",
                                }}
                            ></input>
                            {formik.errors.birthday && (
                                <p id="errorMsg">{formik.errors.birthday}</p>
                            )}
                            </div>
                        </div>
                        <button
                            style={{
                            color: "white",
                            backgroundColor: "#1CB0F6",
                            border: "1px solid black",
                            width: "350px",
                            height: "42px",
                            borderRadius: "27px",
                            }}
                            type="submit"
                            onClick={() => {
                            check();
                            }}
                        >
                            SUBMIT
                        </button>
                    </Stack>
                </Container>
            </form>
        </React.Fragment>   
    )
}

export default ForgetPassword
