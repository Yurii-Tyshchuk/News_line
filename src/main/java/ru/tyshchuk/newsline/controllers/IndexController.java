package ru.tyshchuk.newsline.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.tyshchuk.newsline.services.CommandLineAppStartupRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class IndexController {
  private final CommandLineAppStartupRunner commandLineAppStartupRunner;

  public IndexController(CommandLineAppStartupRunner commandLineAppStartupRunner) {
    this.commandLineAppStartupRunner = commandLineAppStartupRunner;
    this.commandLineAppStartupRunner.runUser();
  }

  @GetMapping("/resource")
  @ResponseBody
  public Map<String, Object> home() {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("id", UUID.randomUUID().toString());
    model.put("content", "Hello World");
    return model;
  }

  @GetMapping(value = "/{path:[^\\.]*}")
  public String redirect() {
    return "forward:/";
  }
}
