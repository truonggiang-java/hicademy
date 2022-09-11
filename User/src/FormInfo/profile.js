import { useFormik } from "formik";
import * as Yup from "yup";
import * as React from "react";
import { useState } from "react";
import "../assets/style/profile.css";
import Form from "react-bootstrap/Form";
import Stack from "@mui/material/Stack";
import Container from "@mui/material/Container";
import { Link } from "react-router-dom";
export default function Profile() {
  const infoUser = {
    email: "Hieule159@gmail.com",
    name: "MinhHieu",
    password: "Abc123456",
    gender: "",
    phone: "",
    address: "",
    birthday: "",
  };

  const getInfo = () => {
    return infoUser;
  };

  let [dataUser] = useState(getInfo());
  let [image, setImage] = useState(null);
  console.log(image);
  const formik = useFormik({
    initialValues: dataUser,
    validationSchema: Yup.object({
      name: Yup.string()
        .required("Required")
        .min(2, "Must be 2 characters or more"),
    }),
    onSubmit: (values) => {
      console.log(values);
    },
  });

  function check() {
    return <div></div>;
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
                  <img style={{width: '120px', height: '120px'}} src={require('../assets/image/logo.png')} />
                </div>
                <div className="col-span-1 mt-6">
                  <input type="file" />
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
