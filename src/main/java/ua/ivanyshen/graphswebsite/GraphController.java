package ua.ivanyshen.graphswebsite;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.ivanyshen.graphswebsite.dao.PasswordEncryptor;
import ua.ivanyshen.graphswebsite.dao.graphDAO;
import ua.ivanyshen.graphswebsite.dao.userDAO;
import ua.ivanyshen.graphswebsite.user.User;

import static ua.ivanyshen.graphswebsite.dao.userDAO.saveUser;

/**
 * @author - Max Ivanyshen
 */

@Controller
public class GraphController {
    public static Graph graph;
    public static User currentUser = null;
    private final String websiteName = "Viz4Charts";
    public static userDAO dao;
    public static graphDAO graphDAO;
    public String message="";

    {
        dao = new userDAO();
        graphDAO = new graphDAO();
    }

    /**
     * In line model.addAttribute("title", websiteName); we create a local variable for Thymeleaf
     * called "title" with value of "Viz4Charts"
     *
     * In line model.addAttribute("chart", new Graph()); or
     * model.addAttribute("chart", graph);
     * we create a local variable for Thymeleaf
     * called "chart" with our new chart or already created one
     **/

    //Creates '/' route of website with home page
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", websiteName);
        model.addAttribute("user", currentUser);
        return "index";
    }

    //Creates '/create-charts' route of website with charts creator
    @GetMapping("/create-charts")
    public String createCharts(Model model) {
        model.addAttribute("title", websiteName);
        graph = new Graph();
        model.addAttribute("chart", graph);
        return "enter-rows";
    }

    //Operates post request from 'create-charts' route: creates as
    // many params and values for them as we want
    @PostMapping("/create-charts")
    public String chooseParamsAndValues(Model model, @ModelAttribute Graph graph) {
        model.addAttribute("title", websiteName);
        model.addAttribute("chart", graph);
        model.addAttribute("user", currentUser);
        return "chooseParamsAndValues";
    }

    //Operates post request from 'create-charts' route: creates
    // a chart with params and values we entered in the previous route
    @PostMapping("/editor")
    public String chartEditor(Model model, @ModelAttribute Graph graph) {
        model.addAttribute("title", websiteName);
        model.addAttribute("chart", graph);
        return "chartEditor";
    }

    @GetMapping("/editor/{name}")
    public String chartEditor(Model model, @PathVariable("name") String graphName) {
        if(currentUser!=null) {
            model.addAttribute("title", websiteName);
            model.addAttribute("chart", graph);
            model.addAttribute("user", currentUser);
            model.addAttribute("chart", graphDAO.findByName(graphName, currentUser.getId()));
            return "chartEditor";
        }
        return "redirect:/";
    }

    @PostMapping("/saveChart")
    public String saveChart(Model model, @ModelAttribute Graph graph) {
        if(!currentUser.isPremium())
            return "redirect:/";
        currentUser = dao.saveGraph(currentUser, graph);
        return "redirect:/savedCharts/"+currentUser.getId();
    }

    @GetMapping("/savedCharts/{id}")
    public String savedCharts(Model model, @PathVariable("id") int id) {
        model.addAttribute("title", websiteName);
        model.addAttribute("user", dao.findUserById(id));
        System.out.println(dao.findUserById(id).getName());
        return "savedCharts";
    }

    //Creates '/contact' route of website with contact page
    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", websiteName);
        return "contact";
    }

    //Creates '/premium' route of website with page for premium payment
    @GetMapping("/premium")
    public String premium(Model model) {
        model.addAttribute("title", websiteName);
        model.addAttribute("user", currentUser);
        if(currentUser == null || !currentUser.isPremium())
            return "premium";
        return "redirect:/";
    }

    //Creates '/sign-up' route of website with page for creating an account
    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("title", websiteName);
        model.addAttribute("message", message);
        model.addAttribute("user", new User());
        return "sign-up";
    }

    //Processes '/sign-up' route data for registration
    @PostMapping("/sign-up")
    public String newUser(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("title", websiteName);
        currentUser = user;
        if(!user.getPass1().equals(user.getPass2())) {
            message = "Passwords don't match";
            return "redirect:/sign-up";
        }
        else if(!user.getEmail().matches("^(.+)@(.+)$")) {
            message = "Invalid email";
            return "redirect:/sign-up";
        }
        if(user.getPass1().equals(user.getPass2()) && user.getEmail().matches("^(.+)@(.+)$")) {
            saveUser(user);
            message="";
        }
        return "redirect:/user/"+currentUser.getId();
    }

    @GetMapping("/user/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("title", websiteName);
        model.addAttribute("user", dao.findUserById(id));
        System.out.println(dao.findUserById(id).getName());
        return "showUser";
    }

    @PostMapping("/deleteUser")
    public String deleteUser() {
        userDAO.deleteUser(currentUser);
        currentUser=null;
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", websiteName);
        model.addAttribute("message", message);
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String logUserIn(Model model, @ModelAttribute User user) {
        if(!user.getEmail().matches("^(.+)@(.+)$")) {
            message="Invalid email";
            return "redirect:/login";
        }
        else {
            message="";
            User neededUser = dao.findUserByEmail(user.getEmail());
            if(neededUser==null) {
                message="User does not exist. Do you want to sign up?";
                return "redirect:/login";
            }
            else {
                PasswordEncryptor encryptor = new PasswordEncryptor();
                String pass = encryptor.decrypt(neededUser.getMainPass());
                if(pass.equals(user.getMainPass())) {
                    message="";
                    currentUser = neededUser;
                    System.out.println(currentUser.getName());
                    return "redirect:/";
                }
                else {
                    message="Invalid password or user does not exists";
                    return "redirect:/login";
                }
            }
        }
    }

    @PostMapping("/addParam")
    public String addParam(Model model, @ModelAttribute Graph graph) {
        String chartName = graph.getName();
        String chartType = graph.getType();
        graphDAO.addParam(graph);
        model.addAttribute("paramNumber", graph.getParams().length-1);
        graph.setName(chartName);
        graph.setType(chartType);
        model.addAttribute("chart", graph);
        return "addParam";
    }
}

