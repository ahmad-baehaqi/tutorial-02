package com.example.demo.contoller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {
	@RequestMapping("/hello")
	public String index() {
		return "hello";
	}
	//Harus didelete atau dicomment untuk dapat menjalankan path/hello2
	@RequestMapping("/hello2")
	public String hello2(@RequestParam(value = "name", required = false, defaultValue = "Thanos") 
	String name, Model model) {
		model.addAttribute("name", name);
		return "hello2";
	}
	@RequestMapping(value= {"/hello2", "/hello2/{name}"})
	public String helloPath(@PathVariable Optional<String> name, Model model) {
		if (name.isPresent()) {
			model.addAttribute("name", name.get());	
		} else {
			model.addAttribute("name", "Phoenix");
		}
		return "hello2";
	}
	@RequestMapping("/calculator")
	public String calculator(@RequestParam(value = "angka1", required = false, defaultValue = "0") 
	Integer angka1, @RequestParam(value = "angka2", required = false, defaultValue = "0") Integer angka2, 
	Model model) {
		model.addAttribute("angka1", angka1);
		model.addAttribute("angka2", angka2);
		Integer hasil = angka1 + angka2; 
		model.addAttribute("hasil", hasil);
		
		String puluhan = " puluh ";
		String belasan = " belas";
		String satuan[] = {"", "satu", "dua", "tiga", "empat", "lima", "enam", "tujuh", "delapan", "sembilan"};
		String convert = "";
		
		if (hasil == 10) {
			convert = "sepuluh";
		} else if (hasil == 11 ) {
			convert = "sebelas";
		} else if(hasil == 0) {
			convert = "nol";
		} else if (hasil > 0 && hasil < 10) {
			convert = satuan[hasil];
		} else if (hasil > 11 && hasil < 20) {
			convert = satuan[hasil%10] + belasan;
		} else if (hasil >= 20 && hasil < 100) {
			convert = satuan[hasil/10] + puluhan + satuan[hasil%((hasil/10)*10)];
		} else {
			convert = "Kebanyakan Uyyy Mager";
		}
		
		model.addAttribute("convert", convert);
		
		return "calculator";
	}
}
