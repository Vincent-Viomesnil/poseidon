package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
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
public class BidListController {
    @Autowired
    private BidListService bidListService;


    @RequestMapping("/bidList/list")
    public String home(Model model, Principal user){
        List<BidList> bidLists = bidListService.findAll();
        model.addAttribute("bidlists", bidLists);

        if(user instanceof OAuth2AuthenticationToken){
            model.addAttribute("username", ((OAuth2AuthenticationToken) user).getPrincipal().getAttributes().get("login"));
            log.info("user " +((OAuth2AuthenticationToken) user).getPrincipal().getAttributes().get("login") + " is connected to his bidlist");
        }
        else if(user instanceof UsernamePasswordAuthenticationToken){
        model.addAttribute("username", user.getName());
            log.info("user" +user.getName() + " is connected to his bidlist");
        }
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        log.info("The user want to add a new bid");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bidList, BindingResult result, Model model, BidList bid) {
        if (!result.hasErrors()) {
            bidListService.save(bidList);
            model.addAttribute("bidlists", bidListService.findAll());
            log.info("The user added a new bid, id:" +bid.getId()+" Account: " +bid.getAccount()+ " Type: " +bid.getType()+ " BidQuantity: " +bid.getBidQuantity());
            return "redirect:/bidList/list";
        }

        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidlist Id:" + id));
        model.addAttribute("bidList", bidList);
        log.info("The user is updating the bidlist id number "  +id);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.error("error when updating the bidlist " +id);
            return "bidList/update";
        }
        bidList.setId(id);
        bidListService.save(bidList);
        model.addAttribute("bidlists", bidListService.findAll());
        log.info("The user updated the bidlist id "  +id);

        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidlist Id:" + id));
        bidListService.delete(bidList);
        model.addAttribute("bidList", bidList);
        log.info("The user deleted the bidlist " +id);
        return "redirect:/bidList/list";

    }
}
