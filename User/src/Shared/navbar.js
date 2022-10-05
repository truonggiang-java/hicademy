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
import Avatar from '@mui/material/Avatar';
import Stack from '@mui/material/Stack';
import axios from '../utils/axios';
import "../assets/style/navbar.css";
import LibraryMusicIcon from '@mui/icons-material/LibraryMusic';
import HomeIcon from '@mui/icons-material/Home';
import MenuBookIcon from '@mui/icons-material/MenuBook';
import PermContactCalendarIcon from '@mui/icons-material/PermContactCalendar';
import SportsEsportsIcon from '@mui/icons-material/SportsEsports';
import LogoutIcon from '@mui/icons-material/Logout';

export default function Navbar() {
  const [state, setState] = React.useState({
    left: false,
  });

    const location = useLocation();
    console.log('location', location);

    const hideTooge = ['/','/login','/confirmEmail','/confirmPassword','/verifyOTP','/confirmPass','/confirmInfo', '/ForgetPassword'];

    const visibleLogin = ['/','/login','/confirmEmail','/confirmPassword', '/ForgetPassword'];
  const [user, setUser] = React.useState(null)
  const getUserInfo = async () => {
    const user_id = localStorage.getItem("user_id")
    if (user_id && user_id !== '') {
      try {
        const res = await axios.get(`/api/v2/customer/findById?id=${user_id}`)
        if (res.status === 200) {
          setUser(res.data)
        }
      } catch (err) {
        console.log('err getUserById');
      }
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
                  <ListSubheader component="div" id="nested-list-subheader" style={{fontSize:'22px', fontWeight:'bold'}}>
                    Menu List Items
                  </ListSubheader>
              }
          >
              <ListItemButton>
                <Link to="/Home" id='a' style={{width: '100%'}}>
                  <HomeIcon style={{width: '50px', height:'50px', display:'inline'}}/>
                    <p style={{ display:'inline',marginLeft:'8px'}}>Home</p>
                </Link>
              </ListItemButton>

              <ListItemButton>
                <Link to="/GameBoard" id='a' style={{width: '100%'}}>
                  <SportsEsportsIcon style={{width: '50px', height:'50px', display:'inline'}}/>
                  <p style={{ display:'inline',marginLeft:'8px'}}>Mini Game</p>
                </Link>
              </ListItemButton>

              <ListItemButton>
                <Link to="/Selection" id='a' style={{width: '100%'}}>
                  <MenuBookIcon style={{width: '50px', height:'50px', display:'inline'}}/>
                  <p style={{ display:'inline',marginLeft:'8px'}}>Lesson</p>
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
                  <PermContactCalendarIcon style={{width: '50px', height:'50px', display:'inline'}}/>
                  <p style={{ display:'inline',marginLeft:'8px'}}>Profile</p>
                </Link>
              </ListItemButton>

              <Divider />

              <ListItemButton>
                <Link to="/" id='a' style={{width: '100%'}}>
                  <LogoutIcon style={{width: '50px', height:'50px', display:'inline'}}/>
                  <p style={{ display:'inline',marginLeft:'8px'}}>Logout</p>
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
        <MenuItem onClick={onLogout}>Log Out</MenuItem>
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
              { location.pathname === '/confirmEmail' || location.pathname === '/confirmPassword' || location.pathname === '/ForgetPassword' ? '' : (<div
              >
                  <Link to="/confirmEmail">
                      <button style={{backgroundColor:'white',color: 'black',margin:'10px',border: '1px solid black', padding:'15px 32px', borderRadius:'27px'}}>
                          CREATE ACCOUNT
                      </button>
                  </Link>
              </div>)}
              { location.pathname === '/confirmEmail' || location.pathname === '/confirmPassword'|| location.pathname === '/' || location.pathname === '/ForgetPassword' ? '' : (<div
              >
                  <Link to="/ForgetPassword">
                      <button style={{backgroundColor:'white',color: 'black',margin:'10px',border: '1px solid black', padding:'15px 32px', borderRadius:'27px'}}>
                          FORGET PASSWORD
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