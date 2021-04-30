package ua.ivanyshen.graphswebsite.graph;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.ivanyshen.graphswebsite.dao.userDAO;
import ua.ivanyshen.graphswebsite.user.User;

/**
 * @author - Max Ivanyshen
 */

@Controller
public class GraphController {
    public static Graph graph;
    public static User currentUser = new User();
    private String websiteName = "Viz4Charts";
    public static userDAO dao;

    /**
     * In line model.addAttribute("title", websiteName); we create a local variable for Thymeleaf
     * called "title" with value of "Viz4Charts"
     *
     * In line model.addAttribute("chart", new Graph()); or
     * model.addAttribute("chart", graph);
     * we create a local variable for Thymeleaf
     * called "chart" with our new chart or already created one
     */

    //Creates '/' route of website with home page
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", websiteName);
        if(currentUser.isPremium())
            return "premium/index";
        else
            return "notPremium/index";
    }

    //Creates '/create-charts' route of website with charts creator
    @GetMapping("/create-charts")
    public String createCharts(Model model) {
        model.addAttribute("title", websiteName);
        graph = new Graph();
        model.addAttribute("chart", graph);
        if(currentUser.isPremium())
            return "premium/enter-rows";
        else
            return "notPremium/enter-rows";
    }

    //Operates post request from 'create-charts' route: creates as
    // many params and values for them as we want
    @PostMapping("/create-charts")
    public String chooseParamsAndValues(Model model, @ModelAttribute Graph graph) {
        model.addAttribute("title", websiteName);
        model.addAttribute("chart", graph);
        if(currentUser.isPremium())
            return "premium/chooseParamsAndValues";
        else
            return "notPremium/chooseParamsAndValues";
    }

    //Operates post request from 'create-charts' route: creates
    // chart with params and values we entered in the previous route
    @PostMapping("/editor")
    public String chartEditor(Model model, @ModelAttribute Graph graph) {
        model.addAttribute("title", websiteName);
        model.addAttribute("chart", graph);
        if(currentUser.isPremium())
            return "premium/chartEditor";
        else
            return "notPremium/chartEditor";
    }

    //Creates '/contact' route of website with contact page
    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", websiteName);
        if(currentUser.isPremium())
            return "premium/contact";
        else
            return "notPremium/contact";
    }

    //Creates '/premium' route of website with page for premium payment
    @GetMapping("/premium")
    public String premium(Model model) {
        model.addAttribute("title", websiteName);
        if(currentUser.isPremium())
            return "redirect:/";
        else
            return "notPremium/premium";
    }

    //Creates '/sign-up' route of website with page for creating an account
    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("title", websiteName);
        model.addAttribute("user", new User());
        return "sign-up";
    }

//    Processes '/sign-up' route data for registration
    @PostMapping("/sign-up")
    public String newUser(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("title", websiteName);
        currentUser = user;
        dao = new userDAO();
        dao.save(user);
        if(currentUser.isPremium())
            return "premium/showUser";
        else
            return "notPremium/showUser";
    }

    @PostMapping("/deleteUser")
    public String deleteUser() {
        userDAO.deleteUser(currentUser);
        return "redirect:/";
    }
}
