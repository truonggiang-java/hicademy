import React from 'react';
import Grid from "@mui/material/Grid";
import Container from '@mui/material/Container';
import "../assets/style/parents.css";
import axios from "../utils/axios";
import {useState} from 'react';
import { Alert } from 'bootstrap';

function Parents() {
  const [name, setName] = useState('');
  const [feedBack, setFeedBack] = useState('');
  
  const handleChangeName = event => {
    setName(event.target.value);
  };
  const handleChangeFb = event => {
    setFeedBack(event.target.value);
  };

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
        console.log(res)
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
                        <h5 className='mh'>Name: <span className='mh2'>Hiếu</span></h5>
                        <h5 className='mh' style={{display:"flex"}}>Star: 99 <img src={require('../assets/image/star.png')} className="star"/></h5>
                    </div>
                    <div className="col-span-1">
                        <img src={require('../assets/image/animal.png')} className="avatar"/>
                    </div>
                </div>
                <div><h5 className='mh1'>Lessons learned:</h5></div> 
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
