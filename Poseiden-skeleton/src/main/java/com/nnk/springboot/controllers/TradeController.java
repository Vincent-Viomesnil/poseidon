package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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
        }
        else if(user instanceof UsernamePasswordAuthenticationToken){
            model.addAttribute("username", user.getName());
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
            return "redirect:/trade/list";
        }
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid Trade trade,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
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
        return "redirect:/trade/list";

    }
    }

