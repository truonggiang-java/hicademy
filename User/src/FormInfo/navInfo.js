import * as React from 'react';
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import List from '@mui/material/List';
import ListItemButton from '@mui/material/ListItemButton';
import ListSubheader from '@mui/material/ListSubheader';
import Divider from '@mui/material/Divider';
import { Link } from 'react-router-dom';
import LibraryMusicIcon from '@mui/icons-material/LibraryMusic';
import Grid from '@mui/material/Grid';

export default function NavInfo() {
  const [state, setState] = React.useState({
    left: false,
  });

  const toggleDrawer = (anchor, open) => (event) => {
    if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
      return;
    }

    setState({ ...state, [anchor]: open });
  };
  
  const list = (anchor) => (
      <Box
          sx={{ width: anchor === 'top' || anchor === 'bottom' ? 'auto' : 200 }}
          role="presentation"
          onClick={toggleDrawer(anchor, false)}
          onKeyDown={toggleDrawer(anchor, false)}
      >
          <List
              sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}
              component="nav"
              aria-labelledby="nested-list-subheader"
              subheader={
                  <ListSubheader component="div" id="nested-list-subheader" style={{fontSize:'22px'}}>
                    Menu List Items
                  </ListSubheader>
              }
          >
              <ListItemButton>
                <Link to="/home" id='a'>
                    <img 
                        src={require('../assets/image/home.png')} alt='Logo'  
                        style={{width: '50px', height:'50px', marginRight:'10px',display:'inline'}}
                    />
                    <p style={{ display:'inline'}}>Home</p>
                </Link>
              </ListItemButton>

              <ListItemButton>
                <Link to="/Audio" id='a'>
                  <img 
                      src={require('../assets/image/story.png')} alt='Logo'  
                      style={{width: '50px', height:'50px', marginRight:'10px',display:'inline'}}
                  />
                  <p style={{ display:'inline'}}>Story</p>
                </Link>
              </ListItemButton>

              <ListItemButton>
                <Link to="/selection" id='a'>
                  <img 
                      src={require('../assets/image/lesson.png')} alt='Logo'  
                      style={{width: '50px', height:'50px', marginRight:'10px',display:'inline'}}
                  />
                  <p style={{ display:'inline'}}>Lesson</p>
                </Link>
              </ListItemButton>

              <ListItemButton>
                <Link to="/Audio" id='a'>
                  <LibraryMusicIcon style={{width: '50px', height:'50px', display:'inline'}}/>
                  <p style={{ display:'inline',marginLeft:'8px'}}>The song</p>
                </Link>
              </ListItemButton>

              <ListItemButton>
                <Link to="/Profile" id='a'>
                  <img 
                      src={require('../assets/image/profile.png')} alt='Logo'  
                      style={{width: '50px', height:'50px', marginRight:'10px',display:'inline'}}
                  />
                  <p style={{ display:'inline'}}> Profile</p>
                </Link>
              </ListItemButton>

              <Divider />

              <ListItemButton>
                <Link to="/" id='a'>
                  <img 
                    src={require('../assets/image/logout.png')} alt='Logo'  
                    style={{width: '50px', height:'50px', marginRight:'10px', display:'inline'}}
                  />
                  <p style={{ display:'inline'}}> Log Out</p>
                </Link>
              </ListItemButton>
          </List>
        </Box>
  );

  return (
    <div style={{/*background:'#FFFF99'*/height:'170px'}}>
      {['left'].map((anchor) => (
        <React.Fragment key={anchor}>       
            <Grid container spacing={2} style={{display: 'flex',fontFamily:'initial'}}>
                <Grid item xs={9}>
                    <img 
                        onClick={toggleDrawer(anchor, true)}
                        src={require('../assets/image/logo.png')} alt='Logo'  
                        style={{maxHeight:'150px', paddingLeft:'25px', paddingTop:'15px',float:'left'}}
                    />
                    </Grid>
                    <Grid item xs={3} >
                        <Box sx={{
                                paddingTop: '30px',
                                height: '175px',
                                width: '100%',
                                textAlign: 'center'
                            }}
                        >
                            <Link to= "/ChangePass">
                                <button style={{background:'white',color: 'black',margin:'10px',border: '1px solid black', padding:'15px 32px', borderRadius:'27px'}}>
                                    Change Password
                                </button>
                            </Link>
                        </Box>
                    </Grid>
                </Grid>
          <Drawer
            anchor={anchor}
            open={state[anchor]}
            onClose={toggleDrawer(anchor, false)}
          >
            {list(anchor)}
          </Drawer>
        </React.Fragment>
      ))}
    </div>
  );
}