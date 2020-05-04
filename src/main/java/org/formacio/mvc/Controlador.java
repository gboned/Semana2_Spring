package org.formacio.mvc;

import org.formacio.repositori.AgendaService;
import org.formacio.repositori.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controlador extends AgendaService {
	@Autowired
	private AgendaService agendaService;
	@Autowired
	private Persona persona;
	@RequestMapping(path = "/nombre")
	@ResponseBody
	public int numeroContactos() {
		return agendaService.nombreContactes();
	}
	@RequestMapping(path = "/telefon")
	@ResponseBody
	public String telefonoContactos(@RequestParam String id) {
		return agendaService.devolverTelefono(id);
	}
		
		
}


