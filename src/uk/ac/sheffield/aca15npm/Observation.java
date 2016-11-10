package uk.ac.sheffield.aca15npm;

//Observation class, used to store the Observation object constructor and the get methods.
public class Observation {
	//Initialise all relevant variables
	private String timeBST, windSpeed, windDirection, events, conditions, dateUTC, visibility;
	private double temperature, dewPoint, gustSpeed, seaLevelPressure;
	private int humidity, precipitation, windDirDegrees;
	
	//Constructor for Observation object
	public Observation(String t, double temp, double d, int h, double s, String v, String w,
			String windSpd, double g, int p, String e, String c, int windDirD, String dUTC) {
		this.timeBST = t;
		this.temperature = temp;
		this.dewPoint = d;
		this.humidity = h;
		this.seaLevelPressure = s;
		this.visibility = v;
		this.windDirection = w;
		this.windSpeed = windSpd;
		this.gustSpeed = g;
		this.precipitation = p;
		this.events = e;
		this.conditions = c;
		this.windDirDegrees = windDirD;
		this.dateUTC = dUTC;
	}
	
	//Create the get methods.
	public String getTime() {
		return timeBST;
	}
	
	public double getTemp() {
		return temperature;
	}
	
	public double getDewPoint() {
		return dewPoint;
	}
	
	public int getHumidity() {
		return humidity;
	}
	
	public double getSeaLevelPressure() {
		return seaLevelPressure;
	}
	
	public String getVisibility() {
		return visibility;
	}
	
	public String getWindDirection() {
		return windDirection;
	}
	
	public String getWindSpeed() {
		return windSpeed;
	}
	
	public double getGustSpeed() {
		return gustSpeed;
	}
	
	public int getPrecipitation() {
		return precipitation;
	}
	
	public String getEvents() {
		return events;
	}
	
	public String getConditions() {
		return conditions;
	}
	
	public int getWindDirDegrees() {
		return windDirDegrees;
	}
	
	public String getDate() {
		return dateUTC;
	}
	
}