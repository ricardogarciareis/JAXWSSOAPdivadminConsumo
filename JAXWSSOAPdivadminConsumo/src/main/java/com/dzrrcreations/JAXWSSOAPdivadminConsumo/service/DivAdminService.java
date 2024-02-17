package com.dzrrcreations.JAXWSSOAPdivadminConsumo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dzrrcreations.JAXWSSOAPdivadminConsumo.model.Concelho;
import com.dzrrcreations.JAXWSSOAPdivadminConsumo.model.Distrito;
import com.dzrrcreations.JAXWSSOAPdivadminConsumo.model.Freguesia;


@Service
public interface DivAdminService {

	public List<Distrito> listarDistritos();
	public List<Concelho> listarConcelhos();
	public List<Freguesia> listarFreguesias();
	
	public List<Freguesia> listarFreguesiasPorIdConcelho(Long idConcelho);
	public List<Freguesia> listarFreguesiasPorIdDistrito(Long idDistrito);
	public List<Concelho> listarConcelhosPorIdDistrito(Long idDistrito);
	
	public Concelho mostrarConcelhoPorIdFreguesia(Long idFreguesia);
	public Distrito mostrarDistritoPorIdFreguesia(Long idFreguesia);
	public Distrito mostrarDistritoPorIdConcelho(Long idConcelho);
	
}
