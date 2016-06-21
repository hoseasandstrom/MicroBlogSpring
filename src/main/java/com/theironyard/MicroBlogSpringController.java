package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by hoseasandstrom on 6/20/16.
 */
@Controller
public class MicroBlogSpringController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {

        String idStr = "id";
        Integer id = 0;
        if (idStr != null) {
            id = Integer.valueOf(idStr);
        }


        String username = (String) session.getAttribute("username");
        User user = null;
        if (username != null) {
            user = new User(username);
        }


        model.addAttribute("user", user);
        model.addAttribute("messages");
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(String username, HttpSession session) throws Exception {
        session.setAttribute("username", username);
        if (username == null) {
            throw new Exception("Did not receive username");
        }
        return "redirect:/";
    }
    @RequestMapping(path ="/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    @RequestMapping(path = "/addmessage", method = RequestMethod.POST)
    public String addmessage(String message, HttpSession session) {
        Message msg = new Message(message,);
        messageList.add(msg);

        return "redirect:/";
    }
    @RequestMapping(path = "/deletemessage", method = RequestMethod.POST)
    public String deletemessage(Integer id) {
        messageList.remove(id - 1);

        return "redirect:/";

    }
    @RequestMapping(path ="/editmessage", method = RequestMethod.PUT)
    public  String editmessage(String message) {
        Integer id = Integer.valueOf("{id}");
        Message msg = new Message(id, message);
        messageList.add(msg);

        return "redirect:/";
    }

}
