import React from "react";

const GameOver = ({ restartGame, nextLevel, level }) => {
  return (
    <div className="w-100 centered h-screen" style={{height: '60vh'}}>
      <div>
      <h1>Congrats!</h1>
      <button className="restart-button" onClick={restartGame}>
        Play Again?
      </button>
      {level < 3 && <button style={{marginLeft: '10px'}} className="restart-button" onClick={nextLevel}>
        Next Level
      </button>}
      </div>
    </div>
  );
};

export default GameOver;
