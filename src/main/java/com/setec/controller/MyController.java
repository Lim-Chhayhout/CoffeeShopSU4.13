package com.setec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.setec.entities.Booked;
import com.setec.repos.BookedRepo;
import com.setec.services.MyTelegramBot;

@Controller
public class MyController {

	//http://localhost:8080/home
	
	@GetMapping({"/","/home"})
	public String home(Model mod) {
		Booked booked = new Booked(
				
				);
		mod .addAttribute("booked", booked);
		return "index";
	}
	
	@GetMapping({"/about"})
	public String about() {
		return "about";
	}
	
	@GetMapping({"/service"})
	public String service() {
		return "service";
	}
	
	@GetMapping({"/menu"})
	public String menu() {
		return "menu";
	}
	
	@GetMapping({"/reservation"})
	public String resrvation(Model mod) {
		Booked booked = new Booked(
				
				);
		mod .addAttribute("booked", booked);
		return "reservation";
	}
	
	@GetMapping({"/testimonial"})
	public String testimonial() {
		return "testimonial";
	}
	
	@GetMapping({"/contact"})
	public String contact() {
		return "contact";
	}
	
	@Autowired
	private BookedRepo bookedRepo;
	
	@Autowired
	private MyTelegramBot bot;
	
	@PostMapping("/success")
	public String success(@ModelAttribute Booked booked){
	    bookedRepo.save(booked);

	    String msg =
	        "🍽️ *New Reservation*\n" +
	        "━━━━━━━━━━━━━━━━━━\n" +
	        "👤 *Name:* " + booked.getName() + "\n" +
	        "📞 *Phone:* " + booked.getPhoneNumber() + "\n" +
	        "📧 *Email:* " + booked.getEmail() + "\n" +
	        "📆 *Date:* " + booked.getDate() + "\n" +
	        "⏰ *Time:* " + booked.getTime() + "\n" +
	        "👥 *Guests:* " + booked.getPerson() + "\n" +
	        "━━━━━━━━━━━━━━━━━━\n" +
	        "✔️ *Please confirm with customer*";

	    bot.sendMessage(msg);

	    return "success";
	}

}
