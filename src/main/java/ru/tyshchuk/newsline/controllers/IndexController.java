package ru.tyshchuk.newsline.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.tyshchuk.newsline.services.CommandLineAppStartupRunner;

@Controller
public class IndexController {
    private final CommandLineAppStartupRunner commandLineAppStartupRunner;

    public IndexController(CommandLineAppStartupRunner commandLineAppStartupRunner) {
        this.commandLineAppStartupRunner = commandLineAppStartupRunner;
        this.commandLineAppStartupRunner.runUser();
    }

    @GetMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/";
    }
}
