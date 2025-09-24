package com.diva.thymeleaf_demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute("javax.servlet.error.status_code");
        Object message = request.getAttribute("javax.servlet.error.message");
        Object path = request.getAttribute("javax.servlet.error.request_uri");

        model.addAttribute("status", status != null ? status : "N/A");
        model.addAttribute("error", message != null ? message : "Unknown error");
        model.addAttribute("message", message != null ? message : "Something went wrong");
        model.addAttribute("path", path != null ? path : "N/A");

        return "error/error";
    }

}
