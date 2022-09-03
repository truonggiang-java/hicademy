import React from 'react';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';

export default function Footers() {
    return (
        <React.Fragment>
        <div className="footer" style={{background:'#1CB0F6',/* position:'fixed',bottom:'0',left:'0',right:'0'*/}}>
            <Container maxWidth='xl' >
                <Grid container style={{textAlign:'center', background:'#1CB0F6', color:'white', fontFamily:'initial'}}>
                    <Grid item xs={3} style={{margin:'auto'}}>
                        <img 
                            src={require('../assets/image/man.png')} alt='Logo'  
                            style={{display:'inline-block',width: '35px'}}
                        />
                        <span style={{marginLeft:'10px'}}>Founder: LeMinhHieu</span>
                    </Grid>
                    <Grid item xs={2} style={{display:'inline-block',margin:'auto',textAlign:'center',alignItems: 'center', justifyItems: 'center', width:'170px'}}>
                        <img 
                            src={require('../assets/image/fbIcon.png')} alt='Logo'  
                            style={{display:'inline-block',width: '35px'}}
                        />
                        <span style={{marginLeft:'10px'}}>minnhieu</span>
                    </Grid>
                    <Grid item xs={2} style={{display:'inline-block',margin:'auto',textAlign:'center',alignItems: 'center', justifyItems: 'center', width:'170px'}}>
                        <img 
                            src={require('../assets/image/insIcon.png')} alt='Logo'  
                            style={{display:'inline-block',width: '35px'}}
                        />
                        <span style={{marginLeft:'10px'}}>minnhieu</span>
                    </Grid>
                    <Grid item xs={5} style={{margin:'auto'}}>
                    <img 
                            src={require('../assets/image/map.png')} alt='Logo'  
                            style={{display:'inline-block',width: '35px'}}
                        />
                        <span style={{marginLeft:'10px'}}>Address: Thuy Phuong, North Tu Liem, Ha Noi</span>
                    </Grid>
                </Grid>
            </Container>
        </div>
        </React.Fragment>
    )
}