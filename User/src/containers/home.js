import * as React from 'react';
import Grid from '@mui/material/Grid';
import { Link } from 'react-router-dom';
import "../assets/style/home.css";

function Home() {
    return(
        <React.Fragment>
            <Grid container spacing={2} style={{fontFamily:'initial',justifyItems: 'center'}}>
                <Grid item xs={4} style={{textAlign:'center'}}>
                    <Link to="/Audio" id='a'>
                        <img
                            src={require('../assets/image/KeChuyen.png')}  alt=''
                            style={{ margin:'0 auto', minHeight:'calc(100vh - 100px - 133px - 250px)'}}
                        />
                        <div style={{paddingBottom:"75px" ,fontFamily:'cursive', fontWeight:'bold', fontSize:'30px', textAlign:'center', textDecoration:'none'}}>Story and Song</div>
                    </Link>
                </Grid>
                <Grid item xs={4} style={{textAlign:'center'}}>
                    <Link to="/Selection" id='a'>
                        <img
                            src={require('../assets/image/BaiHoc.png')}  alt=''
                            style={{margin:'0 auto', minHeight:'calc(100vh - 100px - 133px - 250px)'}}
                        />
                        <div style={{paddingBottom:"75px" ,fontFamily:'cursive', fontWeight:'bold', fontSize:'30px', textAlign:'center'}} >Lesson</div>
                    </Link>
                </Grid>
                <Grid item xs={4} style={{textAlign:'center'}}>
                    <Link to="/GameBoard" id='a'>
                        <img
                            src={require('../assets/image/kidsplay.png')}  alt=''
                            style={{margin:'0 auto', minHeight:'calc(100vh - 100px - 133px - 250px)'}}
                        />
                        <div style={{paddingBottom:"75px" ,fontFamily:'cursive', fontWeight:'bold', fontSize:'30px', textAlign:'center'}} >Mini Game</div>
                    </Link>
                </Grid>
            </Grid>
        </React.Fragment>
    )
}

export default Home