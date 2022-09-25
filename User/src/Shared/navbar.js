import * as React from 'react';
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import List from '@mui/material/List';
import ListItemButton from '@mui/material/ListItemButton';
import ListSubheader from '@mui/material/ListSubheader';
import Divider from '@mui/material/Divider';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import {Link, useLocation } from 'react-router-dom';
import LibraryMusicIcon from '@mui/icons-material/LibraryMusic';
import Avatar from '@mui/material/Avatar';
import Stack from '@mui/material/Stack';
import axios from '../utils/axios';

export default function Navbar() {
  const [state, setState] = React.useState({
    left: false,
  });

    const location = useLocation();
    console.log('location', location);

    const hideTooge = ['/','/login','/confirmEmail','/confirmPassword','/verifyOTP','/confirmPass','/confirmInfo'];

    const visibleLogin = ['/','/login','/confirmEmail','/confirmPassword'];
  const [user, setUser] = React.useState(null)
  const getUserInfo = async () => {
    const user_id = localStorage.getItem("user_id")
    
    try {
      const res = await axios.get(`/api/v2/customer/findById?id=${user_id}`)
      if (res.status === 200) {
        setUser(res.data)
      }
    } catch (err) {
      console.log(1111);
    }
  
  }
  React.useEffect(()=> {
    getUserInfo()
  }, [])
  const toggleDrawer = (anchor, open) => (event) => {
    if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift') ) {
      return;
    }

    setState({ ...state, [anchor]: open });
  };
  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
    window.location.href = "http://localhost:3000/profile"
  };

  const onLogout = () => {
    console.log('logout');
    localStorage.removeItem('Authorization')
    window.location.href = "http://localhost:3000/login"        
  }
  
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
                <Link to="/Home" id='a' style={{width: '100%'}}>
                    <img 
                        src={require('../assets/image/home.png')} alt='Logo'  
                        style={{marginRight:'10px',display:'inline'}}
                        width="50"
                        height="50"
                    />
                    <p style={{ display:'inline'}}>Home</p>
                </Link>
              </ListItemButton>

              <ListItemButton>
                <Link to="/GameBoard" id='a' style={{width: '100%'}}>
                  <img 
                      src={require('../assets/image/story.png')} alt='Logo'  
                      style={{width: '50px', height:'50px', marginRight:'10px',display:'inline'}}
                  />
                  <p style={{ display:'inline'}}>Mini Game</p>
                </Link>
              </ListItemButton>

              <ListItemButton>
                <Link to="/Selection" id='a' style={{width: '100%'}}>
                  <img 
                      src={require('../assets/image/lesson.png')} alt='Logo'  
                      style={{width: '50px', height:'50px', marginRight:'10px',display:'inline'}}
                  />
                  <p style={{ display:'inline'}}>Lesson</p>
                </Link>
              </ListItemButton>

              <ListItemButton>
                <Link to="/Audio" id='a' style={{width: '100%'}}>
                  <LibraryMusicIcon style={{width: '50px', height:'50px', display:'inline'}}/>
                  <p style={{ display:'inline',marginLeft:'8px'}}>Audio</p>
                </Link>
              </ListItemButton>

              <ListItemButton>
                <Link to="/Profile" id='a' style={{width: '100%'}}>
                  <img 
                      src={require('../assets/image/profile.png')} alt='Logo'  
                      style={{width: '50px', height:'50px', marginRight:'10px',display:'inline'}}
                  />
                  <p style={{ display:'inline'}}> Profile</p>
                </Link>
              </ListItemButton>

              <Divider />

              <ListItemButton>
                <Link to="/" id='a' style={{width: '100%'}}>
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
    <div className="nav-header">
        <div>
            {['left'].map((anchor) => (
                <React.Fragment key={anchor}>
                    { hideTooge.includes(location.pathname) ? (<img
                        src={require('../assets/image/logo.png')} alt='Logo'
                        style={{maxHeight:'150px', paddingLeft:'25px', paddingTop:'15px',float:'left'}}
                    />): (<img
                        onClick={toggleDrawer(anchor, true)}
                        src={require('../assets/image/logo.png')} alt='Logo'
                        style={{maxHeight:'150px', paddingLeft:'25px', paddingTop:'15px',float:'left'}}
                    />)}
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
        {!hideTooge.includes(location.pathname) ? (
          <div className="flex items-center">
             <Stack direction="row" spacing={2}>
               <Avatar alt="Remy Sharp" src={user?.link || ''} onClick={handleClick}/>
             </Stack>
      <Menu
      elevation={0}
        id="fade-menu"
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
      >
        <MenuItem onClick={handleClose}>My account</MenuItem>
        <MenuItem onClick={onLogout}>Logout</MenuItem>
      </Menu>
    </div>
  
        ) : ''}
   
        { visibleLogin.includes(location.pathname) ? (
            <div className="navbar-login">
              { location.pathname === '/login' ? '' : (<div>
                  <Link to= "/login">
                      <button style={{background:'white',color: 'black',margin:'10px',border: '1px solid black', padding:'15px 32px', borderRadius:'27px'}}>
                          LOGIN
                      </button>
                  </Link>
              </div>) }
              { location.pathname === '/confirmEmail' || location.pathname === '/confirmPassword' ? '' : (<div
              >
                  <Link to="/confirmEmail">
                      <button style={{backgroundColor:'white',color: 'black',margin:'10px',border: '1px solid black', padding:'15px 32px', borderRadius:'27px'}}>
                          CREATE ACCOUNT
                      </button>
                  </Link>
              </div>)}
              { location.pathname === '/profile' ? (<div>
                  <Link to= "/ChangePass">
                      <button style={{background:'white',color: 'black',margin:'10px',border: '1px solid black', padding:'15px 32px', borderRadius:'27px'}}>
                          Change Password
                      </button>
                  </Link>
              </div>) : ''}
            </div>) : ''}
    </div>
  );
}