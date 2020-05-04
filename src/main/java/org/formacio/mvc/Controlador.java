package org.formacio.mvc;

import org.formacio.repositori.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controlador extends AgendaService {
	@Autowired
	private AgendaService agendaService;
	@RequestMapping(path = "/nombre")
	@ResponseBody
	public int numeroContactos() {
		return agendaService.nombreContactes();
	}
	
}
