package com.example.springboot.helper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;

import com.example.springboot.model.CallModel;

public class CallServiceRDFGenerator {

	OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RULE_INF);

	public Model createCallServiceModel(HashMap<String, CallModel> callList) {

		Resource callService = model.createResource(Constants.CALL_SERVICE_URI, RDFS.Class);
		
		Resource location = model.createResource(Constants.CALL_SERVICE_LOCATION_URI, RDFS.Class);
		Resource time = model.createResource(Constants.CALL_SERVICE_TIME_URI, RDFS.Class);

		Resource state = model.createResource(Constants.CALL_SERVICE_LOCATION_URI + "/state");
		Resource zip = model.createResource(Constants.CALL_SERVICE_LOCATION_URI + "/zip");
		Resource latitude = model.createResource(Constants.CALL_SERVICE_LOCATION_URI + "/lat");
		Resource longitude = model.createResource(Constants.CALL_SERVICE_LOCATION_URI + "/long");
		Resource street = model.createResource(Constants.CALL_SERVICE_LOCATION_URI + "/street");

		ObjectProperty hasSate = model.createObjectProperty(Constants.CALL_SERVICE_LOCATION_URI + "/hasState");
		hasSate.addDomain(location);
		hasSate.addRange(state);
		ObjectProperty isSateOf = model.createObjectProperty(Constants.CALL_SERVICE_LOCATION_URI + "/isStateOf");
		isSateOf.addInverseOf(hasSate);

		ObjectProperty hasZip = model.createObjectProperty(Constants.CALL_SERVICE_LOCATION_URI + "/hasZip");
		hasZip.addDomain(location);
		hasZip.addRange(zip);
		ObjectProperty isZipOf = model.createObjectProperty(Constants.CALL_SERVICE_LOCATION_URI + "/isZipOf");
		isZipOf.addInverseOf(hasZip);

		ObjectProperty hasLat = model.createObjectProperty(Constants.CALL_SERVICE_LOCATION_URI + "/hasLat");
		hasLat.addDomain(location);
		hasLat.addRange(latitude);
		ObjectProperty isLatOf = model.createObjectProperty(Constants.CALL_SERVICE_LOCATION_URI + "/isLatOf");
		isLatOf.addInverseOf(hasLat);

		ObjectProperty hasLong = model.createObjectProperty(Constants.CALL_SERVICE_LOCATION_URI + "/hasLong");
		hasLong.addDomain(location);
		hasLong.addRange(longitude);
		ObjectProperty isLongOf = model.createObjectProperty(Constants.CALL_SERVICE_LOCATION_URI + "/isLongOf");
		isLongOf.addInverseOf(hasLong);

		ObjectProperty hasStreet = model.createObjectProperty(Constants.CALL_SERVICE_LOCATION_URI + "/hasStreet");
		hasStreet.addDomain(location);
		hasStreet.addRange(street);
		ObjectProperty isStreetOf = model.createObjectProperty(Constants.CALL_SERVICE_LOCATION_URI + "/isStreetOf");
		isStreetOf.addInverseOf(hasStreet);

		// time class

		ObjectProperty hastimeCreated = model.createObjectProperty(Constants.CALL_SERVICE_TIME_URI + "/hastimeCreated");
		hastimeCreated.addDomain(time);
		hastimeCreated.addRange(XSD.dateTimeStamp);
		ObjectProperty isTimeCreatedOf = model
				.createObjectProperty(Constants.CALL_SERVICE_TIME_URI + "/isTimeCreatedOf");
		isTimeCreatedOf.addInverseOf(hastimeCreated);

		ObjectProperty hastimeClosed = model.createObjectProperty(Constants.CALL_SERVICE_TIME_URI + "/hastimeClosed");
		hastimeClosed.addDomain(time);
		hastimeClosed.addRange(XSD.dateTimeStamp);
		ObjectProperty isTimeClosedOf = model.createObjectProperty(Constants.CALL_SERVICE_TIME_URI + "/isTimeClosedOf");
		isTimeClosedOf.addInverseOf(hastimeClosed);

		// Call class

		ObjectProperty hasOccuredAt = model.createObjectProperty(Constants.CALL_SERVICE_URI + "/hasOccuredAt");
		hasOccuredAt.addDomain(callService);
		hasOccuredAt.addRange(location);
		ObjectProperty isOccuredAtOf = model.createObjectProperty(Constants.CALL_SERVICE_URI + "/isOccuredAtOf");
		isOccuredAtOf.addInverseOf(hasOccuredAt);

		ObjectProperty hasOccuredOn = model.createObjectProperty(Constants.CALL_SERVICE_URI + "/hasOccuredOn");
		hasOccuredOn.addDomain(callService);
		// hasOccuredOn.setRDFType(OWL.FunctionalProperty);
		hasOccuredOn.addRange(time);
		ObjectProperty isOccuredOnOf = model.createObjectProperty(Constants.CALL_SERVICE_URI + "/isOccuredOnOf");
		isOccuredOnOf.addInverseOf(hasOccuredOn);

		Resource compliant = model.createResource(Constants.CALL_SERVICE_URI + "/compliant");
		compliant.addProperty(RDFS.domain, callService);
		compliant.addProperty(RDFS.range, XSD.xstring);

		Resource item_number = model.createResource(Constants.CALL_SERVICE_URI + "/item_number");
		item_number.addProperty(RDFS.domain, callService);
		item_number.addProperty(RDFS.range, XSD.xstring);

		ObjectProperty hasComplaint = model.createObjectProperty(Constants.CALL_SERVICE_URI + "/hasComplaint");
		hasComplaint.addDomain(callService);
		hasComplaint.setRDFType(OWL.FunctionalProperty);
		hasComplaint.addRange(compliant);
		ObjectProperty isComplaintOf = model.createObjectProperty(Constants.CALL_SERVICE_URI + "/isComplaintOf");
		isComplaintOf.addInverseOf(hasComplaint);

		ObjectProperty hasitemNumber = model.createObjectProperty(Constants.CALL_SERVICE_URI + "/hasitemNumber");
		hasitemNumber.addDomain(callService);
		hasitemNumber.setRDFType(OWL.FunctionalProperty);
		hasitemNumber.addRange(item_number);
		ObjectProperty isItemNumberOf = model.createObjectProperty(Constants.CALL_SERVICE_URI + "/isItemNumberOf");
		isItemNumberOf.addInverseOf(hasitemNumber);

		model.setNsPrefix("cs", Constants.CALL_SERVICE_URI + "/");
		model.setNsPrefix("csbase", Constants.RESOURCE_URI + "/");
		model.setNsPrefix("location", Constants.CALL_SERVICE_LOCATION_URI + "/");
		model.setNsPrefix("time", Constants.CALL_SERVICE_TIME_URI + "/");
		int count = 0;
		for (String item : callList.keySet()) {
			if (count > 30000) {
				break;
			}
			count++;
			CallModel call = callList.get(item);

			Resource cs = model.createResource(Constants.CALL_SERVICE_URI + "/" + item, callService);

			Resource loc = model.createResource(Constants.CALL_SERVICE_LOCATION_URI + "/" + item, location);

			Resource tm = model.createResource(Constants.CALL_SERVICE_TIME_URI + "/" + item, time);
			model.add(loc, hasLat, call.getLatitude());
			model.add(loc, hasLong, call.getLongitude());
			model.add(loc, hasStreet, call.getStreetAddress());
			model.add(loc, hasStreet, call.getStreetAddress());
			model.add(loc, hasZip, call.getZip());
			model.add(tm, hastimeClosed, call.getTimeClosed().toString());
			model.add(tm, hastimeCreated, call.getTimeCreated().toString());
			model.add(cs, hasOccuredAt, tm);
			model.add(cs, hasOccuredOn, loc);
			model.add(cs, hasComplaint, call.getCompliantText());
			model.add(cs, hasitemNumber, call.getItemNumber());
		}
		return model;
	}

	public void writeInOutputFile(Model model) {
		try {
			model.write(new FileOutputStream(Constants.CALL_SERVICE_OUTPUT_CSV_PATH), "RDF/XML");
		} catch (FileNotFoundException e) {
			System.err.println("File not found for output of Call Serive CSV");
		}

	}
}
