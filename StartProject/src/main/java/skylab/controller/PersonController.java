package skylab.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import skylab.entity.Person;
import skylab.service.PersonService;

@Controller
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/person")
	private String showPerson(Model model) {
		model.addAttribute("person", new Person());
		model.addAttribute("persons", personService.getAll());
		return "person";
	}

	@RequestMapping(value = "/person", method = RequestMethod.POST)
	private String showAllPerson(@Valid Person person,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "person";
		} else {
			personService.create(person);
			return "redirect:/fdgfgdfg";
		}
	}
}
