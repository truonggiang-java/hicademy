import { useFormik } from "formik";
import * as Yup from "yup";
import * as React from "react";
import { useState, useEffect } from "react";
import "../assets/style/profile.css";
import Form from "react-bootstrap/Form";
import Stack from "@mui/material/Stack";
import Container from "@mui/material/Container";
import { Link } from "react-router-dom";
import axios_fetch from '../utils/axios';
import axios from 'axios';

export default function Profile() {
  const [profile, setProfile] = React.useState({
    "email" : "",
    "phone": "",
    "gender":"",
    "address":"",
    "name":"",
    "birthday":""
  })
  const getUserInfo = async () => {
    const user_id = localStorage.getItem("user_id")
    if (user_id && user_id !== '') {
      try {
        const res = await axios_fetch.get(`/api/v2/customer/findById?id=${user_id}`)
        if (res.status === 200) {
          console.log('res', res.data)
          setProfile(res.data)
          const {
            address, date, email, gender,
            link, name, phone,
          } = res.data
          formik.setFieldValue('email', email, false)
          formik.setFieldValue('name', name, false)
          formik.setFieldValue('address', address, false)
          formik.setFieldValue('phone', phone, false)
          formik.setFieldValue('gender', gender, false)
          formik.setFieldValue('birthday', date, false)
          setImage({link: link})
        }
      } catch (err) {
        console.log('err getUserById');
      }
    }
  }
  useEffect(()=> {
    getUserInfo()
  }, [])

  let [image, setImage] = useState(null);
  const formik = useFormik({
    initialValues: profile,
    validationSchema: Yup.object({
      name: Yup.string()
        .required("Required")
        .min(2, "Must be 2 characters or more"),
    }),
    onSubmit: (values) => {
      console.log(values);
    },
  });

  const check = async () => {
    const {email, name, address, phone, gender, birthday} = formik.values
    if ((!email || email.trim() === '') || (!name || name.trim() === '') || (!address || address.trim() === '') || (!phone || phone.trim() === '') || (!gender || gender.trim() === '') || (!birthday || birthday.trim() === '')) {
        return alert('Please enter full information!')
    }
    try {
        const res = await axios_fetch.put(`/api/v2/customer/updateCustomer/${profile.id}`, {...formik.values, phoneNumber: phone, date: birthday, idLogo: image.id, link: image.link});
        if(res) {
          alert('Update User Information Success')
        }
    } catch(err) 
    {
        console.log('ERR submit form register', err.response.data)
    }
}

  const onUpload = async (file) => {
    let newUploadFile = new FormData();
    newUploadFile.append('file', file[0]);
    const authorize = localStorage.getItem('Authorization')
    if (file[0]) {
      const res = await axios({
        method: 'post',
        url: 'http://localhost:8080/api/v2/customer/upload', 
        headers: {
          'Content-Type': 'multipart/form-data',
          "Authorization": `Basic ${authorize}`
        },
        data: newUploadFile,
        dataType: 'json'
      })
      setTimeout(() => {
        setImage(res.data)
      }, 500);
    }
  }
  return (
    <React.Fragment>
      <Form
        onSubmit={formik.handleSubmit}
        style={{ textAlign: "center", paddingTop: "50px" }}
      >
        <Container
          maxWidth="lg"
          style={{
            textAlign: "center",
            minHeight: "calc(100vh - 100px - 133px - 60px)",
            marginTop: "20px",
          }}
        >
          <Stack
            component=""
            sx={{
              width: "768px",
              margin: "0 auto",
            }}
            spacing={2}
          >
            <div className="grid grid-cols-3 gap-4">
              <div className="col-span-1">
                <div className="col-span-1 mt-6">
                  <input
                    disabled
                    className="input w-full"
                    title="This information cannot be edited"
                    type="email"
                    id="email"
                    name="emal"
                    required
                    value={formik.values.email}
                    style={{
                      border: "1px solid black",
                      borderRadius: "20px",
                      padding: "8px 12px",
                    }}
                  ></input>
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
                    placeholder="Gender"
                    type="text"
                    id="gender"
                    name="gender"
                    required
                    onChange={formik.handleChange}
                    value={formik.values.gender}
                    style={{
                      border: "1px solid black",
                      borderRadius: "20px",
                      padding: "8px 12px",
                    }}
                  ></input>
                  {formik.errors.gender && (
                    <p id="errorMsg">{formik.errors.gender}</p>
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
              <div className="col-span-1">
                <div className="col-span-1 mt-6">
                  <img style={{width: '120px', height: '120px'}} src={image?.link || require('../assets/image/logo.png')} />
                </div>
                <div className="col-span-1 mt-6">
                  <input type="file" onChange={(e) => onUpload(e.target.files)}/>
                </div>
              </div>
            </div>
            <div className="flex justify-between mt-8">
              <Link to="/changePassword">
                <button
                  style={{
                    color: "#1CB0F6",
                    backgroundColor: "white",
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
                  CHANGE PASSWORD
                </button>
              </Link>
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
            </div>
          </Stack>
        </Container>
      </Form>
    </React.Fragment>
  );
}
