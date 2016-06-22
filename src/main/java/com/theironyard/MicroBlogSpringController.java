package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * Created by hoseasandstrom on 6/20/16.
 */
@Controller
public class MicroBlogSpringController {
    @Autowired
    UserRepository users;
    @Autowired
    MessageRepository messages;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {


        String username = (String) session.getAttribute("username");
        User user = null;
        if (username != null) {
            user = new User(username);
        }

        model.addAttribute("user", user);

        Iterable<Message> msgs = messages.findAll();
        model.addAttribute("msgs", msgs);

        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session) {
        if(username.isEmpty() || password.isEmpty()) {
            return "/login";
        }

        User user = users.findByUsername(username);
        if (user == null) {
            user = new User(username, password);
            users.save(user);
        }

        if (!user.password.equals(password)) {
            return "/login";
        }

        session.setAttribute("username", username);
        return "redirect:/";
    }
    @RequestMapping(path ="/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    @RequestMapping(path = "/addmessage", method = RequestMethod.POST)
    public String addmessage(String text, HttpSession session) {
        Message msg = new Message(text);
        messages.save(msg);

        return "redirect:/";
    }
    @RequestMapping(path = "/deletemessage", method = RequestMethod.POST)
    public String deletemessage(HttpSession session, int id) {
        messages.delete(id);

        return "redirect:/";

    }
    @RequestMapping(path ="/editmessage", method = RequestMethod.GET)
    public  String editmessage(String message, Model model, int id) {
        Message msg = messages.findById(id);
        model.addAttribute("msg", msg);
        model.addAttribute("text", msg.text);
        model.addAttribute("id", msg.id);
        return "/edit";
        
    }

}
