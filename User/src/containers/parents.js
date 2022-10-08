import React from 'react';
import Grid from "@mui/material/Grid";
import Container from '@mui/material/Container';
import "../assets/style/parents.css";

export default function parents() {
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
                <div className=''>
                    <input placeholder='Parents Name' className='mh3'></input>
                    <textarea placeholder='Content to reflect' className='mh4'></textarea>
                </div>
              </div>
            </Grid>
          </Grid>
        </Container>
      </div>
    </React.Fragment>
  )
}
