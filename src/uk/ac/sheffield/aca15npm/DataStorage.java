package uk.ac.sheffield.aca15npm;
import java.io.IOException;
import java.util.*;

public class DataStorage extends WeatherViewer {
	//Initialise ArrayList that will store the observation objects.
	public static List<Observation> observations = new ArrayList<Observation>();
	private String nextLine;
	
	public void scanData() throws IOException {
	//while loop which causes the program to read in the entire file and add 
		//skips the first line which just contains titles
		nextLine = read.readLine();
		nextLine = read.readLine();
		nextLine = read.readLine();
	//loop until the end of the data and assign values to relevant data types
		while (read.readLine() != null) {
			//while more information is available for reading, split each line into an array and add each index of the array into
			//its respective index of the ArrayList. Where necessary, validations exist in order to replace data that may be missing 
			//or in an incorrect format.
			String[] weatherData = nextLine.split(",");
			String time = weatherData[0]; //add time data	
			double temperature = Double.parseDouble(weatherData[1]);
			double dewP = Double.parseDouble(weatherData[2]);
			int humidity = Integer.parseInt(weatherData[3]);
			
			double seaLvlPressr;
			if (weatherData[4].equals("")) {
				seaLvlPressr = 0;
			} else {
				seaLvlPressr = Double.parseDouble(weatherData[4]);
			}
			
			String visibility;
			if (Double.parseDouble(weatherData[5]) < 0) {
				visibility = "Not available"; 
			} else {
				visibility = weatherData[5];
			}
			
			String windDirection = weatherData[6];
			
			String windSpd;
			if (weatherData[7].equals("Calm")) {
				windSpd = "0";
			} else {
				windSpd = weatherData[7];
			}
			
			Double gustSpd;
			if (weatherData[8].equals("-") == true || weatherData[8].equals("") == true) { 
				gustSpd = 0.0; 
			} else {
				gustSpd = Double.parseDouble(weatherData[8]); 
			}
			
			int precip;
			if (weatherData[9].equals("N/A") == true || weatherData[9].equals("") == true) { 
				precip = 0;
			} else {
				precip = Integer.parseInt(weatherData[9]);
			}
			
			String events;
			if (weatherData[10].equals("") == true) {
				events = "No events to show";
			} else {
				events = weatherData[10];
			}
			
			String conditions;			
			if (weatherData[11].equals("") == true) { 
				conditions = "Unknown";
			} else {
				conditions = weatherData[11];
			}
			
			int windDirDegrees;
			if (weatherData[12].equals("") == true) {
				windDirDegrees = 0;
			} else {
				windDirDegrees = Integer.parseInt(weatherData[12]);
			}
			
			String dateUTC = weatherData[13];
			
			//declare a new observation object for each loop and take the data from the arraylists as the variables
			Observation observation = new Observation(time, temperature, dewP, humidity, seaLvlPressr, visibility, windDirection, 
					windSpd, gustSpd, precip, events, conditions, windDirDegrees, dateUTC);
			observations.add(observation);
			nextLine = read.readLine();
		}//while loop ends here
	}//scanData ends here
}	