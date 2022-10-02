import React from "react";
import { useSpeechSynthesis } from "react-speech-kit";

const Card = ({ id, name, flipped, matched, clicked, image }) => {
  return (
    <div
      onClick={() => (flipped ? undefined : clicked(name, id))}
      className={
        "card" + (flipped ? " flipped" : "") + (matched ? " matched" : "")
      }
    >
      <div className="back">?</div>
      <div className="front">
        <img alt={name} src={image} />
      </div>
    </div>
  );
};

export default Card;
