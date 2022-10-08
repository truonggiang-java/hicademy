import * as React from 'react';
import "bootstrap/dist/css/bootstrap.css";
import { useState, useEffect } from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick-theme.css";
import "slick-carousel/slick/slick.css";
import "../assets/style/selection.css";
// import { useSpeechSynthesis } from "react-speech-kit";
import {useParams} from "react-router-dom"
import axios from '../utils/axios';
import '../assets/style/lessonOne.css';
import {Link} from 'react-router-dom';

function LessonOne(props) {
    // const { speak, voices } = useSpeechSynthesis(); 
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
                        {suggestions.map((current, index) => (
                            <div key={current.id}>
                                {/* <Link to={`/MiniLesson/${current.id}`} style={{textDecoration:"none", fontSize:"large"}}> */}
                                <Link to={`/MiniLesson/${current.id}`} style={{textDecoration:"none", fontSize:"large"}}>
                                    <div className="card">
                                        <img
                                        alt={"lesson here"}
                                        src={`${current.link}`}
                                        height={400}
                                        width={400}
                                        />
                                        <div id='infor' data={index}>
                                            <span className='nameLesson'> 
                                                Lesson: {current.name} 
                                            </span>
                                        </div>
                                    </div>
                                </Link>
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