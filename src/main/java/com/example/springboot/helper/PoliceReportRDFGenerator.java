package com.example.springboot.helper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;

import com.example.springboot.model.PoliceReportModel;

public class PoliceReportRDFGenerator {
	public Model createPoliceReportModel(HashMap<String, PoliceReportModel> policeReportList) {
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RULE_INF);

		Resource policeReport = model.createResource(Constants.POLICE_REPORT_URI, RDFS.Class);

		Resource person = model.createResource(Constants.POLICE_REPORT_PERSON_URI, RDFS.Class);
		Property age = model.createProperty(Constants.POLICE_REPORT_PERSON_URI + "/age");
		age.addProperty(RDFS.domain, person);
		age.addProperty(RDFS.range, XSD.integer);
		Property race = model.createProperty(Constants.POLICE_REPORT_PERSON_URI + "/race");
		race.addProperty(RDFS.domain, person);
		race.addProperty(RDFS.range, XSD.integer);
		Property gender = model.createProperty(Constants.POLICE_REPORT_PERSON_URI + "/gender");
		gender.addProperty(RDFS.domain, person);
		gender.addProperty(RDFS.range, XSD.integer);

		ObjectProperty hasOffender = model.createObjectProperty(Constants.POLICE_REPORT_URI + "/hasOffender");
		hasOffender.addDomain(policeReport);
		hasOffender.addRange(person);
		ObjectProperty isOffenderOf = model.createObjectProperty(Constants.POLICE_REPORT_URI + "/isOffenderOf");
		isOffenderOf.addInverseOf(hasOffender);

		ObjectProperty hasVictim = model.createObjectProperty(Constants.POLICE_REPORT_URI + "/hasVictim");
		hasVictim.addDomain(policeReport);
		hasVictim.addRange(person);
		ObjectProperty isOccuredAtOf = model.createObjectProperty(Constants.POLICE_REPORT_URI + "/isOccuredAtOf");
		isOccuredAtOf.addInverseOf(hasVictim);

//		ObjectProperty hasDeposition = model.createObjectProperty(Constants.POLICE_REPORT_URI + "/hasDeposition");
//		Literal ratingLow = model.createTypedLiteral(1);
//		Literal ratingHigh = model.createTypedLiteral(5);
//		DataRange ratingRange = model.createDataRange(model.createList(ratingLow, ratingHigh));
//		hasRating.addRange(ratingRange);
//		hasRating.addDomain(createClassIfAbsent(Class_Name.Hospital.getURI()));
//		ObjectProperty isRatingOf = createPropIfAbsent(sourceURI + Prop_Name.isRatingOf);
//		isRatingOf.addComment("is a hospital's rating from 1-5", "EN");
//		isRatingOf.addInverseOf(hasRating);

		Property item_number = model.createProperty(Constants.POLICE_REPORT_URI + "/item_number");
		item_number.addProperty(RDFS.domain, policeReport);
		item_number.addProperty(RDFS.range, XSD.xstring);
		Property disposition_text = model.createProperty(Constants.POLICE_REPORT_URI + "/dispositionText");
		disposition_text.addProperty(RDFS.domain, policeReport);
		disposition_text.addProperty(RDFS.range, XSD.xstring);
		Property occuredDate = model.createProperty(Constants.POLICE_REPORT_URI + "/occuredDate");
		occuredDate.addProperty(RDFS.domain, policeReport);
		occuredDate.addProperty(RDFS.range, XSD.dateTime);
		Property deposition = model.createProperty(Constants.POLICE_REPORT_URI + "/deposition");
		deposition.addProperty(RDFS.domain, policeReport);
		deposition.addProperty(RDFS.range, XSD.xstring);
		Property location = model.createProperty(Constants.POLICE_REPORT_URI + "/location");
		location.addProperty(RDFS.domain, policeReport);
		location.addProperty(RDFS.range, XSD.xstring);
		Property reportType = model.createProperty(Constants.POLICE_REPORT_URI + "/reportType");
		reportType.addProperty(RDFS.domain, policeReport);
		reportType.addProperty(RDFS.range, XSD.xstring);
		Property fatalStatus = model.createProperty(Constants.POLICE_REPORT_URI + "/fatalStatus");
		fatalStatus.addProperty(RDFS.domain, policeReport);
		fatalStatus.addProperty(RDFS.range, XSD.xstring);

		model.setNsPrefix("pr", Constants.POLICE_REPORT_URI + "/");
		model.setNsPrefix("prbase", Constants.RESOURCE_URI + "/");
		model.setNsPrefix("person", Constants.POLICE_REPORT_PERSON_URI + "/");

		int count = 0;
		for (String policeReportKey : policeReportList.keySet()) {
			if (count > 30000)
				break;
			PoliceReportModel reportModel = policeReportList.get(policeReportKey);
			Resource reportResource = model.createResource(Constants.POLICE_REPORT_URI + "/" + policeReportKey,
					policeReport);

			reportResource.addProperty(item_number, policeReportKey);
			reportResource.addProperty(deposition, reportModel.getDisposition());
			reportResource.addProperty(disposition_text, reportModel.getDipositionText());
			reportResource.addProperty(reportType, reportModel.getReportType());
			reportResource.addProperty(fatalStatus, reportModel.getFatalStatus());
			reportResource.addProperty(location, reportModel.getLocation());
			reportResource.addProperty(reportType, reportModel.getReportType());
			reportResource.addProperty(occuredDate, reportModel.getOccuredAt().toString());

			Resource victim = model.createResource(Constants.POLICE_REPORT_PERSON_URI + "/victim/" + policeReportKey,
					person);
			victim.addProperty(age, reportModel.getVictimAge());
			victim.addProperty(gender, reportModel.getVictimGender());
			victim.addProperty(race, reportModel.getVictimRace());

			reportResource.addProperty(hasVictim, victim);

			Resource offender = model
					.createResource(Constants.POLICE_REPORT_PERSON_URI + "/offender/" + policeReportKey, person);
			offender.addProperty(age, reportModel.getOffenderAge());
			offender.addProperty(gender, reportModel.getOffenderGender());
			offender.addProperty(race, reportModel.getOffenderRace());

			model.add(reportResource, hasOffender, offender);
			model.add(reportResource, hasVictim, victim);
			count++;
		}

		return model;
	}

	public void writeInOutputFile(Model model) {
		try {
			model.write(new FileOutputStream(Constants.POLICE_REPORT_OUTPUT_CSV_PATH), "RDF/XML");
		} catch (FileNotFoundException e) {
			System.err.println("File not found for output of Call Serive CSV");
		}
	}

}
