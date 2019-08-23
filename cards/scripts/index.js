//Stats
//Opponent's life, attack, and defense
let oppLife = 20;
const oppLifeStatus = document.querySelector('.opp-life');
oppLifeStatus.innerText = oppLife;

let oppAttack = 0;
const oppAttackStatus = document.querySelector('.opp-attack');
oppAttackStatus.innerText = oppAttack;

let oppDefense = 0;
const oppDefenseStatus = document.querySelector('.opp-defense');
oppDefenseStatus.innerText = oppDefense;

//Your life, attack, and defense
let life = 20;
const lifeStatus = document.querySelector('.life');
lifeStatus.innerText = life;

//This stat activates when you tap a general
let attack = 0;
const attackStatus = document.querySelector('.attack');
attackStatus.innerText = attack;

//This is a passive trait of any general placed in the field
//a later version can deal with actively choosing a defender
let defense = 0;
const defenseStatus = document.querySelector('.defense');
defenseStatus.innerText = defense;

const stack = document.querySelector('.pile');
//This flag is for the moveCard functions
let isClicked = false;

const card = stack.querySelector('.card');
const slots = document.querySelector('.slots');
const slot = [...slots.querySelectorAll('.slot')];
slot.forEach(slot => slot.isEmpty = true);

const hand = document.querySelector('.hand');
let handCards = [];

function initDrawDeck() {
  const topCard = stack.firstElementChild;
  topCard.children[0].innerText = deck[0]["title"];
  topCard.children[1].setAttribute("src", deck[0]["img"]);
}

let fieldCards = [];
let deck = [];
req=new XMLHttpRequest();
req.open("GET",'/json/deck.json',true);
req.send();
req.onload=function(){
  deck=JSON.parse(req.responseText);
  console.log('deck loaded');
  initDrawDeck();
};



function stackClicked(e) {
  if (!isClicked) {
    stack.style.borderColor = "yellow";
    slot.forEach(function(slot) {
      if (slot.isEmpty) {
        slot.style.borderColor = "pink";
      }
    });
  } else {
    stack.style.borderColor = "white";
    slot.forEach(function(slot) {
      if (slot.isEmpty) {
        slot.style.borderColor = "white";
      }
    });
  }
  isClicked = !isClicked;
}

function moveCardToField(e) {
  if (isClicked && slot.includes(e.target) && e.target.isEmpty) {
    stackClicked(e);
    e.target.innerHTML = stack.innerHTML;
    e.target.isEmpty = false;
    stack.style.borderColor = 'white';
    fieldCards.push(deck[0]);
    deck.shift();
    initDrawDeck();
    if (fieldCards[fieldCards.length-1].type === "general") {
      defense += parseInt(fieldCards[fieldCards.length-1].defense);
      defenseStatus.innerText = defense;
    }
  }
}

function moveCardToHand(e) {
  if (isClicked && handCards.length < 7) {
    stackClicked(e);
    handCards.push(deck[0]);
    deck.shift();
    e.target.style.border = '3px solid white';
    e.target.innerHTML = stack.innerHTML;
    //e.target.style.borderColor = 'white';
    initDrawDeck();
  }
}

function tapCard(e) {
  if (!e.target.isEmpty) {
    console.log(e.target)
    e.target.style.backgroundColor = '#bada55';
    for (let i=0; i<fieldCards.length; i++) {
      const hasTarget = fieldCards[i].title === e.target.innerText;
      if(fieldCards[i].type === "event" && hasTarget) {
        const name = fieldCards[i]['effect'].split(' ')[0];
        const arg = [parseInt(fieldCards[i]['effect'].split(' ')[1])];
        const fn = window[name];
        //console.log(arg);
        if (typeof fn === 'function') {
          fn.apply(null, arg);
        }
      } else if (fieldCards[i].type === "general" && hasTarget) {
          attack += parseInt(fieldCards[i].attack);
          attackStatus.innerText = attack;
      }
    }
  }
}

function incLife(amount) {
  life += amount;
  lifeStatus.innerText = life;
}

stack.onclick = stackClicked;
slots.addEventListener('click', (e) => {
  if (stack.style.borderColor === 'white') {
    tapCard(e);
  } else {
    moveCardToField(e);
  }
});

hand.onclick = moveCardToHand;
