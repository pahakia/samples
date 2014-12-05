package pahakia.samples.spring.gplus.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pahakia.gplus.settings.GooglePlusSettings;
import pahakia.settings.Settings;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.Gson;

@Controller
public class AppController {
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

    @RequestMapping(value = { "/", "/public" }, method = RequestMethod.GET)
    public ModelAndView publicPage(HttpSession session) {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "public page");
        model.addObject("message", "No authentication required");
        model.addObject("clientId", CLIENT_ID);
        String state = new BigInteger(130, new SecureRandom()).toString(32);
        session.setAttribute("state", state);
        model.setViewName("public");
        return model;
    }

    @RequestMapping(value = { "/gplus" }, method = RequestMethod.GET)
    public ModelAndView gplus(HttpSession session) {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "google+");
        model.addObject("message", "demo exchange google+ token");
        String state = new BigInteger(130, new SecureRandom()).toString(32);
        session.setAttribute("state", state);
        model.addObject("state", state);
        model.addObject("clientId", CLIENT_ID);
        model.setViewName("gplus-exchange-token");
        return model;
    }

    @RequestMapping(value = { "/gplus" }, method = RequestMethod.POST)
    public void gplusPost(HttpSession session, HttpServletResponse response, @RequestParam("state") String state,
            @RequestBody String code) throws IOException {
        String tokenData = (String) session.getAttribute("token");
        if (tokenData != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print(GSON.toJson("Current user is already connected."));
            return;
        }
        if (!state.equals(session.getAttribute("state"))) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().print(GSON.toJson("Invalid state parameter."));
            return;
        }
        session.removeAttribute("state");
        GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(TRANSPORT, JSON_FACTORY, CLIENT_ID,
                CLIENT_SECRET, code, "postmessage").execute();
        // validate the token
        GoogleIdToken idToken = tokenResponse.parseIdToken();
        String gplusId = idToken.getPayload().getSubject();
        System.out.println(gplusId);
        // Store the token in the session for later use.
        session.setAttribute("token", gplusId);
        session.setAttribute("tokenData", tokenResponse.toString());
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(GSON.toJson("Successfully connected user."));
    }

    @RequestMapping(value = { "/revoke" }, method = RequestMethod.GET)
    public ModelAndView revoke(HttpSession session) throws IOException {
        String tokenData = (String) session.getAttribute("tokenData");
        if (tokenData != null) {
            session.invalidate();
            // Build credential from stored token data.
            GoogleCredential credential = new GoogleCredential.Builder().setJsonFactory(JSON_FACTORY)
                    .setTransport(TRANSPORT).setClientSecrets(CLIENT_ID, CLIENT_SECRET).build()
                    .setFromTokenResponse(JSON_FACTORY.fromString(tokenData, GoogleTokenResponse.class));
            // Execute HTTP GET request to revoke current token.
            HttpResponse revokeResponse = TRANSPORT
                    .createRequestFactory()
                    .buildGetRequest(
                            new GenericUrl(String.format("https://accounts.google.com/o/oauth2/revoke?token=%s",
                                    credential.getAccessToken()))).execute();
            System.out.println("revoked: " + revokeResponse.getStatusCode() + ", " + revokeResponse);
        }
        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/");
        return model;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView protectedPage(HttpSession session) {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "user page");
        model.addObject("message", "This page is only accessible after user is authenticated");
        model.setViewName("user");
        return model;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView superAdminPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "admin page");
        model.addObject("message", "This page is limited to admin users only");
        model.setViewName("user");
        return model;
    }
}
