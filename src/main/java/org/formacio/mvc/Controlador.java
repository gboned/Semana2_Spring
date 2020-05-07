package org.formacio.mvc;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.formacio.repositori.AgendaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import com.google.gson.Gson;

@Controller
public class Controlador extends AgendaService {
	@Autowired
	private AgendaService agendaService;
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
	
	@RequestMapping(
			value = "/contacte/{id}", 
			method = RequestMethod.GET, 
			produces = "application/json")
	
	@ResponseBody
	public String personaPorId(@PathVariable("id") String id) {
		String clau = id;
		String nom = agendaService.devolverNombre(id);
		String tel = agendaService.devolverTelefono(id);
		
		HashMap<String, String> persona = new HashMap<String, String>();
		
		persona.put("clau", clau);
		persona.put("contacte", nom);
		persona.put("telefon", tel);
		
		Gson parserPersona = new Gson();
		String parsedPersona = parserPersona.toJson(persona);
		
		return parsedPersona;
	}
	
	@RequestMapping(
			value = "/contacte/{id}",
			method = RequestMethod.GET,
			produces = "application/xml")
	
	@ResponseBody
	public String personaPorIdXML(@PathVariable("id") String id, HttpServletResponse response) throws JsonProcessingException, IOException {
		String clau = id;
		String nom = agendaService.devolverNombre(id);
		String tel = agendaService.devolverTelefono(id);
		
		String jsonAsXml = null;
		HashMap<String, String> persona = new HashMap<String, String>();
		
		if (nom == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		} else {
		
		persona.put("clau", clau);
		persona.put("nom", nom);
		persona.put("telefon", tel);
		
		Gson parserPersona = new Gson();
		String parsedPersona = parserPersona.toJson(persona);
		
		ObjectMapper objectMapper = new ObjectMapper();
		XmlMapper mapper = new XmlMapper();
		JsonNode tree = objectMapper.readTree(parsedPersona);
		jsonAsXml = mapper.writer().withRootName("persona").writeValueAsString(tree);
	}
		
		return jsonAsXml;
	}
	
	@PostMapping(value = "/afegir")
	
	@ResponseBody
	public void nuevaPersona(@RequestParam Map<Object, String> persona)  {
		agendaService.inserta(persona.get("id"), persona.get("nom"), persona.get("telefon"));
	}

}  

