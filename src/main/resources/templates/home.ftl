<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <title>Fuscho Coinche</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <script src="js/game.js"></script>
    </head>
    <body>
        <div id="gameContainer">

            <div id="containerJ3">j3</div>
            <div id="centerContainer">
                <div id="containerJ2">j2</div>

                <div id="cardsContainer">
                    <div id="cardPlayJ3">card j3</div>
                    <div id=""playCardJ2J4">
                        <div id="cardPlayJ2">card j2</div>
                        <div id="cardPlayJ4">card j4</div>
                    </div>
                    <div id="cardPlayJ1">card j1</div>
                </div>
                <div id="containerJ4">j4</div>
            </div>

            <div id="containerJ1">
                <select name="select" id="cardsSelect"></select>
                <button type="playButton" onclick="playBtnClick()">Play!</button>
            </div>

        </div>
    </body>
</html>