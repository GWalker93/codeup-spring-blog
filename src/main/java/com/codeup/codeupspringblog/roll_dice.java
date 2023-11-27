package com.codeup.codeupspringblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class roll_dice {

    @GetMapping("/roll-dice")
    public String rollDiceForm() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    @ResponseBody
    public String rolledDice(@PathVariable int n) {

        int dice = (int) (Math.random()*6+1);

        if (n == dice) {
            return "The roll was " + dice + " You guessed right";
        } else {
            return "The roll was " + dice + " You guessed wrong";
        }

    }

}
