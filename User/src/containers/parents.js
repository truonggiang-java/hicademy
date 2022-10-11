import React, { useEffect } from 'react';
import Grid from "@mui/material/Grid";
import Container from '@mui/material/Container';
import "../assets/style/parents.css";
import axios from "../utils/axios";
import {useState} from 'react';
import { Alert } from 'bootstrap';
import axios_fetch from '../utils/axios';
import List from "@mui/material/List";

function Parents() {
  const [name, setName] = useState('');
  const [feedBack, setFeedBack] = useState('');
  
  const handleChangeName = event => {
    setName(event.target.value);
  };
  const handleChangeFb = event => {
    setFeedBack(event.target.value);
  };
  const [profile, setProfile] = React.useState({
    "nameU":"",
  })
  const [profile_1, setProfile_1] = React.useState({
    "name":"",
    "point":"",
    "lesson":[]
  })

  const getUserInfo_1 = async () => {
    const user_id = localStorage.getItem("user_id")
    if (user_id && user_id !== '') {
      try {
        const res_2 = await axios_fetch.get(`/api/v2/learn/findById/${user_id}`)
        if (res_2.status === 200) {
          console.log('res_2', res_2)
          setProfile_1(res_2)
        }
      } catch (err) {
        console.log('err getUserById');
      }
    }
  }

  const getUserInfo = async () => {
    const user_id = localStorage.getItem("user_id")
    if (user_id && user_id !== '') {
      try {
        const res_1 = await axios_fetch.get(`/api/v2/customer/findById?id=${user_id}`)
        if (res_1.status === 200) {
          // console.log('res', res_1.data)
          setProfile(res_1.data)
          
        }
      } catch (err) {
        console.log('err getUserById');
      }
    }
  }
  useEffect(()=> {
    getUserInfo();
    getUserInfo_1();
    console.log("profile_1",profile_1)
  }, [])

  const fb = async () => {
    try {
        const body = {
          name: name,
          description: feedBack,
          idUser: localStorage.getItem('user_id'),
        };
    
        const res = await axios.post(
            "/api/v2/feedBack/insertFeedBack",
            body
        );
        // console.log(res)
        if (res.data) {
          console.log("Gui feedback thanh cong!");   
        }
        } catch (err) {
          Alert("Send feedback successfully")
        }
  }

  return (
    <React.Fragment>
      <div style={{ minHeight: "calc(100vh - 250px)" }}>
        <Container maxWidth="xl">
          <Grid
            container
            spacing={2}
            style={{ fontFamily: "initial", justifyItems: "center" }}
          >
            <Grid item xs={4} className="grid_1">
              <div className="profile">
                <div className='tieude'>Student</div>

                <div className="grid grid-cols-2 fix">
                    <div className="col-span-1">
                        <h5 className='mh'>Name: <span className='mh2'>{profile.name}</span></h5>
                        <h5 className='mh' style={{display:"flex"}}>Start: {profile.point}<img src={require('../assets/image/star.png')} className="star"/></h5>
                    </div>
                    <div className="col-span-1">
                        <img src={profile?.link||require('../assets/image/animal.png')} className="avatar"/>
                    </div>
                </div>
                <div>
                  <h5 className='mh1'>Lessons learned:</h5>
                    {/* {res_2.lesson.map((current) => (
                      <List key={current.id}>
                        <div>
                          {current.name}
                        </div>
                      </List>
                    ))} */}
                </div>
              </div>
            </Grid>

            <Grid item xs={2} className="grid_1">
              
            </Grid>

            <Grid item xs={6}
              style={{
                textAlign: "center",
                display: "flex",
                justifyContent: "center",
                paddingTop: "50px",
              }}
            >
              <div className="feedback">
                <div className='tieude'>Feedback</div>
                <form>
                  <div className=''>
                      <div className="grid grid-cols-2">
                        <input placeholder='Parents Name' className='mh3' 
                          id="name"
                          name="name"
                          type="text"
                          onChange={handleChangeName}
                          value={name}
                          required
                        >
                          </input>
                        <div className="col-span-1">
                          <button className='btnfb' onClick={() => {fb()}} type='submit'>
                            Send Feedback
                          </button>
                        </div>
                      </div>
                      <textarea placeholder='Content to reflect' className='mh4'
                        id="feedBack"
                        name="feedBack"
                        type="text"
                        onChange={handleChangeFb}
                        value={feedBack}
                      >
                      </textarea>
                  </div>
                </form>
              </div>
            </Grid>
          </Grid>
        </Container>
      </div>
    </React.Fragment>
  )
}

export default Parents
