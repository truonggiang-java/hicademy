import { Route, Routes, Navigate } from 'react-router-dom';
import './App.css';
import Audio from './containers/audio';
import Home from './containers/home';
import LogIn from './FormLogIn/login';
import Start from './containers/start';
import Test from './containers/test';
import LessonOne from './FormLearn/lessonOne';
import ConfirmInfo from './FormLogIn/confirmInfo';
import GetOTP from './FormLogIn/getOTP';
import VerifyOTP from './FormLogIn/verifyOTP';
import ConfirmEmail from './FormLogIn/confirmEmail';
import Footers from './Shared/footers';
import Navbar from './Shared/navbar';
import * as React from 'react';
import Selection from './FormLearn/selection';
import Profile from './FormInfo/profile';
import ChangePassword from './FormInfo/changePassword';
import GameBoard from './FormGame/GameBoard';
import ForgetPassword from './FormLogIn/forgetPassword';
import Background from './assets/image/background1.jpg';

function App() {  
  const authorize = localStorage.getItem("Authorization")
  return (
    <div style={{backgroundImage: `url(${Background})`,
      backgroundRepeat: 'no-repeat', backgroundPosition: 'center', width:"100%", backgroundSize:'cover'
    }} className="layout">
    {/* <div style={{backgroundImage: `url("https://bom.so/dQCwoW")`,  
      backgroundRepeat: 'no-repeat', backgroundPosition: 'center', width:"100%", backgroundSize:'cover'
    }} className="layout"> */}
      <Navbar />
      <div className="main">
        <Routes>
          <Route exac path='/' exact element={<Start/>} />
          <Route path='/Footers' element={<Footers/>}/>
          <Route path='/Navbar' element={<Navbar/>}/>
          <Route path='/ConfirmEmail' element={<ConfirmEmail/>}/>
          <Route path='/GetOTP' element={<GetOTP/>}/>
          <Route path='/VerifyOTP' element={<VerifyOTP/>}/>
          <Route path='/ConfirmInfo' element={<ConfirmInfo/>}/>
          <Route path='/Login' element={<LogIn/>}/>
          <Route path='/ForgetPassword' element={<ForgetPassword/>}/>
          <Route exact path='/Home' element={authorize ? <Home/> : <Navigate to='/login' replace/>}/>
          <Route exact path='/Selection' element={authorize ? <Selection/> : <Navigate to='/login' replace/>}/>
          <Route path='/LessonOne/:name' element={authorize ? <LessonOne/> : <Navigate to='/login' replace/>}/>
          <Route path='/Audio' element={authorize ? <Audio/> : <Navigate to='/login' replace/>}/>
          <Route path='/Test' element={authorize ? <Test/> : <Navigate to='/login' replace/>}/>
          <Route path='/Profile' element={authorize ? <Profile/> : <Navigate to='/login' replace/>}/>
          <Route path='/ChangePassword' element={authorize ? <ChangePassword/> : <Navigate to='/login' replace/>}/>
          <Route path='/GameBoard' element={authorize ? <GameBoard/> : <Navigate to='/login' replace/>}/>
          <Route path='*' element={<Navigate to='/login' replace/>} />
        </Routes>
      </div>
      <Footers />
    </div>
  );
}

export default App;
