package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model, Principal user)
    {
        List<Trade> tradeList = tradeService.findAll();
        model.addAttribute("tradelist", tradeList);

        if(user instanceof OAuth2AuthenticationToken){
            model.addAttribute("username", ((OAuth2AuthenticationToken) user).getPrincipal().getAttributes().get("login"));
            log.info("user " +((OAuth2AuthenticationToken) user).getPrincipal().getAttributes().get("login") + " is connected to his trade list");
        }
        else if(user instanceof UsernamePasswordAuthenticationToken){
            model.addAttribute("username", user.getName());
            log.info("user" +user.getName() + " is connected to his trade list");
        }
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addBidForm(Trade trade) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            tradeService.save(trade);
            model.addAttribute("tradelist", tradeService.findAll());
            log.info("The user is added a new trade, id:" +trade.getId()+" Account: " +trade.getAccount()+ " Type: " +trade.getType()+
                    " BidQuantity: " +trade.getBuyQuantity());
            return "redirect:/trade/list";
        }
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        model.addAttribute("trade", trade);
        log.info("The user is updating the trade id number "  +id);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid Trade trade,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.error("error when updating the trade " +id);
            return "trade/update";
        }
        trade.setId(id);
        tradeService.save(trade);
        model.addAttribute("tradelist", tradeService.findAll());

        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        tradeService.delete(trade);
        model.addAttribute("trade", trade);
        log.info("The user deleted the trade " +id);
        return "redirect:/trade/list";
    }
    }

