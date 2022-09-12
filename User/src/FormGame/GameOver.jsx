import React from "react";

const GameOver = ({ restartGame, nextLevel, level }) => {
  return (
    <div className="centered">
      <h1>Congrats!</h1>
      <button className="restart-button" onClick={restartGame}>
        Play Again?
      </button>
      {level < 3 && <button style={{marginLeft: '5px'}} className="restart-button" onClick={nextLevel}>
        Next Level
      </button>}
    </div>
  );
};

export default GameOver;
