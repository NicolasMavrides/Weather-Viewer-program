package uk.ac.sheffield.aca15npm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class WeatherViewer {
	
	protected static BufferedReader read;
	//method to read the data from the wunderground server
	public static void readURL(String airportCode, int year, int month, int date) {
		try {
			//create URL object. URL will vary depending on the user input for date and airport
			URL data = new URL("https://www.wunderground.com/history/airport/"+airportCode+"/"+year+"/"+month+"/"+date+"/DailyHistory.html?HideSpecis%20=1&format=1");
			read = new BufferedReader(new InputStreamReader(data.openStream()));
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		StartupFrame startupFrame = new StartupFrame();
		startupFrame.setVisible(true);
	}
}