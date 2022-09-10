import * as React from 'react';
import Stack from '@mui/material/Stack';
import Container from '@mui/material/Container';
import {Link } from "react-router-dom";
import {useNavigate } from "react-router-dom";
import axios from '../utils/axios';

function ConfirmEmail() {
    const [values, setValues] = React.useState('');
    const history = useNavigate();
    const confirmEmail = async (e) => {
        e.preventDefault();
        try {
            const res = await axios.get(`/api/v2/verification/sendEmail?email=${values}`);
            console.log(123, res);
            if(res) {
                history(
                    '/verifyOTP',
                   { state: {name: 'Xyz'}})
            }
        } catch(err) 
        {
            console.log(err.response.data)
        }
    }

    const handleChanges = (e) => {
      setValues(e.target.value);
    }

    return(
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
                    <input placeholder='Enter Email' style={{border: '1px solid black', borderRadius:'20px', padding:'8px 12px',marginBottom:'10px'}} onChange={handleChanges} value={values}></input>
                    <Link to="/verifyOTP">
                        <button style={{color:'white',margin:'15px auto',backgroundColor:'#1CB0F6',border: '1px solid black', width:'350px', height:'42px', borderRadius:'27px',}} onClick={confirmEmail}>
                            Comfirm
                        </button>
                    </Link>
                </Stack>
            </Container>
        </React.Fragment>
    )
}

export default ConfirmEmail
