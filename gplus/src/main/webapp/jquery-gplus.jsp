<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>jQuery UI Dialog - Modal form</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
  <style>
    body { font-size: 62.5%; }
    div#create-user-div {width: 700px; margin: 20px auto; text-align: center;}
  </style>
  <script>
  
  $(function() {
    var dialog, form;
 
    dialog = $( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 130,
      width: 250,
      modal: true,
      close: function() {
        $('#gplusone').remove();
      }
    });
 
    $( "#create-user" ).button().on( "click", function() {
      dialog.dialog( "open" );
      var po = document.createElement('script');
      po.id = "gplusone";
      po.type = 'text/javascript'; po.async = true;
      po.src = 'https://plus.google.com/js/client:plusone.js';
      var s = $('#gConnect')[0];
      s.parentNode.insertBefore(po, s);
    });
  });
  function onSignInCallback(authResult) {
  }
  </script>
</head>
<body>

<% pahakia.settings.Settings.init(); %> 
<div id="dialog-form" title="Create new user">
  <p>Please sign in with google+.</p>
  <div id="gConnect">
    <button class="g-signin"
        data-scope="https://www.googleapis.com/auth/plus.login"
        data-requestvisibleactions="http://schemas.google.com/AddActivity"
        data-clientId="<%= pahakia.gplus.settings.GooglePlusSettings.GOOGLE_PLUS_CLIENT_ID.get() %>"
        data-accesstype="offline"
        data-callback="onSignInCallback"
        data-theme="dark"
        data-cookiepolicy="single_host_origin">
    </button>
  </div>
</div>

<div id="create-user-div">
  <button id="create-user">Create new user</button>
</div>
</body>
</html>