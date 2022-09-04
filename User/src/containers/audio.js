import * as React from 'react';
import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';
import ReactPlayer from 'react-player';
import List from '@mui/material/List';
import ListItemButton from '@mui/material/ListItemButton';
import ListSubheader from '@mui/material/ListSubheader';
import '../App.css';
import '../assets/style/audio.css';
import { useState, useEffect } from "react";
import {useParams} from "react-router-dom";

const Music = [
    {
      "id": 1,
      "name": "Monkey Banana Dance",
      "link": "https://www.youtube.com/watch?v=ubmiT8JKeRU&t=35s",
    },
    {
        "id": 2,
        "name": "Finger Family Song",
        "link": "https://www.youtube.com/watch?v=VH2FBHGDqMU",
    },
    {
        "id": 3,
        "name": "Yes Yes Playground Song",
        "link": "https://www.youtube.com/watch?v=YxiY3JS880U",
    },
    {
        "id": 4,
        "name": "Bath Song",
        "link": "https://www.youtube.com/watch?v=ewIaJ5laDM0",
    },
    {
        "id": 5,
        "name": "Wheels on the Bus (Play Version)! ",
        "link": "https://www.youtube.com/watch?v=1rFXLztqMSw",
    },
];

const Story = [
    {
      "id": 1,
      "name": "Ba Chú Heo Con - Truyện cổ tích - Truyện Tiếng Anh",
      "link": "https://www.youtube.com/watch?v=ZVKp5Dq5uqk",
    },
    {
        "id": 2,
        "name": "THE PRINCE WHO WASN’T HUNGRY",
        "link": "https://www.youtube.com/watch?v=8nidy47jBoQ",
    },
    {
        "id": 3,
        "name": "Truyện Cổ Tích : CINDERELLA - CÔ BÉ LỌ LEM",
        "link": "https://www.youtube.com/watch?v=SKOQW3BVEJE",
    },
];

function Audio(){    
    const params = useParams()
    const [audio, setAudio] = useState([]);
    const [links,setLinks] = useState()

    useEffect(() => {
        console.log(params)
        switch (params.name) {
            case 'Music':
                break;
            case 'Story':
                break;
            default:
                setAudio(Music);
                break;
        }
    },[params]);
    
    
    function now(current){
        return(
            setLinks(current.link),
            console.log(links)
        )
    }
    
    return(
        <React.Fragment>
            <div style={{minHeight: 'calc(100vh - 250px)'}}>
                <Container maxWidth='xl'>
                    <Grid container spacing={2} style={{fontFamily:'initial',justifyItems: 'center'}}>
                        <Grid item xs={2} style={{textAlign:'center',display:'flex', justifyContent:'center', paddingTop:'50px'}}>
                            <div>
                                <button className='btnaudio' onClick={()=>Music()}> Music </button>
                                <br/>
                                <button className='btnaudio'  onClick={()=>Story()}> Story </button>
                            </div>
                        </Grid>
                        
                        <Grid item xs={3} style={{textAlign:'center',display:'flex', justifyContent:'center', paddingTop:'50px'}}>
                            <List
                                sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper', borderRadius:'20px', overflow:'hidden' }}
                                component="nav"
                                aria-labelledby="nested-list-subheader"
                                subheader={
                                    <ListSubheader component="div" id="nested-list-subheader" className='font' style={{fontSize:'30px'}}>
                                    Play List 
                                    </ListSubheader>
                                }
                            >
                                {audio.map((current) => (
                                    <ListItemButton onClick={()=>now(current)} key={current.id}>
                                        {current.name}
                                    </ListItemButton>
                                ))} 
                            </List>
                        </Grid>
                        
                        <Grid item xs={7} style={{textAlign:'center', display:'flex', justifyContent:'center', paddingTop:'50px'}}>
                            <div style={{borderRadius:'20px', overflow:'hidden'}}>
                                <ReactPlayer controls={true} url={links || '"https://www.youtube.com/embed/ptk68qC1woI"'}/>
                            </div>
                        </Grid>
                    </Grid>
                </Container>
            </div>
        </React.Fragment>
    )
};

export default Audio