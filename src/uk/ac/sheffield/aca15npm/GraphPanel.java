package uk.ac.sheffield.aca15npm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.Date;

import javax.swing.JPanel;

import javafx.scene.text.Font;

@SuppressWarnings("serial")
public class GraphPanel extends JPanel {
	String graph;
	String button;
	int scale;
	
	public GraphPanel(String button) {
		this.button = button;
		setBackground(Color.white);;
	}
	
	public void setGraph(String graph) {
		
	}
	
	public void paintComponent(Graphics g) {
		int arrayListSize = DataStorage.observations.size();
		super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;
	    
		if (button == "Temperature") {
			//draw the axes
		    g2.setStroke(new BasicStroke(2));
			g2.drawLine(40, 0, 40, 600);
			g2.drawLine(40, 430, 1000, 430);	
			
			//loop to draw labels on the x-axis
			int xPosIncrement = 0; //increment value for x-axis labels
			for (int x=0; x<arrayListSize-1; x+= 2) {
				String timeLabel = DataStorage.observations.get(x).getTime();
				g2.drawString(timeLabel, 40+xPosIncrement, 560);
				xPosIncrement = xPosIncrement+54;
			}
			
			//loop to draw labels on the y-axis
			int yPosIncrement = 0;
			for (int y=-9; y<=28; y++) {
				g2.drawString(String.valueOf(y), 20, 570-yPosIncrement);
				yPosIncrement = yPosIncrement + 15;
			}
			
			//loop to plot the temperature line graph
			int xCoord = 40; //starting x-coordinate for the plotted line
			for (int z=0; z<arrayListSize-1; z++) {
				int initialTemp = (int)DataStorage.observations.get(z).getTemp();
				int finalTemp = (int)DataStorage.observations.get(z+1).getTemp();
				g2.drawLine(xCoord, 430-(initialTemp*15), xCoord+27, 430-(finalTemp*15));
				xCoord = xCoord+27;
				
			//loop to find and display the average temperature
			double sumOfReadings = 0;
			for(int i=0; i<arrayListSize-1; i++) {
				sumOfReadings = sumOfReadings + DataStorage.observations.get(i).getTemp();
			}
			String avTemp = "Average recorded temperature: "+String.valueOf(sumOfReadings/arrayListSize)+" C";
			g2.drawString(avTemp, 600, 10);
			}
			
		} else if (button == "Pressure") {
			//draw the axes
		    g2.setStroke(new BasicStroke(2));
			g2.drawLine(40, 0, 40, 540);
			g2.drawLine(40, 540, 1000, 540);	
			
			//loop to draw labels on the x-axis
			int xPosIncrement = 0; //increment value for x-axis labels
			for (int x=0; x<arrayListSize-1; x+= 2) {
				String timeLabel = DataStorage.observations.get(x).getTime();
				g2.drawString(timeLabel, 40+xPosIncrement, 560);
				xPosIncrement = xPosIncrement+54;
			}
			
			//loop to draw labels on the y-axis
			int yPosIncrement = 0;
			for (int y=950; y<=1050; y+=5) {
				g2.drawString(String.valueOf(y), 10, 540-yPosIncrement);
				yPosIncrement = yPosIncrement + 25;
			}
			
			//loop to plot the pressure line graph
			int xCoord = 40; //starting x-coordinate for the plotted line
			for (int z=0; z<arrayListSize-1; z++) {
				int initialPres = (int)DataStorage.observations.get(z).getSeaLevelPressure();
				int finalPres = (int)DataStorage.observations.get(z+1).getSeaLevelPressure();
				//plot pressure line on the graph by calculating the position through comparison with the starting value and scale
				//if any pressure readings are missing (ie. set to 0), the loop skips those readings.
				if (initialPres == 0 || finalPres == 0) { 
				} else {
				g2.drawLine(xCoord, 540-((initialPres-950)*5), xCoord+27, 540-((finalPres-950)*5));
				xCoord = xCoord+27;
				}
			}
			//Compare first and last pressure readings to find pressure trend
			double firstRead = DataStorage.observations.get(0).getSeaLevelPressure();
			double lastRead = DataStorage.observations.get(arrayListSize-1).getSeaLevelPressure();
			double pressureTrend = (lastRead - firstRead);
			
			if (pressureTrend < 0) {
				g2.drawString("Pressure trend is decreasing by "+pressureTrend*(-1)+" hPa", 600, 10);
			} else if (pressureTrend > 0) {
				g2.drawString("Pressure trend is rising by "+pressureTrend+" hPa", 600, 10);
			} else {
				g2.drawString("Pressure Trend is static", 600, 10);
			}
			
		} else if (button == "Wind Data") {
			//draw the axes
		    g2.setStroke(new BasicStroke(2));
			g2.drawLine(40, 0, 40, 540);
			g2.drawLine(40, 540, 1000, 540);	
			
			//loop to draw labels on the x-axis
			int xPosIncrement = 0; //increment value for x-axis labels
			for (int x=0; x<arrayListSize-1; x+= 2) {
				String timeLabel = DataStorage.observations.get(x).getTime();
				g2.drawString(timeLabel, 40+xPosIncrement, 560);
				xPosIncrement = xPosIncrement+54;
			}
			
			//loop to draw labels on the y-axis
			int yPosIncrement = 0;
			for (int y=0; y<=100; y+=10) {
				g2.drawString(String.valueOf(y), 15, 540-yPosIncrement);
				yPosIncrement = yPosIncrement + 50;
			}
			
			//loop to plot the wind speed line graph
			int xCoordwind = 40; //starting x-coordinate for the plotted line
			for (int z=0; z<arrayListSize-1; z++) {
				int initialwSpd = (int)Double.parseDouble(DataStorage.observations.get(z).getWindSpeed());
				int finalwSpd = (int)Double.parseDouble(DataStorage.observations.get(z+1).getWindSpeed());
				//plot wind speed line on the graph by calculating the position through comparison with the starting value and scale
				g2.drawLine(xCoordwind, 540-(initialwSpd*5), xCoordwind+27, 540-(finalwSpd*5));
				xCoordwind = xCoordwind+27;
			}
			
			//loop to plot the gust speed points on the graph
			int xCoordgust = 40; //starting x-coordinate for the first reading
			for (int z=0; z<arrayListSize-1; z++) {
				int gustSpd = (int)DataStorage.observations.get(z).getGustSpeed();	
				//plot gust speed points on the graph by calculating the position through comparison with the starting value and scale
				//check if there is any gust speed data
				if (gustSpd != 0) {
				g2.fillRect(xCoordgust, 540-(gustSpd*5), 5, 5);
				xCoordgust = xCoordgust+27;
				} else {
				//if there isn't, don't plot any point for the respective time.
				xCoordgust = xCoordgust+27;
				}		
			}
			
			//loop to find and plot the average wind speed
			double sumOfReadings = 0;
			for (int i=0; i<arrayListSize-1; i++) {
				sumOfReadings = sumOfReadings + Double.parseDouble(DataStorage.observations.get(i).getWindSpeed());
			}
			//find average wind speed and plot it as a line on the graph
			int avWindSpd = (int)sumOfReadings/arrayListSize;
			g2.setColor(Color.blue);
			g2.drawLine(40, 540-(avWindSpd*5), 1000, 540-(avWindSpd*5));
			
			//add info key to the graph
			g2.setColor(Color.black);
			g2.drawString("Wind speed (km/h)", 810, 10);
			g2.drawLine(700, 10, 800, 10);
			
			g2.setColor(Color.blue);
			g2.drawString("Average wind speed of the day (km/h)", 810, 25);
			g2.drawLine(700, 25, 800, 25);
			
			g2.setColor(Color.black);
			g2.fillRect(800, 40, 5, 5);
			g2.drawString("Gust speed (km/h)", 810, 45);

		} else if (button == "Total Precipitation") {
			double totalPrecip = 0;
			for (int i=0; i<arrayListSize-1; i++) {
				totalPrecip = totalPrecip + DataStorage.observations.get(i).getPrecipitation();
			}
	        g2.drawString("Total precipitation is "+totalPrecip+" mm", 450, 250);
			
		} else if (button == "Exit Program") {
			//exit the program
			System.exit(1);
		}
	}
}