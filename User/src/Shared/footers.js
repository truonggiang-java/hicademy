import React from 'react';
import "../assets/style/footer.css";
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';

export default function Footers() {
    return (
        <React.Fragment>
        <div className="footer" style={{background: 'linear-gradient(90deg, #0072ff 0%, #00d4ff 100%)', position:'absolute',bottom:'0',left:'0',right:'0'}}>
            <Container maxWidth='xl' >
                <Grid container style={{textAlign:'center', color:'white', fontFamily:'initial'}}>
                    <Grid item xs={3} style={{margin:'auto'}}>
                        <img id='items'
                            src={require('../assets/image/man.png')} alt='Logo'  
                        />
                        <span className='text-f'>Founder: LeMinhHieu</span>
                    </Grid>
                    <Grid item xs={2} style={{display:'inline-block',margin:'auto',textAlign:'center',alignItems: 'center', justifyItems: 'center', width:'170px'}}>
                        <img id='items'
                            src={require('../assets/image/fbIcon.png')} alt='Logo'  
                        />
                        <span className='text-f'>minnhieu</span>
                    </Grid>
                    <Grid item xs={2} style={{display:'inline-block',margin:'auto',textAlign:'center',alignItems: 'center', justifyItems: 'center', width:'170px'}}>
                        <img id='items'
                            src={require('../assets/image/insIcon.png')} alt='Logo'  
                        />
                        <span className='text-f'>minnhieu</span>
                    </Grid>
                    <Grid item xs={5} style={{margin:'auto'}}>
                        <img id='items'
                            src={require('../assets/image/map.png')} alt='Logo'  
                        />
                        <span className='text-f'>Address: Thuy Phuong, North Tu Liem, Ha Noi</span>
                    </Grid>
                </Grid>
            </Container>
        </div>
        </React.Fragment>
    )
}