import { Route, Routes } from 'react-router-dom';
import './App.css';
import Audio from './containers/audio';
import Home from './containers/home';
import LogIn from './FormLogIn/login';
import NotFound from './containers/notFound';
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

function App() {  
  return (
    <div style={{background:'linear-gradient(#FFFF99, #FA6EFF)'}} className="layout">
      <Navbar />
      <div className="main">
        <Routes>
          <Route path='*' element={<NotFound />} />
          <Route exac path='/' exact element={<Start/>} />
          <Route path='/Footers' element={<Footers/>}/>
          <Route path='/ConfirmEmail' element={<ConfirmEmail/>}/>
          <Route path='/GetOTP' element={<GetOTP/>}/>
          <Route path='/VerifyOTP' element={<VerifyOTP/>}/>
          <Route path='/Navbar' element={<Navbar/>}/>
          <Route path='/ConfirmInfo' element={<ConfirmInfo/>}/>
          <Route exact path='/Home' element={<Home/>}/>
          <Route path='/Login' element={<LogIn/>}/>
          <Route exact path='/Selection' element={<Selection/>}/>
          <Route path='/LessonOne/:name' element={<LessonOne/>}/>
          <Route path='/Audio' element={<Audio/>}/>
          <Route path='/Test' element={<Test/>}/>
          <Route path='/Profile' element={<Profile/>}/>
          <Route path='/ChangePassword' element={<ChangePassword/>}/>
          <Route path='/GameBoard' element={<GameBoard/>}/>
        </Routes>
      </div>
      <Footers />
    </div>
  );
}

export default App;
