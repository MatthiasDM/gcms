/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sdm.gcms.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Matthias
 */
@Order(1)
public class SessionAuthFilter extends OncePerRequestFilter {

    private static final String SESSIONKEY = "userSession";

    public SessionAuthFilter(final String headerName) {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession httpSession = request.getSession(false);
        if (httpSession == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        } else {           
//            if (request.getPathInfo().startsWith("/page/")) {
//                ModelAndView mav = new ModelAndView("static/page/index.html");
//                mav.addObject("message", "Hello from the filter!");
//                httpSession.setAttribute("myModelAndView", mav);
//                return;
//            };
        };
        filterChain.doFilter(request, response);

    }

    //
}
