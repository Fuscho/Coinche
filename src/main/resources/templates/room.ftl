<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>Fuscho Coinche</title>
    <script src="/bower_components/jquery/dist/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/card.css">
    <script src="js/bundle.js"></script>
    <script src="/bower_components/sockjs/sockjs.js"></script>
    <script src="/bower_components/stomp-websocket/lib/stomp.js"></script>
    <link href='https://fonts.googleapis.com/css?family=Dosis' rel='stylesheet' type='text/css'>
</head>
<body>
    <div id="rooms">

    </div>
</body>

<script type="text/javascript" th:inline="javascript" async="async">
    var containerId = 'rooms';
    document.getElementById(containerId).innerHTML = '';
    Coinche.ReactDOM.render(
            Coinche.React.createElement(Coinche.Rooms, null),
            document.getElementById(containerId)
    );
</script>

</html>