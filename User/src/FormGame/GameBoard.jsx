import React, { useState, useEffect } from "react";
import Card from "./Card";
import GameOver from "./GameOver";
import "../FormGame/Styles.scss";
import axios from '../utils/axios';

const GameBoard = () => {
  ///////////// HELPER FUNCTION /////////////

  const shuffle = array => {
    let currentIndex = array.length,
      temporaryValue,
      randomIndex;
    while (0 !== currentIndex) {
      randomIndex = Math.floor(Math.random() * currentIndex);
      currentIndex -= 1;
      temporaryValue = array[currentIndex];
      array[currentIndex] = array[randomIndex];
      array[randomIndex] = temporaryValue;
    }
    return array;
  };

  ///////////// SETUP /////////////
  const [cards] = useState([])
  const [flippedCards, setFlippedCards] = useState([]);
  const [gameOver, setGameOver] = useState(false);
  const [level, setLevel] = useState(1)
  const [cardList, setCardList] = useState(
    shuffle(cards).map((item, index) => {
      return {
        id: index,
        name: item.name,
        image: item.link,
        flipped: false,
        matched: false
      };
    })
  );
  ///////////// GAME LOGIC /////////////
  const getListCard = async () => {
    const res = await axios.get(`/api/v2/tags/randomTags?number=${level}`)
    if (res.status === 200) {
      // setCard(res.data)
      setCardList(shuffle(res.data).map((item, index) => {
        return {
          id: index,
          name: item.name,
          image: item.link,
          flipped: false,
          matched: false
        };
      }))
    }
  }
  useEffect(() => {
    getListCard()
  },[])

  const handleClick = (name, index) => {
    let currentCard = {
      name,
      index
    };

    //update card is flipped
    let updateCards = cardList.map(card => {
      if (card.id === index) {
        card.flipped = true;
      }
      return card;
    });
    let updateFlipped = flippedCards;
    updateFlipped.push(currentCard);
    setFlippedCards(updateFlipped);
    setCardList(updateCards);

    //if 2 cards are flipped, check if they are a match
    if (flippedCards.length === 2) {
      setTimeout(() => {
        check();
      }, 750);
    }
  };

  const check = () => {
    let updateCards = cardList;
    if (
      flippedCards[0].name === flippedCards[1].name &&
      flippedCards[0].index !== flippedCards[1].index
    ) {
      updateCards[flippedCards[0].index].matched = true;
      updateCards[flippedCards[1].index].matched = true;
      isGameOver();
    } else {
      updateCards[flippedCards[0].index].flipped = false;
      updateCards[flippedCards[1].index].flipped = false;
    }
    setCardList(updateCards);
    setFlippedCards([]);
  };

  const isGameOver = () => {
    let done = true;
    cardList.forEach(card => {
      if (!card.matched) done = false;
    });
    setGameOver(done);
  };

  ///////////// RESTART - REDO SETUP /////////////

  const restartGame = async () => {
    setLevel(1);
    const res = await axios.get(`/api/v2/tags/randomTags?number=1`)
    if (res.status === 200) {
      // setCard(res.data)
      setCardList(shuffle(res.data).map((item, index) => {
        return {
          id: index,
          name: item.name,
          image: item.link,
          flipped: false,
          matched: false
        };
      }))
    }
    setFlippedCards([]);
    setGameOver(false);
  };
  const nextLevel = async () => {
    setLevel(level + 1)
    const res = await axios.get(`/api/v2/tags/randomTags?number=${level + 1}`)
    if (res.status === 200) {
      // setCard(res.data)
      setCardList(shuffle(res.data).map((item, index) => {
        return {
          id: index,
          name: item.name,
          image: item.link,
          flipped: false,
          matched: false
        };
      }))
    }
    setFlippedCards([]);
    setGameOver(false);
  }
  ///////////// DISPLAY /////////////

  return (
    <div className="game-board">
      {!gameOver &&
        cardList.map((card, index) => (
          <Card
            key={index}
            id={index}
            name={card.name}
            flipped={card.flipped}
            matched={card.matched}
            clicked={flippedCards.length === 2 ? () => {} : handleClick}
            image={card.image}
          />
        ))}
      {gameOver && <GameOver restartGame={restartGame} nextLevel={nextLevel} level={level}/>}
    </div>
  );
};

export default GameBoard;
