package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurveService;
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
public class CurveController {
    @Autowired
    private CurveService curveService;


    @RequestMapping("/curvePoint/list")
    public String home(Model model, Principal user) {
        List<CurvePoint> curvePointList = curveService.findAll();
        model.addAttribute("curvepointlist", curvePointList);

        if(user instanceof OAuth2AuthenticationToken){
            model.addAttribute("username", ((OAuth2AuthenticationToken) user).getPrincipal().getAttributes().get("login"));
            log.info("user " +((OAuth2AuthenticationToken) user).getPrincipal().getAttributes().get("login") + " is connected to his curvePoint");
        }
        else if(user instanceof UsernamePasswordAuthenticationToken){
            model.addAttribute("username", user.getName());
            log.info("user" +user.getName() + " is connected to his curvePoint");
        }
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint curvePoint) {
        log.info("The user want to add a new curvePoint");
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            curveService.save(curvePoint);
            model.addAttribute("curvepointlist", curveService.findAll());
            log.info("The user added a new curvepoint, id:" +curvePoint.getCurveId()+" Term: " +curvePoint.getTerm()+ " Value: " +curvePoint.getValue());
            return "redirect:/curvePoint/list";
        }
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curveService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvepoint Id:" + id));
        model.addAttribute("curvePoint", curvePoint);
        log.info("The user is updating the curvepoint id number "  +id);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.error("error when updating the curvepoint " +id);
            return "curvePoint/update";
        }
        curvePoint.setId(id);
        curveService.save(curvePoint);
        model.addAttribute("curvepointlist", curveService.findAll());
        log.info("The user updated the curvepoint id "  +id);

        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curveService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvepoint Id:" + id));
        curveService.delete(curvePoint);
        model.addAttribute("curvePoint", curvePoint);
        log.info("The user deleted the curvepoint " +id);
        return "redirect:/curvePoint/list";

    }
}
