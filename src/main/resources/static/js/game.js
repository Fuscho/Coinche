

$( document ).ready(function() {
    $.ajax({
        url: "/api/init",
        method: "POST"
    })
    .done(function( data ) {
        console.log(data)
    });
});