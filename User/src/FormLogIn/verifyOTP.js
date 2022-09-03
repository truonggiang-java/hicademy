import * as React from 'react';
import Stack from '@mui/material/Stack';
import Container from '@mui/material/Container';
import {Link } from "react-router-dom";

function VerifyOTP(){
    return (
        <React.Fragment>
            <Container maxWidth="sm" style={{textAlign:'center',minHeight:'calc(100vh - 100px - 133px - 39px)'}}>
                <Stack
                    component="form"
                    sx={{
                        width: '350px',
                        margin: '0 auto',
                        paddingTop: '50px',
                    }}
                >
                    <span style={{fontFamily:'initial', fontWeight:'bold', fontSize:'30px', textAlign:'center',marginBottom:'10px'}}>
                        Register
                    </span>
                    <input placeholder='OTP' style={{border: '1px solid black', borderRadius:'20px', padding:'8px 12px',marginBottom:'10px'}}></input>
                    <Link to="/confirmInfo">
                        <button style={{color:'white',margin:'15px auto',backgroundColor:'#1CB0F6',border: '1px solid black', width:'350px', height:'42px', borderRadius:'27px',}}>
                            VERIFY OTP
                        </button>
                    </Link>
                </Stack>
            </Container>
        </React.Fragment>
    )
}
 export default VerifyOTP