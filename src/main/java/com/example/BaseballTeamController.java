package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("")
public class BaseballTeamController {

	@Autowired
	BaseballTeamService service = new BaseballTeamService();

	@RequestMapping("/")
	public String index(Model model){
		List<Team> teamlist = service.findAll();
		model.addAttribute("teamList", teamlist);
		return "index";
	}

	@RequestMapping("/toDetail")
	public String toDetail(@RequestParam int id, Model model){
		Team team = service.findById(id);
		model.addAttribute("team", team);
		return "detail";
	}

}
