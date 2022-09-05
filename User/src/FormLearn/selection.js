import "bootstrap/dist/css/bootstrap.css";
import * as React from 'react';
import { useState, useEffect } from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick-theme.css";
import "slick-carousel/slick/slick.css";
import "../assets/style/selection.css";
// import image1 from '../assets/image/animal.png';
// import image2 from '../assets/image/shape.png';
// import image3 from '../assets/image/color.png';
import '../App.css';
import {Link} from 'react-router-dom';
import axios from '../utils/axios';

function Selection() {
    // const usedata = [
    //     {
    //       "id": 1,
    //       "nameLesson": "Animal",
    //       "image": image1,
    //     },
    //     {
    //       "id": 2,
    //       "nameLesson": "Shape",
    //       "image": image2,
    //     },
    //     {
    //       "id": 3,
    //       "nameLesson": "Color",
    //       "image": image3,
          
    //     },
    //     {
    //       "id": 4,
    //       "nameLesson": "Tree",
    //       "image": image1,
    //     },
    //     {
    //       "id": 5,
    //       "nameLesson": "Weather",
    //       "image": image2,
    //     },
    //   ];
    const [suggestions, setSuggestions] = useState([]);
    // const [selection, setSelection] = useState([]);

    useEffect(()=> {
      const fetchData = async () => {
        const response = await axios.get('/api/v2/course/findAllCourse');
        console.log('responsen', response.data);
        if(response) {
          setSuggestions(response);
        }
      }
      fetchData();
    }, []);

    
    let settings = {
        infinite: false,
        speed: 1000,
        arrows: true,
        slidesToShow: 3,
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
    
    const [lessson,setLesson] = useState()
    function go(current){
      return(
        setLesson(current.nameLesson)
      )
    }

    return(
        <React.Fragment>
            <div style={{minHeight: 'calc(100vh - 270px)'}}>
                <div className="container">
                    {suggestions.length === 0 ? (
                        <div className="spinner-border" role="status">
                        <span className="sr-only">Loading...</span>
                        </div>
                    ) : (
                        <Slider {...settings}>
                        {suggestions.data.map((current) => (
                            <div key={current.id} onClick={()=>go(current)}>
                                <Link to={`/LessonOne/${current.id}`} style={{textDecoration:"none", fontSize:"large"}}>
                                  <div className="card">
                                      <img
                                      alt={"users here"}
                                      src={`${current.link}`}
                                      height={500}
                                      width={500}
                                      />
                                      {current.name}
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

export default Selection