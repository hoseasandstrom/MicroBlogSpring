package com.theironyard.controllers;

import com.theironyard.entities.Message;
import com.theironyard.entities.User;
import com.theironyard.services.MessageRepository;
import com.theironyard.services.UserRepository;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;

/**
 * Created by hoseasandstrom on 6/20/16.
 */
@Controller
public class MicroBlogSpringController {
    @Autowired
    UserRepository users;
    @Autowired
    MessageRepository messages;

    public MicroBlogSpringController() throws FileNotFoundException {
    }

    @PostConstruct
    public void init() throws Exception {
        Server.createWebServer().start();
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {

        String username = (String) session.getAttribute("username");
        User user = null;
        if (username != null) {
            user = new User(username);
        }

            Iterable<Message> msg = messages.findAll();
            model.addAttribute("msgs", msg);
            model.addAttribute("user", user);
            return "home";

    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session) {
        if(username.isEmpty() || password.isEmpty()) {
            return  "redirect:/";
        }

        User user = users.findByName(username);
        if (user == null) {
            user = new User(username, password);
            users.save(user);
        }

        if (!user.password.equals(password)) {
            return "redirect:/";
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
    public String deletemessage(Integer id) {
        messages.delete(id);

        return "redirect:/";

    }
    @RequestMapping(path ="/editmessage", method = RequestMethod.POST)
    public  String editmessage(Integer id, String text) {
        Message message = new Message(id, text);
        messages.save(message);
        return "redirect:/";

    }

}
