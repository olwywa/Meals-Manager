package com.example.mealsmanagerapp.controllers;

import com.example.mealsmanagerapp.models.Meal;
import com.example.mealsmanagerapp.models.User;
import com.example.mealsmanagerapp.services.MealService;
import com.example.mealsmanagerapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping(path = "/")
public class MainController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final MealService mealService;

    public MainController(UserService userService, MealService mealService) {
        this.userService = userService;
        this.mealService = mealService;
    }

    @GetMapping(path = "/")
    public String showHome(){
        return "home";
    }

    @GetMapping(path = "/login")
    public ModelAndView showLogin(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping(path = "/login")
    public ModelAndView logInToSystem(@ModelAttribute("user") User user, String email, String password) {
        ModelAndView modelAndView = new ModelAndView();

        if (userService.findUserByEmail(user.getEmail()) != null
                && user.getEmail().equals(email)
                && userService.checkAuth(password, email)) {
            modelAndView.addObject("successLogIn", "User has been authorized successfully");

            System.out.println("User has been authorized successfully");
            modelAndView.setViewName("loggedDashboard");
        } else {
//            TODO zmienic notke
            System.out.println("email error.user There is already a user registered with the email provided");
        }
        return modelAndView;
    }

    @GetMapping(path = "/deletedAccount")
    public String deletedAccountPage() {
        return "deletedAccount";
    }


    @GetMapping(path = "/register")
    public ModelAndView showRegister(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView createUser(User user) {
        ModelAndView modelAndView = new ModelAndView();

        if (userService.findIfUserExistsByEmail(user.getEmail())) {
            System.out.println("email error.user There is already a user registered with the email provided");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");

            System.out.println("User has been registered successfully");
            modelAndView.setViewName("result");
        }
        return modelAndView;
    }


    @GetMapping(path = "/addMeal")
    public String showAddMealPage(Model model){
        Meal meal = new Meal();
        model.addAttribute("meal", meal);
        return "addNewMeal";
    }

    public static String uploadDirectory = System.getProperty("meal.dir") + "/src/main/resources/images";

    @PostMapping(path = "/addMeal")
    @ResponseBody
    public String addMeal(@ModelAttribute("meal") Meal meal, @RequestParam("file") MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        meal.setPhoto(fileName);

        Meal savedMeal = mealService.save(meal);

        String uploadDir = "meal-photos/" + savedMeal.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, file);

        return "redirect:/loggedDashboard.html";
    }

    @GetMapping(path = "/all")
    public String getAll(Model model) {

        model.addAttribute("meals", mealService.getAll());

        return "loggedDashboard";
    }
}