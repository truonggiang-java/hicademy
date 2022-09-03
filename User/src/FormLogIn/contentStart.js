import * as React from 'react';
import Grid from '@mui/material/Grid';

export default function ContentStart() {
    return (
        <div>
            <Grid container >
                <Grid item xs={6} >
                    <img
                        src={require('../assets/image/cau_be.png')} alt='Logo'
                        style={{ width: '100%', minHeight: 'calc(100vh - 100px - 253px)' }}
                    />
                </Grid>
                <Grid item xs={6} style={{ fontFamily: 'cursive', fontWeight: 'bold', fontSize: '40px', textAlign: 'center', marginTop: '150px' }}>
                    <span>
                        The free, fun and
                        <br />
                        effective way
                        <br />
                        to learn a language!
                    </span>
                </Grid>
            </Grid>
        </div>
    )
}