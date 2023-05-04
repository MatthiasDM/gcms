/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sdm.gcms.cc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Matthias
 */
@RestController
public class LoginController {

    @Bean
    public GroupedOpenApi LoginOpenApi() {
        String paths[] = {"/login/**"};
        return GroupedOpenApi.builder().group("authentication").pathsToMatch(paths)
                //.pathsToExclude("/api/**")
                //.pathsToExclude("/api/v2/**", "/v2/**", "/**/v3/**", "/api/**")
                .build();
    }

    @GetMapping("/login")
    public ModelAndView showLoginForm(HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("static/login/index.html");
        return modelAndView;
    }

    public String showSecurePage(HttpSession session, HttpServletRequest request) {
        if (session.getAttribute("authenticated") == null) {
            return "redirect:/login";
        } else {
            return "secure";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLogin(@RequestParam String username, @RequestParam String password, HttpSession session, HttpServletRequest request) {
        if (username.equals("admin") && password.equals("secret")) {
            session.setAttribute("authenticated", true);
            return "redirect: " + request.getHeader("Referer");
        } else {
            return "login";
        }
    }
}
