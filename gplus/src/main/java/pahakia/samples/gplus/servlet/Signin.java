/*
 * Copyright 2013 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pahakia.samples.gplus.servlet;

import javax.servlet.http.HttpServlet;

import pahakia.gplus.settings.GooglePlusSettings;
import pahakia.settings.Settings;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.Gson;

/**
 * Simple server to demonstrate how to use Google+ Sign-In and make a request via your own server.
 *
 * @author joannasmith@google.com (Joanna Smith)
 * @author vicfryzel@google.com (Vic Fryzel)
 */
@SuppressWarnings("serial")
public abstract class Signin extends HttpServlet {
    static {
        try {
            Settings.init();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
    /*
     * Default HTTP transport to use to make HTTP requests.
     */
    protected static final HttpTransport TRANSPORT = new NetHttpTransport();

    /*
     * Default JSON factory to use to deserialize JSON.
     */
    protected static final JacksonFactory JSON_FACTORY = new JacksonFactory();

    /*
     * Gson object to serialize JSON responses to requests to this servlet.
     */
    protected static final Gson GSON = new Gson();

    /*
     * This is the Client ID that you generated in the API Console.
     */
    protected static final String CLIENT_ID = GooglePlusSettings.GOOGLE_PLUS_CLIENT_ID.get();

    /*
     * This is the Client Secret that you generated in the API Console.
     */
    protected static final String CLIENT_SECRET = GooglePlusSettings.GOOGLE_PLUS_CLIENT_SECRET.get();

    /*
     * Optionally replace this with your application's name.
     */
    protected static final String APPLICATION_NAME = "Google+ Java Quickstart";

}
