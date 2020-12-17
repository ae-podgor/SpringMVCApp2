package com.podgornova.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {

    // вычленение параметров из запроса двумя способами:
    // через объект HttpServletRequest в методе helloPage()
    // и через аннотацию @RequestParam в методе goodbyePage()

    //если не передать параметры, переменные примут значение null
    @GetMapping("/hello")
    public String helloPage(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        System.out.println(String.format("Hello, %s %s", name, surname));
        return "first/hello";
    }

    //если не передать параметры, будет ошибка 400, если не прописать required=false
    @GetMapping("/goodbye")
    public String goodbyePage(@RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "surname", required = false) String surname,
                              Model model) {
        model.addAttribute("message", String.format("Hello, %s %s", name, surname));
//        System.out.println(String.format("Hello, %s %s", name, surname));
        return "first/goodbye";
    }

    // простой веб-калькулятор
    @GetMapping("/calculator")
    public String calculate(@RequestParam("a") int a,
                            @RequestParam("b") int b,
                            @RequestParam("action") String action,
                            Model model) {
        double result = 0;
        switch (action){
            case "multiplication":
                result = a*b;
                break;
            case "addition":
                result = a+b;
                break;
            case "subtraction":
                result = a-b;
                break;
            case "division":
                result = (double) a/b;
        }

        model.addAttribute("result", result);

        return "first/calculator";
    }
}
