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
    ArrayList<Message> messageList = new ArrayList<>();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {

        String username = (String) session.getAttribute("username");
        User user = null;
        if (username != null) {
            user = new User(username);
        }

        for (Message msg : messageList) {
            int id = 1;
            msg.id = id;
            id++;

        }

        model.addAttribute("user", user);
        model.addAttribute("messageList", messageList);
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(String username, HttpSession session) {
        session.setAttribute("username", username);
        return "redirect:/";
    }
    @RequestMapping(path ="/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String addmessage(String text, HttpSession session) {
        Message msg = new Message(text);
        messageList.add(msg);

        return "redirect:/";
    }
    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String deletemessage(Integer id) {
        messageList.remove(id - 1);


        return "redirect:/";

    }

}