// import * as React from 'react';
// import Container from '@mui/material/Container';
// import Stack from '@mui/material/Stack';
// import * as Yup from 'yup';
// import { useFormik } from 'formik';
// import '../App.css'
// import axios from '../utils/axios';

// function forgetPassword(){
//     const formik = useFormik({
//         initialValues:{
//             email: '',
//             password: '',
//         },
//         validationSchema: Yup.object({
//             // name: Yup.string().required('Required').min(4, 'Must be 4 characters or more'),
//             email: Yup.string().required('Required').matches(/^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/, 'Please enter a valid'),
//         }),
//         onSubmit: (values) => {
//             console.log(values)
//         },
//     });

//     const check = async () => {
//         if ((!formik.values.email || formik.values.email === "") || (!formik.values.password || formik.values.password === '')) {
//             alert('Please enter full information!')
//         }
       
//         try {
//             // const res = await axios.post('/api/v2/customer/signin', {email: formik.values.email, password: formik.values.password})
//             // if (res && res.data.code === 200) {
//             //     localStorage.setItem("Authorization", res.data.data.token)
//             //     localStorage.setItem("user_id", res.data.data.id)
//             //     window.location.href = "http://localhost:3000/home" 
//             // }         
//         }
//        catch(err) {
//             alert('Information does not match!');
//         }
//     }

//     return(
//         <React.Fragment>
//             <form style={{height: 'calc(100vh - 100px - 193px)'}} onSubmit={formik.handleSubmit}>
//                 <Container maxWidth="sm" style={{textAlign:'center'}}>
//                     <Stack
//                         component=""
//                         sx={{
//                             width: '350px',
//                             margin: '0 auto'
//                         }}
//                         spacing={2}
//                     >
//                         <span style={{fontFamily:'initial', fontWeight:'bold', fontSize:'30px', textAlign:'center'}}>
//                             Log In
//                         </span>
                    
//                         <div>
//                             <input 
//                                 type='email'
//                                 id = 'email'
//                                 name = 'email'
//                                 value={formik.values.email}
//                                 onChange={formik.handleChange} 
//                                 placeholder='Email'
//                                 style={{border: '1px solid black', borderRadius:'20px', padding:'8px 12px',width:'350px',}}>
//                             </input>
//                             {formik.errors.email &&(
//                                 <p id='errorMsg'>{formik.errors.email}</p>
//                             )}
//                         </div>

//                         <div>
//                             <input 
//                                 type='password'
//                                 id = 'password'
//                                 name = 'password'
//                                 required
//                                 value={formik.values.password}
//                                 onChange={formik.handleChange}
//                                 placeholder='Password' 
//                                 style={{border: '1px solid black', borderRadius:'20px', padding:'8px 12px',width:'350px',}}>
//                             </input>
//                             {formik.errors.password &&(
//                                 <p id='errorMsg'>{formik.errors.password}</p>
//                             )}
//                         </div>
                        
                        
//                         <button 
//                             onClick={() => { check()}}
//                             type = "submit"
//                             style={{
//                                 color:'white',
//                                 backgroundColor:'#1CB0F6',
//                                 border: '1px solid black', 
//                                 width:'350px', 
//                                 height:'42px',
//                                 borderRadius:'27px',
//                             }}>
//                                 Go !
//                         </button>
//                     </Stack>
//                 </Container>
//             </form>
//         </React.Fragment>   
//     )
// }

// export default forgetPassword
