package ua.ivanyshen.graphswebsite.graph;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.ivanyshen.graphswebsite.user.User;

import java.util.Arrays;

/**
 * @author - Max Ivanyshen
 */

@Controller
public class GraphController {
    public static Graph graph;
    private String websiteName = "Viz4Charts";

    /**
     * In line model.addAttribute("title", websiteName); we create a local variable for thymeleaf
     * called "title" with value of "Viz4Charts"
     */

    //Creates '/' route of website
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", websiteName);
        return "index";
    }

    //Creates '/create-charts' route of website
    @GetMapping("/create-charts")
    public String createCharts(Model model) {
        model.addAttribute("title", websiteName);
        graph = new Graph();
        model.addAttribute("chart", graph);
        return "enter-rows";
    }

    @PostMapping("/create-charts")
    public String chooseParamsAndValues(Model model, @ModelAttribute Graph graph) {
        model.addAttribute("title", websiteName);
        model.addAttribute("chart", graph);
        return "chooseParamsAndValues";
    }

    @PostMapping("/editor")
    public String chartEditor(Model model, @ModelAttribute Graph graph) {
        model.addAttribute("title", websiteName);
        model.addAttribute("chart", graph);
        System.out.println(Arrays.toString(graph.getParams()));
        return "chartEditor";
    }

    //Creates '/contact' route of website
    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", websiteName);
        return "contact";
    }

    //Creates '/premium' route of website
    @GetMapping("/premium")
    public String premium(Model model) {
        model.addAttribute("title", websiteName);
        return "premium";
    }

    //Creates '/sign-up' route of website
    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("title", websiteName);
        model.addAttribute("user", new User());
        return "sign-up";
    }

    //Processes '/sign-up' route data for registration
    @PostMapping("/sign-up")
    public String newUser(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("title", websiteName);
        return "showUser";
    }
}

