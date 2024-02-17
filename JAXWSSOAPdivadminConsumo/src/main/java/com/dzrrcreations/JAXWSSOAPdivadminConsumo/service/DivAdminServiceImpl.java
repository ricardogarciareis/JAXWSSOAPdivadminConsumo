package com.dzrrcreations.JAXWSSOAPdivadminConsumo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.dzrrcreations.JAXWSSOAPdivadminConsumo.model.Concelho;
import com.dzrrcreations.JAXWSSOAPdivadminConsumo.model.Distrito;
import com.dzrrcreations.JAXWSSOAPdivadminConsumo.model.Freguesia;

public class DivAdminServiceImpl implements DivAdminService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(DivAdminServiceImpl.class);
	
	private final String wsURL = "http://192.168.1.12:8088/JAXWSSOAPdivadmin/divadmin";
	private URL url = null;
	private URLConnection connection = null;
	private HttpURLConnection httpCon = null;
	private String responseString = null;
	private String outputString = "";
	private OutputStream out = null;
	private InputStreamReader isr = null;
	private BufferedReader in = null;
	

	@Override
	public List<Distrito> listarDistritos() {
		String xmlInput = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.JAXWSSOAPdivadmin.dzrrcreations.com/\">\r\n"
						+ "   <soapenv:Header/>\r\n"
						+ "   <soapenv:Body>\r\n"
						+ "      <ser:listarDistritos/>\r\n"
						+ "   </soapenv:Body>\r\n"
						+ "</soapenv:Envelope>";
		
		List<Distrito> distritos = new ArrayList<Distrito>();
		
		try {
			url = new URL(wsURL);
			connection = url.openConnection();
			httpCon = (HttpURLConnection) connection;
			
			byte[] buffer = new byte[xmlInput.length()];
			buffer = xmlInput.getBytes();
			
			// Set the appropriate HTTP parameters.
			httpCon.setRequestProperty("Content-Length", String.valueOf(buffer.length));
			httpCon.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			httpCon.setRequestMethod("GET");
			httpCon.setDoOutput(true);
			httpCon.setDoInput(true);
			out = httpCon.getOutputStream();
			out.write(buffer);
			out.close();
			
			//Read the response and write it to standard out.
			isr = new InputStreamReader(httpCon.getInputStream());
			in = new BufferedReader(isr);
			while ((responseString = in.readLine()) != null) {
				outputString = outputString + responseString;
			}
			LOGGER.info("Envelope SOAP: " + outputString);
			
			//Get the response from the web service call
			Document document = parseXmlFile(outputString);
			NodeList nodeList = document.getElementsByTagName("ns0:listarDistritosResponse");
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = nodeList.item(itr);
				LOGGER.info("Metodo: " + node.getNodeName());
			    if (node.getNodeType() == Node.ELEMENT_NODE) {
			    	Element eElement = (Element) node;
			    	for (int item = 0; item < eElement.getElementsByTagName("return").getLength(); item++) {
				        Distrito distrito = new Distrito();
				        distrito.setId(Long.parseLong(eElement.getElementsByTagName("id").item(item).getTextContent()));
				        distrito.setNome(eElement.getElementsByTagName("nome").item(item).getTextContent());
				        distritos.add(distrito);
			    	}
			    }
			}
		} catch(Exception e) {
			LOGGER.error("Erro: " + e);
			System.err.println("Erro: " + e.getStackTrace());
		}
		return distritos;
	}

	@Override
	public List<Concelho> listarConcelhos() {
		String xmlInput = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.JAXWSSOAPdivadmin.dzrrcreations.com/\">\r\n"
				+ "   <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "      <ser:listarConcelhos/>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";

		List<Concelho> concelhos = new ArrayList<Concelho>();
		
		try {
			url = new URL(wsURL);
			connection = url.openConnection();
			httpCon = (HttpURLConnection) connection;
			
			byte[] buffer = new byte[xmlInput.length()];
			buffer = xmlInput.getBytes();
			
			// Set the appropriate HTTP parameters.
			httpCon.setRequestProperty("Content-Length", String.valueOf(buffer.length));
			httpCon.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			httpCon.setRequestMethod("GET");
			httpCon.setDoOutput(true);
			httpCon.setDoInput(true);
			out = httpCon.getOutputStream();
			out.write(buffer);
			out.close();
			
			//Read the response and write it to standard out.
			isr = new InputStreamReader(httpCon.getInputStream());
			in = new BufferedReader(isr);
			while ((responseString = in.readLine()) != null) {
				outputString = outputString + responseString;
			}
			LOGGER.info("Envelope SOAP: " + outputString);
			
			//Get the response from the web service call
			Document document = parseXmlFile(outputString);
			NodeList nodeList = document.getElementsByTagName("ns0:listarConcelhosResponse");
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = nodeList.item(itr);
				LOGGER.info("Metodo: " + node.getNodeName());
			    if (node.getNodeType() == Node.ELEMENT_NODE) {
			    	Element eElement = (Element) node;
			    	for (int item = 0; item < eElement.getElementsByTagName("return").getLength(); item++) {
				        Concelho concelho = new Concelho();
				        concelho.setId(Long.parseLong(eElement.getElementsByTagName("id").item(item).getTextContent()));
				        concelho.setNome(eElement.getElementsByTagName("nome").item(item).getTextContent());
				        concelho.setDistrito(eElement.getElementsByTagName("distrito").item(item).getTextContent());
				        concelhos.add(concelho);
			    	}
			    }
			}
		} catch(Exception e) {
			LOGGER.error("Erro: " + e);
			System.err.println("Erro: " + e.getStackTrace());
		}
		return concelhos;
	}

	@Override
	public List<Freguesia> listarFreguesias() {
		String xmlInput = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.JAXWSSOAPdivadmin.dzrrcreations.com/\">\r\n"
				+ "   <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "      <ser:listarFreguesias/>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";

		List<Freguesia> freguesias = new ArrayList<Freguesia>();
		
		try {
			url = new URL(wsURL);
			connection = url.openConnection();
			httpCon = (HttpURLConnection) connection;
			
			byte[] buffer = new byte[xmlInput.length()];
			buffer = xmlInput.getBytes();
			
			// Set the appropriate HTTP parameters.
			httpCon.setRequestProperty("Content-Length", String.valueOf(buffer.length));
			httpCon.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			httpCon.setRequestMethod("GET");
			httpCon.setDoOutput(true);
			httpCon.setDoInput(true);
			out = httpCon.getOutputStream();
			out.write(buffer);
			out.close();
			
			//Read the response and write it to standard out.
			isr = new InputStreamReader(httpCon.getInputStream());
			in = new BufferedReader(isr);
			while ((responseString = in.readLine()) != null) {
				outputString = outputString + responseString;
			}
			LOGGER.info("Envelope SOAP: " + outputString);
			
			//Get the response from the web service call
			Document document = parseXmlFile(outputString);
			NodeList nodeList = document.getElementsByTagName("ns0:listarFreguesiasResponse");
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = nodeList.item(itr);
				LOGGER.info("Metodo: " + node.getNodeName());
			    if (node.getNodeType() == Node.ELEMENT_NODE) {
			    	Element eElement = (Element) node;
			    	for (int item = 0; item < eElement.getElementsByTagName("return").getLength(); item++) {
				        Freguesia freguesia = new Freguesia();
				        freguesia.setId(Long.parseLong(eElement.getElementsByTagName("id").item(item).getTextContent()));
				        freguesia.setNome(eElement.getElementsByTagName("nome").item(item).getTextContent());
				        freguesia.setConcelho(eElement.getElementsByTagName("concelho").item(item).getTextContent());
				        freguesia.setDistrito(eElement.getElementsByTagName("distrito").item(item).getTextContent());
				        freguesias.add(freguesia);
			    	}
			    }
			}
		} catch(Exception e) {
			LOGGER.error("Erro: " + e);
			System.err.println("Erro: " + e.getStackTrace());
		}
		return freguesias;
	}

	@Override
	public List<Freguesia> listarFreguesiasPorIdConcelho(Long idConcelho) {
		String xmlInput = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.JAXWSSOAPdivadmin.dzrrcreations.com/\">\r\n"
						+ "   <soapenv:Header/>\r\n"
						+ "   <soapenv:Body>\r\n"
						+ "      <ser:listarFreguesiasPorIdConcelho>\r\n"
						+ "         <!--Optional:-->\r\n"
						+ "         <arg0>" + idConcelho + "</arg0>\r\n"
						+ "      </ser:listarFreguesiasPorIdConcelho>\r\n"
						+ "   </soapenv:Body>\r\n"
						+ "</soapenv:Envelope>";

		List<Freguesia> freguesias = new ArrayList<Freguesia>();
		
		try {
			url = new URL(wsURL);
			connection = url.openConnection();
			httpCon = (HttpURLConnection) connection;
			
			byte[] buffer = new byte[xmlInput.length()];
			buffer = xmlInput.getBytes();
			
			// Set the appropriate HTTP parameters.
			httpCon.setRequestProperty("Content-Length", String.valueOf(buffer.length));
			httpCon.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			httpCon.setRequestMethod("GET");
			httpCon.setDoOutput(true);
			httpCon.setDoInput(true);
			out = httpCon.getOutputStream();
			out.write(buffer);
			out.close();
			
			//Read the response and write it to standard out.
			isr = new InputStreamReader(httpCon.getInputStream());
			in = new BufferedReader(isr);
			while ((responseString = in.readLine()) != null) {
				outputString = outputString + responseString;
			}
			LOGGER.info("Envelope SOAP: " + outputString);
			
			//Get the response from the web service call
			Document document = parseXmlFile(outputString);
			NodeList nodeList = document.getElementsByTagName("ns0:listarFreguesiasPorIdConcelhoResponse");
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = nodeList.item(itr);
				LOGGER.info("Metodo: " + node.getNodeName());
			    if (node.getNodeType() == Node.ELEMENT_NODE) {
			    	Element eElement = (Element) node;
			    	for (int item = 0; item < eElement.getElementsByTagName("return").getLength(); item++) {
				        Freguesia freguesia = new Freguesia();
				        freguesia.setId(Long.parseLong(eElement.getElementsByTagName("id").item(item).getTextContent()));
				        freguesia.setNome(eElement.getElementsByTagName("nome").item(item).getTextContent());
				        freguesia.setConcelho(eElement.getElementsByTagName("concelho").item(item).getTextContent());
				        freguesia.setDistrito(eElement.getElementsByTagName("distrito").item(item).getTextContent());
				        freguesias.add(freguesia);
			    	}
			    }
			}
		} catch(Exception e) {
			LOGGER.error("Erro: " + e);
			System.err.println("Erro: " + e.getStackTrace());
		}
		return freguesias;
	}

	@Override
	public List<Freguesia> listarFreguesiasPorIdDistrito(Long idDistrito) {
		String xmlInput = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.JAXWSSOAPdivadmin.dzrrcreations.com/\">\r\n"
						+ "   <soapenv:Header/>\r\n"
						+ "   <soapenv:Body>\r\n"
						+ "      <ser:listarFreguesiasPorIdDistrito>\r\n"
						+ "         <!--Optional:-->\r\n"
						+ "         <arg0>" + idDistrito + "</arg0>\r\n"
						+ "      </ser:listarFreguesiasPorIdDistrito>\r\n"
						+ "   </soapenv:Body>\r\n"
						+ "</soapenv:Envelope>";

		List<Freguesia> freguesias = new ArrayList<Freguesia>();
		
		try {
			url = new URL(wsURL);
			connection = url.openConnection();
			httpCon = (HttpURLConnection) connection;
			
			byte[] buffer = new byte[xmlInput.length()];
			buffer = xmlInput.getBytes();
			
			// Set the appropriate HTTP parameters.
			httpCon.setRequestProperty("Content-Length", String.valueOf(buffer.length));
			httpCon.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			httpCon.setRequestMethod("GET");
			httpCon.setDoOutput(true);
			httpCon.setDoInput(true);
			out = httpCon.getOutputStream();
			out.write(buffer);
			out.close();
			
			//Read the response and write it to standard out.
			isr = new InputStreamReader(httpCon.getInputStream());
			in = new BufferedReader(isr);
			while ((responseString = in.readLine()) != null) {
				outputString = outputString + responseString;
			}
			LOGGER.info("Envelope SOAP: " + outputString);
			
			//Get the response from the web service call
			Document document = parseXmlFile(outputString);
			NodeList nodeList = document.getElementsByTagName("ns0:listarFreguesiasPorIdDistritoResponse");
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = nodeList.item(itr);
				LOGGER.info("Metodo: " + node.getNodeName());
			    if (node.getNodeType() == Node.ELEMENT_NODE) {
			    	Element eElement = (Element) node;
			    	for (int item = 0; item < eElement.getElementsByTagName("return").getLength(); item++) {
				        Freguesia freguesia = new Freguesia();
				        freguesia.setId(Long.parseLong(eElement.getElementsByTagName("id").item(item).getTextContent()));
				        freguesia.setNome(eElement.getElementsByTagName("nome").item(item).getTextContent());
				        freguesia.setConcelho(eElement.getElementsByTagName("concelho").item(item).getTextContent());
				        freguesia.setDistrito(eElement.getElementsByTagName("distrito").item(item).getTextContent());
				        freguesias.add(freguesia);
			    	}
			    }
			}
		} catch(Exception e) {
			LOGGER.error("Erro: " + e);
			System.err.println("Erro: " + e.getStackTrace());
		}
		return freguesias;
	}

	@Override
	public List<Concelho> listarConcelhosPorIdDistrito(Long idDistrito) {
		String xmlInput = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.JAXWSSOAPdivadmin.dzrrcreations.com/\">\r\n"
						+ "   <soapenv:Header/>\r\n"
						+ "   <soapenv:Body>\r\n"
						+ "      <ser:listarConcelhosPorIdDistrito>\r\n"
						+ "         <!--Optional:-->\r\n"
						+ "         <arg0>" + idDistrito + "</arg0>\r\n"
						+ "      </ser:listarConcelhosPorIdDistrito>\r\n"
						+ "   </soapenv:Body>\r\n"
						+ "</soapenv:Envelope>";

		List<Concelho> concelhos = new ArrayList<Concelho>();
		
		try {
			url = new URL(wsURL);
			connection = url.openConnection();
			httpCon = (HttpURLConnection) connection;
			
			byte[] buffer = new byte[xmlInput.length()];
			buffer = xmlInput.getBytes();
			
			// Set the appropriate HTTP parameters.
			httpCon.setRequestProperty("Content-Length", String.valueOf(buffer.length));
			httpCon.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			httpCon.setRequestMethod("GET");
			httpCon.setDoOutput(true);
			httpCon.setDoInput(true);
			out = httpCon.getOutputStream();
			out.write(buffer);
			out.close();
			
			//Read the response and write it to standard out.
			isr = new InputStreamReader(httpCon.getInputStream());
			in = new BufferedReader(isr);
			while ((responseString = in.readLine()) != null) {
				outputString = outputString + responseString;
			}
			LOGGER.info("Envelope SOAP: " + outputString);
			
			//Get the response from the web service call
			Document document = parseXmlFile(outputString);
			NodeList nodeList = document.getElementsByTagName("ns0:listarConcelhosPorIdDistritoResponse");
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = nodeList.item(itr);
				LOGGER.info("Metodo: " + node.getNodeName());
			    if (node.getNodeType() == Node.ELEMENT_NODE) {
			    	Element eElement = (Element) node;
			    	for (int item = 0; item < eElement.getElementsByTagName("return").getLength(); item++) {
				        Concelho concelho = new Concelho();
				        concelho.setId(Long.parseLong(eElement.getElementsByTagName("id").item(item).getTextContent()));
				        concelho.setNome(eElement.getElementsByTagName("nome").item(item).getTextContent());
				        concelho.setDistrito(eElement.getElementsByTagName("distrito").item(item).getTextContent());
				        concelhos.add(concelho);
			    	}
			    }
			}
		} catch(Exception e) {
			LOGGER.error("Erro: " + e);
			System.err.println("Erro: " + e.getStackTrace());
		}
		return concelhos;
	}

	@Override
	public Concelho mostrarConcelhoPorIdFreguesia(Long idFreguesia) {
		String xmlInput = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.JAXWSSOAPdivadmin.dzrrcreations.com/\">\r\n"
						+ "   <soapenv:Header/>\r\n"
						+ "   <soapenv:Body>\r\n"
						+ "      <ser:mostrarConcelhoPorIdFreguesia>\r\n"
						+ "         <!--Optional:-->\r\n"
						+ "         <arg0>" + idFreguesia + "</arg0>\r\n"
						+ "      </ser:mostrarConcelhoPorIdFreguesia>\r\n"
						+ "   </soapenv:Body>\r\n"
						+ "</soapenv:Envelope>";

		Concelho concelho = new Concelho();
		
		try {
			url = new URL(wsURL);
			connection = url.openConnection();
			httpCon = (HttpURLConnection) connection;
			
			byte[] buffer = new byte[xmlInput.length()];
			buffer = xmlInput.getBytes();
			
			// Set the appropriate HTTP parameters.
			httpCon.setRequestProperty("Content-Length", String.valueOf(buffer.length));
			httpCon.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			httpCon.setRequestMethod("GET");
			httpCon.setDoOutput(true);
			httpCon.setDoInput(true);
			out = httpCon.getOutputStream();
			out.write(buffer);
			out.close();
			
			//Read the response and write it to standard out.
			isr = new InputStreamReader(httpCon.getInputStream());
			in = new BufferedReader(isr);
			while ((responseString = in.readLine()) != null) {
				outputString = outputString + responseString;
			}
			LOGGER.info("Envelope SOAP: " + outputString);
			
			//Get the response from the web service call
			Document document = parseXmlFile(outputString);
			NodeList nodeList = document.getElementsByTagName("ns0:mostrarConcelhoPorIdFreguesiaResponse");
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = nodeList.item(itr);
				LOGGER.info("Metodo: " + node.getNodeName());
			    if (node.getNodeType() == Node.ELEMENT_NODE) {
			    	Element eElement = (Element) node;
			    	for (int item = 0; item < eElement.getElementsByTagName("return").getLength(); item++) {
				        concelho.setId(Long.parseLong(eElement.getElementsByTagName("id").item(item).getTextContent()));
				        concelho.setNome(eElement.getElementsByTagName("nome").item(item).getTextContent());
				        concelho.setDistrito(eElement.getElementsByTagName("distrito").item(item).getTextContent());
			    	}
			    }
			}
		} catch(Exception e) {
			LOGGER.error("Erro: " + e);
			System.err.println("Erro: " + e.getStackTrace());
		}
		return concelho;
	}

	@Override
	public Distrito mostrarDistritoPorIdFreguesia(Long idFreguesia) {
		String xmlInput = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.JAXWSSOAPdivadmin.dzrrcreations.com/\">\r\n"
						+ "   <soapenv:Header/>\r\n"
						+ "   <soapenv:Body>\r\n"
						+ "      <ser:mostrarDistritoPorIdFreguesia>\r\n"
						+ "         <!--Optional:-->\r\n"
						+ "         <arg0>" + idFreguesia + "</arg0>\r\n"
						+ "      </ser:mostrarDistritoPorIdFreguesia>\r\n"
						+ "   </soapenv:Body>\r\n"
						+ "</soapenv:Envelope>";

		Distrito distrito = new Distrito();
		
		try {
			url = new URL(wsURL);
			connection = url.openConnection();
			httpCon = (HttpURLConnection) connection;
			
			byte[] buffer = new byte[xmlInput.length()];
			buffer = xmlInput.getBytes();
			
			// Set the appropriate HTTP parameters.
			httpCon.setRequestProperty("Content-Length", String.valueOf(buffer.length));
			httpCon.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			httpCon.setRequestMethod("GET");
			httpCon.setDoOutput(true);
			httpCon.setDoInput(true);
			out = httpCon.getOutputStream();
			out.write(buffer);
			out.close();
			
			//Read the response and write it to standard out.
			isr = new InputStreamReader(httpCon.getInputStream());
			in = new BufferedReader(isr);
			while ((responseString = in.readLine()) != null) {
				outputString = outputString + responseString;
			}
			LOGGER.info("Envelope SOAP: " + outputString);
			
			//Get the response from the web service call
			Document document = parseXmlFile(outputString);
			NodeList nodeList = document.getElementsByTagName("ns0:mostrarDistritoPorIdFreguesiaResponse");
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = nodeList.item(itr);
				LOGGER.info("Metodo: " + node.getNodeName());
			    if (node.getNodeType() == Node.ELEMENT_NODE) {
			    	Element eElement = (Element) node;
			    	for (int item = 0; item < eElement.getElementsByTagName("return").getLength(); item++) {
				        distrito.setId(Long.parseLong(eElement.getElementsByTagName("id").item(item).getTextContent()));
				        distrito.setNome(eElement.getElementsByTagName("nome").item(item).getTextContent());
			    	}
			    }
			}
		} catch(Exception e) {
			LOGGER.error("Erro: " + e);
			System.err.println("Erro: " + e.getStackTrace());
		}
		return distrito;
	}

	@Override
	public Distrito mostrarDistritoPorIdConcelho(Long idConcelho) {
		String xmlInput = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.JAXWSSOAPdivadmin.dzrrcreations.com/\">\r\n"
						+ "   <soapenv:Header/>\r\n"
						+ "   <soapenv:Body>\r\n"
						+ "      <ser:mostrarDistritoPorIdConcelho>\r\n"
						+ "         <!--Optional:-->\r\n"
						+ "         <arg0>" + idConcelho + "</arg0>\r\n"
						+ "      </ser:mostrarDistritoPorIdConcelho>\r\n"
						+ "   </soapenv:Body>\r\n"
						+ "</soapenv:Envelope>";

		Distrito distrito = new Distrito();
		
		try {
			url = new URL(wsURL);
			connection = url.openConnection();
			httpCon = (HttpURLConnection) connection;
			
			byte[] buffer = new byte[xmlInput.length()];
			buffer = xmlInput.getBytes();
			
			// Set the appropriate HTTP parameters.
			httpCon.setRequestProperty("Content-Length", String.valueOf(buffer.length));
			httpCon.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			httpCon.setRequestMethod("GET");
			httpCon.setDoOutput(true);
			httpCon.setDoInput(true);
			out = httpCon.getOutputStream();
			out.write(buffer);
			out.close();
			
			//Read the response and write it to standard out.
			isr = new InputStreamReader(httpCon.getInputStream());
			in = new BufferedReader(isr);
			while ((responseString = in.readLine()) != null) {
				outputString = outputString + responseString;
			}
			LOGGER.info("Envelope SOAP: " + outputString);
			
			//Get the response from the web service call
			Document document = parseXmlFile(outputString);
			NodeList nodeList = document.getElementsByTagName("ns0:mostrarDistritoPorIdConcelhoResponse");
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = nodeList.item(itr);
				LOGGER.info("Metodo: " + node.getNodeName());
			    if (node.getNodeType() == Node.ELEMENT_NODE) {
			    	Element eElement = (Element) node;
			    	for (int item = 0; item < eElement.getElementsByTagName("return").getLength(); item++) {
				        distrito.setId(Long.parseLong(eElement.getElementsByTagName("id").item(item).getTextContent()));
				        distrito.setNome(eElement.getElementsByTagName("nome").item(item).getTextContent());
			    	}
			    }
			}
		} catch(Exception e) {
			LOGGER.error("Erro: " + e);
			System.err.println("Erro: " + e.getStackTrace());
		}
		return distrito;
	}
	
	private static Document parseXmlFile(String xmlString) {
	    //Parser that produces DOM object trees from XML content
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    //API to obtain DOM Document instance
	    DocumentBuilder builder = null;
	    try {
	    	//Create DocumentBuilder with default configuration
	    	builder = factory.newDocumentBuilder();
	    	//Parse the content to Document object
	    	Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
	    	return doc;
	    } catch (Exception e) {
	    	LOGGER.error("Erro de conversao string-xml", e);
	    }
	    return null;
	}

}
