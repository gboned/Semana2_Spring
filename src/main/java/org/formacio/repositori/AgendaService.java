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
	/* Creado método que según la id del contacto de la base
	 * de datos, devuelva su número de telefono, la comprobación
	 * de si dichos datos apuntan a null, es para el método del 
	 * controlador referente al error 404 
	 * */
	public String devolverTelefono(String id) {
		Persona datos = bbdd.get(id);
		if (datos == null) {
			return null;
		}
		
		String numero = datos.getTelefon();
		return numero;
	}

	/* Añadido método que devuelve el nombre del contacto
	 * según la id que se le pase, la comprobación de si
	 * los datos son null, es para el método de controlador
	 * referente al error 404
	 * */
	public String devolverNombre(String id) {
		Persona datos = bbdd.get(id);
		if (datos == null) {
			return null;
		}
		
		String nombre = datos.getNom();
		return nombre;
	}
	
	public int nombreContactes() {
		return bbdd.size();
	}
	
}
