import * as React from 'react';
import "bootstrap/dist/css/bootstrap.css";
import { useState, useEffect } from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick-theme.css";
import "slick-carousel/slick/slick.css";
import "../assets/style/selection.css";
import { useSpeechSynthesis } from "react-speech-kit";
import {useParams} from "react-router-dom"
import axios from '../utils/axios';
import '../assets/style/lessonOne.css';
import SweetAlert2 from 'react-sweetalert2';

function LessonOne(props) {
    const { speak, voices } = useSpeechSynthesis(); 
    const [suggestions, setSuggestions] = useState([]);
    const params = useParams();
    const [swalProps, setSwalProps] = useState({});
    function handleClick(){
            setSwalProps({
            show: true,
            title: 'Example',
            text: 'Hello World',
        }); 
        console.log('bbbbb')
    }

    useEffect(() => {
        console.log(params)
        const fetchData = async () => {
            const response = await axios.get(`/api/v2/miniLesson/findByIdLesson/${params.name}`);
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
        afterChange: (current) => {
            console.log(current)
            console.log(suggestions.listDlFileEntryDto.length)
            if((current+1) === suggestions.listDlFileEntryDto.length){
                console.log("aaa");
                handleClick()
            }
        },
    
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
                        {suggestions.listDlFileEntryDto.map((current, index) => (
                            <div key={current.id}>
                                <div className="card">
                                    <img
                                    alt={"users here"}
                                    src={`${current.link}`}
                                    height={400}
                                    width={400}
                                    />
                                    <div id='infor' data={index}>
                                        <span className='nameLesson'> 
                                            {current.name} 
                                        </span>
                                        <img 
                                            onClick={() => speak({ text:(current.name), voice: voices[1]})}
                                            src={require('../assets/image/icon_loa.png')} alt='Logo' className='imgLesson'  
                                        />
                                    </div>
                                </div>
                            </div>
                        ))}
                        </Slider>
                    )}
                    <SweetAlert2 {...swalProps}
                        didOpen={() => {
                            // run when swal is opened...
                        }}
                        didClose={() => {
                            // run when swal is closed...
                        }}
                        onConfirm={result => {
                            // run when clieked in confirm and promise is resolved...
                        }}
                        onError={error => {
                            // run when promise rejected...
                        }}
                        onResolve={result => {
                            // run when promise is resolved...
                        }}
                    />
                </div>
            </div>
        </React.Fragment>
    )
}

export default LessonOne