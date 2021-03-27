package com.example.springboot.model;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

import com.example.springboot.helper.Constants;
import com.opencsv.CSVReader;

public class CSVRead {
	SimpleDateFormat callDateFormatter = new SimpleDateFormat("M/dd/yyyy hh:mm", Locale.ENGLISH);
	SimpleDateFormat reportDateformatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);

	public HashMap<String, CallModel> readCallServiceCSV() {
		HashMap<String, CallModel> callList = new HashMap<String, CallModel>();
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(Constants.CALL_SERVICE_CSV));
			String[] nextLine;
			reader.readNext();
			while ((nextLine = reader.readNext()) != null) {

				CallModel call = new CallModel();
				call.setItemNumber(nextLine[0]);
				call.setCompliantText(nextLine[13]);
				call.setZip(nextLine[18]);

				String loc = nextLine[20];
				if (!loc.isEmpty() && loc.split(",").length == 2) {
					String lat = loc.split(",")[0];
					String longi = loc.split(",")[1];
					call.setLatitude(lat.substring(1));
					call.setLongitude(longi.substring(1, longi.length() - 2));
				} else {
					call.setLatitude("");
					call.setLongitude("");

				}
				call.setStreetAddress(nextLine[17]);
				try {
					call.setTimeCreated(callDateFormatter.parse(nextLine[6]));
					call.setTimeClosed(callDateFormatter.parse(nextLine[9]));
				} catch (ParseException e) {
					call.setTimeClosed(null);
					call.setTimeCreated(null);
				}
				callList.put(nextLine[0], call);

			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("CSV Read File error");
		}
		return callList;
	}

	public HashMap<String, PoliceReportModel> readReportCSV() {
		HashMap<String, PoliceReportModel> reportList = new HashMap<>();
		try {
			CSVReader reader = new CSVReader(new FileReader(Constants.POLICE_REPORT_CSV));
			String[] nextLine;
			reader.readNext();

			while ((nextLine = reader.readNext()) != null) {
				PoliceReportModel report = new PoliceReportModel();

				report.setItemNumber(nextLine[0].replace("-", ""));
				report.setLocation(nextLine[2]);
				report.setDisposition(nextLine[3]);
				report.setDipositionText(nextLine[5]);
				report.setOccuredAt(callDateFormatter.parse(nextLine[6]));
				if (nextLine[9].equals("NULL") && nextLine[10].equals("NULL") && nextLine[11].equals("NULL"))
					continue;
				report.setOffenderRace(nextLine[9]);
				report.setOffenderGender(nextLine[10]);
				report.setOffenderAge(nextLine[11]);
				if (nextLine[14].equals("NULL") && nextLine[15].equals("NULL") && nextLine[16].equals("NULL"))
					continue;
				report.setVictimRace(nextLine[14]);
				report.setVictimGender(nextLine[15]);
				report.setVictimAge(nextLine[16]);
				report.setVictimNumber(nextLine[17]);
				report.setFatalStatus(nextLine[18]);
				report.setReportType(nextLine[20]);

				reportList.put(nextLine[0].replace("-", ""), report);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportList;
	}

}
