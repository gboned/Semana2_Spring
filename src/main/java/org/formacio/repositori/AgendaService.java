package org.formacio.repositori;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class AgendaService extends Persona {

	private Map<String, Persona> bbdd = new LinkedHashMap<>();
	
	@PostConstruct
	public void init() {
		bbdd.put("ant", new Persona("ant","Antoni","971-555123"));
		bbdd.put("joa", new Persona("joa","Joana","971-555555"));
		bbdd.put("lin", new Persona("lin","Lina","971-555888"));
	}
	
	public void inserta (String id, String nom, String telefon) {
		bbdd.put(id, new Persona(id, nom, telefon));
	}
	
	public Persona recupera (String id) {
		return bbdd.get(id);
	}
	
	public String devolverTelefono(String id) {
		Persona datos = bbdd.get(id);
		String numero = datos.getTelefon();
		return numero;
		
	}
	
	public Persona devolverPersona(String id) {
		Persona datos = bbdd.get(id);
		return datos;
	}
	
	public String devolverNombre(String id) {
		Persona datos = bbdd.get(id);
		String nombre = datos.getNom();
		return nombre;
	}
	
	public int nombreContactes() {
		return bbdd.size();
	}
	
}
