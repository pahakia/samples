pahakia samples - gplus
==

This is a repackage of google's gplus-quickstart-java: https://developers.google.com/+/quickstart/java

This is a standard java web app.

Please follow the following steps:

1. configure the environment variable SETTINGS_DIR to point to a directory.
   bash: export SETTINGS_DIR=/path/to/dir
   csh:  setenv SETTINGS_DIR /path/to/dir
2. if the directory pointed to by SETTINGS_DIR does not exist: 
   mkdir -p $SETTINGS_DIR
3. cp pahakia.properties.example $SETTIGS_DIR/pahakia.properties
4. edit $SETTINGS_DIR/pahakia.properties to include your google client id and secret.

Note the port has to be the one you specified in google developer console when you create the google plus client id and secret.
