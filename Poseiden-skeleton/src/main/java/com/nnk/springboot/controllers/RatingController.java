package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
public class RatingController {
    @Autowired
    private RatingService ratingService;


    @RequestMapping("/rating/list")

    public String home(Model model, Principal user){
        List<Rating> ratingList = ratingService.findAll();
        model.addAttribute("ratinglist", ratingList);

        if(user instanceof OAuth2AuthenticationToken){
            model.addAttribute("username", ((OAuth2AuthenticationToken) user).getPrincipal().getAttributes().get("login"));
            log.info("user " +((OAuth2AuthenticationToken) user).getPrincipal().getAttributes().get("login") + " is connected to his rating list");
        }
        else if(user instanceof UsernamePasswordAuthenticationToken){
            model.addAttribute("username", user.getName());
            log.info("user" +user.getName() + " is connected to his rating list");
        }

        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addBidForm(Rating rating) {
        log.info("The user want to add a new rating");
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ratingService.save(rating);
            model.addAttribute("ratinglist", ratingService.findAll());
            log.info("The user added a new rating, id:" +rating.getId()+" MoodysRating: " +rating.getMoodysRating()+ " SandPrating: " +rating.getSandPRating()+
                    " FitchRating: " +rating.getFitchRating()+ " Order: " +rating.getOrderNumber());
            return "redirect:/rating/list";
        }
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        model.addAttribute("rating", rating);
        log.info("The user is updating the rating id number "  +id);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid Rating rating,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.error("error when updating the rating " +id);
            return "rating/update";
        }
        rating.setId(id);
        ratingService.save(rating);
        model.addAttribute("ratinglist", ratingService.findAll());
        log.info("The user updated the rating id "  +id);

        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        ratingService.delete(rating);
        model.addAttribute("rating", rating);
        log.info("The user deleted the rating " +id);
        return "redirect:/rating/list";

    }
}
