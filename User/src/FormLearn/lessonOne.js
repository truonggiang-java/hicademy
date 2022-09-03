import * as React from 'react';
import "bootstrap/dist/css/bootstrap.css";
import { useState, useEffect } from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick-theme.css";
import "slick-carousel/slick/slick.css";
import "../assets/style/selection.css";
import image1 from '../assets/image/conlon.png';
import image2 from '../assets/image/conech.png';
import image3 from '../assets/image/concua.png';
import image4 from '../assets/image/conga.png';
import '../assets/style/lessonOne.css';
import { useSpeechSynthesis } from "react-speech-kit";
import {useParams} from "react-router-dom"

const Animal = [
    {
      "id": 1,
      "nameLesson": "Animal",
      "name": "Pig",
      "image": image1,
    },
    {
        "id": 2,
        "nameLesson": "Animal",
        "name": "Frog",
        "image": image2,
    },
    {
        "id": 3,
        "nameLesson": "Animal",
        "name": "Crab",
        "image": image3,
    },
    {
        "id": 4,
        "nameLesson": "Animal",
        "name": "Rooster",
        "image": image4,
    },
    {
        "id": 4,
        "nameLesson": "Color",
        "name": "Rooster",
        "image": image4,
    }
]

const Color = [
    {
      "id": 1,
      "nameLesson": "Color",
      "name": "Green",
      "image": image1,
    },
    {
        "id": 2,
        "nameLesson": "Color",
        "name": "Red",
        "image": image2,
    },
]

const Tree = [
    {
      "id": 1,
      "nameLesson": "Tree",
      "name": "aaaa",
      "image": image1,
    },
    {
        "id": 2,
        "nameLesson": "Tree",
        "name": "bbbb",
        "image": image2,
    },
]

function LessonOne(props) {
    const { speak, voices } = useSpeechSynthesis(); 
    const [suggestions, setSuggestions] = useState([]);
    const params = useParams()

    useEffect(() => {
        console.log(params)
        switch (params.name) {
            case 'Animal':
                setSuggestions(Animal)
                break;
            case 'Color':
                setSuggestions(Color)
                break;
            case 'Tree':
                setSuggestions(Tree)
                break;
            default:
                setSuggestions(Animal);
                break;
        }
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
                                    src={`${current.image}`}
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