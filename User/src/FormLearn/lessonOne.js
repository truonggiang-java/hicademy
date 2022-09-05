import * as React from 'react';
import "bootstrap/dist/css/bootstrap.css";
import { useState, useEffect } from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick-theme.css";
import "slick-carousel/slick/slick.css";
import "../assets/style/selection.css";
import '../assets/style/lessonOne.css';
import { useSpeechSynthesis } from "react-speech-kit";
import {useParams} from "react-router-dom"
import axios from '../utils/axios';

function LessonOne(props) {
    const { speak, voices } = useSpeechSynthesis(); 
    const [suggestions, setSuggestions] = useState([]);
    const params = useParams()

    useEffect(() => {
        console.log(params)
        const fetchData = async () => {
            const response = await axios.get(`/api/v2/course/findLessonByIdCourse?id=${params.name}`);
            console.log('responsen', response.data);
            if(response) {
              setSuggestions(response.data);
            }
          }
          fetchData();
    },[params]);

    let settings = {
        infinite: false,
        speed: 1000,
        arrows: true,
        slidesToShow: 1,
        slidesToScroll: 1,
    
        responsive: [
          {
            breakpoint: 960,
            settings: {
              slidesToShow: 1,
              slidesToScroll: 1,
            },
          },
          {
            breakpoint: 480,
            settings: {
              slidesToShow: 1,
              slidesToScroll: 1,
            },
          },
        ],
      };

    
    console.log(suggestions)

    return(
        <React.Fragment>
            <div>
                <div className="container">
                    {suggestions.length === 0 ? (
                        <div style={{minHeight: 'calc(100vh - 100px)', justyfiContent:'center'}}>
                            <div className="spinner-border" role="status" style={{marginLeft:'500px', marginTop:'150px'}}>
                                <span className="sr-only">Loading...</span>
                            </div>
                        </div>
                    ) : (
                        <Slider {...settings}>
                        {suggestions.map((current) => (
                            <div key={current.id}>
                                <div className="card">
                                    <img
                                    alt={"users here"}
                                    src={`${current.link}`}
                                    height={400}
                                    width={400}
                                    />
                                    <div id='infor'>
                                        <span style={{display:'inline',fontFamily:'initial', fontWeight:'bold', fontSize:'35px'}}> 
                                            {current.name} 
                                        </span>
                                        <img 
                                            onClick={() => speak({ text:(current.name), voice: voices[1]})}
                                            src={require('../assets/image/icon_loa.png')} alt='Logo'  
                                            style={{width:'50px', display:'inline', marginLeft:'20px', marginBottom:'10px'}}
                                        />
                                    </div>
                                </div>
                            </div>
                        ))}
                        </Slider>
                    )}
                </div>
            </div>
        </React.Fragment>
    )
}

export default LessonOne