package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
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
public class RuleNameController {
    @Autowired
    private RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")

    public String home(Model model, Principal user){
        List<RuleName> ruleNameList = ruleNameService.findAll();
        model.addAttribute("rulenamelist", ruleNameList);

        if(user instanceof OAuth2AuthenticationToken){
            model.addAttribute("username", ((OAuth2AuthenticationToken) user).getPrincipal().getAttributes().get("login"));
            log.info("user " +((OAuth2AuthenticationToken) user).getPrincipal().getAttributes().get("login") + " is connected tp his rulename list");
        }
        else if(user instanceof UsernamePasswordAuthenticationToken){
            model.addAttribute("username", user.getName());
            log.info("user" +user.getName() + " is connected to his rulename list");
        }
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addBidForm(RuleName bid) {
        log.info("The user want to add a new rulename");
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ruleNameService.save(ruleName);
            model.addAttribute("rulenamelist", ruleNameService.findAll());
            log.info("The user added a new rule, id:" +ruleName.getId()+" Name: " +ruleName.getName()+ " Description: " +ruleName.getDescription()+
                    " Template: " +ruleName.getTemplate()+ " SqlStr/sqlPart: " +ruleName.getSqlStr()+ "/" +ruleName.getSqlPart());
            return "redirect:/ruleName/list";
        }
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        model.addAttribute("ruleName", ruleName);
        log.info("The user is updating the rulename id number "  +id);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.error("error when updating the rulename " +id);
            return "ruleName/update";
        }
        ruleName.setId(id);
        ruleNameService.save(ruleName);
        model.addAttribute("rulenamelist", ruleNameService.findAll());


        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        ruleNameService.delete(ruleName);
        model.addAttribute("ruleName", ruleName);
        log.info("The user deleted the rulename " +id);
        return "redirect:/ruleName/list";

    }
}
