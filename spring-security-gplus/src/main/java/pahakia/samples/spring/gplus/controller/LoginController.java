package pahakia.samples.spring.gplus.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @RequestMapping(value = { "/loggedin" }, method = RequestMethod.GET)
    @ResponseBody
    public String isLoggedIn(HttpServletRequest request) {
        return "" + (request.getSession().getAttribute("token") != null);
    }
}
// is user logged in filter
// if not logged in then redirect to login page
// finish log in, redirect to original page (GET)
// what about POST?