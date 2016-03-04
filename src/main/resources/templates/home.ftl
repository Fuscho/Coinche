<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>Fuscho Coinche</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/card.css">
    <script src="js/game.js"></script>
    <script src="js/bundle.js"></script>
    <script src="/bower_components/sockjs/sockjs.js"></script>
    <script src="/bower_components/stomp-websocket/lib/stomp.js"></script>
</head>
<body>
<div id="test"></div>
<div id="gameContainer">

    <div id="containerJ3">j3</div>
    <div id="centerContainer">
        <div id="containerJ2">j2</div>

        <div id="bitContainer">
            <div class="suitWanted">
                <button>Hearts</button>
                <button>Diamonds</button>
                <button>Clubs</button>
                <button>Spades</button>
            </div>
            <div class="contract">
                <button>80</button>
                <button>90</button>
                <button>100</button>
                <button>110</button>
                <button>120</button>
                <button>130</button>
                <button>140</button>
                <button>150</button>
                <button>Capot</button>
            </div>
            <div class="action">
                <button onclick="pass()">Passer</button>
                <button onclick="bit()">Miser</button>
            </div>
        </div>
        <div id="cardsContainer">
            <div id="cardPlayJ3"></div>
            <div id="cardPlayJ2"></div>
            <div id="cardPlayJ4"></div>
            <div id="cardPlayJ1"></div>
            <button id="play-btn" onclick="nextTrick()">Pli vu</button>
            <button id="next-round-btn" onclick="nextRound()">Manche suivante</button>
        </div>
        <div id="containerJ4">j4</div>
    </div>

    <div id="containerJ1">
        <div id="cardsPlayerContainer"></div>
        <div id="currentBid">
            <p>Annonceur : <span class="bidder"></span></p>
            <p>Couleur : <span class="suit"></span></p>
            <p>Annonce : <span class="point"></span></p>
        </div>
    </div>

</div>
</body>
</html>