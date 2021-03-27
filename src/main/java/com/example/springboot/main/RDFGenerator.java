package com.example.springboot.main;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import org.apache.jena.rdf.model.Model;

import com.example.springboot.helper.CallServiceRDFGenerator;
import com.example.springboot.helper.PoliceReportRDFGenerator;
import com.example.springboot.model.CSVRead;
import com.example.springboot.model.CallModel;
import com.example.springboot.model.PoliceReportModel;


public class RDFGenerator {
	static CSVRead csv2rdf = new CSVRead();
	static CallServiceRDFGenerator callServiceRDFGenerator=new CallServiceRDFGenerator();
	static PoliceReportRDFGenerator policeReportRDFGenerator=new PoliceReportRDFGenerator();

	public static void main(String args[]) throws IOException, ParseException {
		makeCallServiceRDF();
		makePoliceReportRDF();
	}

	public static void makeCallServiceRDF(){
		HashMap<String, CallModel> callList = csv2rdf.readCallServiceCSV();
		Model model=callServiceRDFGenerator.createCallServiceModel(callList);
		callServiceRDFGenerator.writeInOutputFile(model);
	}
	
	public static void makePoliceReportRDF(){
		HashMap<String, PoliceReportModel> policeReportList = csv2rdf.readReportCSV();
		Model model=policeReportRDFGenerator.createPoliceReportModel(policeReportList);
		policeReportRDFGenerator.writeInOutputFile(model);
	}
}
