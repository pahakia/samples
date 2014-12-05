<!doctype html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <title>jQuery UI Dialog functionality</title>
      <link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
      <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
      <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
      <!-- CSS -->
      <style>
         .ui-widget-header,.ui-state-default, ui-button{
            background:#b9cd6d;
            border: 1px solid #b9cd6d;
            color: #FFFFFF;
            font-weight: bold;
         }
         div#opener-div {width: 700px; margin: 20px auto; text-align: center;}
      </style>
      <!-- Javascript -->
      <script>
         $(function() {
            $( "#dialog-1" ).dialog({
               autoOpen: false,  
            });
            $( "#opener" ).click(function() {
               $( "#dialog-1" ).dialog( "open" );
            });
         });
      </script>
   </head>
   <body>
      <!-- HTML --> 
      <div id="dialog-1" title="Dialog Title goes here...">This my first jQuery UI Dialog!</div>
      <div id="opener-div">
        <h1 style="font-size: 95%; color: orange;">Example taken from <a style="color: orange;" href="http://www.tutorialspoint.com/jqueryui/jqueryui_dialog.htm">tutorialspoint</a></h1>
        <button id="opener">Open Dialog</button>
      </div>
   </body>
</html>