package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by LaunchCode
 */

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    static ArrayList<Cheese> cheeses = new ArrayList<>();

    // Request path: cheese/
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    // Request path: cheese/add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {

        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    // Request path: cheese/add
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDescription) {

        cheeses.add(new Cheese(cheeseName, cheeseDescription));

        // Redirect to cheese/
        return "redirect:";
    }

    @RequestMapping(value="remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model){

        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", cheeses);
        return "cheese/remove";
    }

    @RequestMapping(value="remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam ArrayList<String> cheesesToRemove){

        Iterator<Cheese> cheeseRemovalIterator = cheeses.iterator();
        while(cheeseRemovalIterator.hasNext()) {
            Cheese cheese = cheeseRemovalIterator.next();
            if (cheesesToRemove.contains(cheese.getName())) {
                cheeseRemovalIterator.remove();
            }
        }
        return "redirect:";
    }

}
