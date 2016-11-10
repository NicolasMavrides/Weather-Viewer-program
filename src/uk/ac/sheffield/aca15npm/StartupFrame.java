package uk.ac.sheffield.aca15npm;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.Year;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class StartupFrame extends JFrame implements ActionListener {
	
	private JButton search;
	private JComboBox date;
	private JComboBox month;
	private JComboBox year;
	private JComboBox airport;
	private static String[] airportList = {"London Heathrow", "Manchester"};
	private static String[] dateList = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19","20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	private static String[] monthList = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October","November", "December"};
	private static String[] yearList = {"2016", "2015", "2014", "2013", "2012", "2011", "2010"};
	private static String selectedAirport;
	private static int selectedDate;
	private static int selectedMonth;
	private static int selectedYear;
	private static JOptionPane caution;
	
	public StartupFrame() throws IOException {
		//set GUI properties
		setTitle("Airport Weather Data Viewer");
		setSize(300,130);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		//add ComboBoxes
		date = new JComboBox(dateList);
		month = new JComboBox(monthList);
		year = new JComboBox(yearList);
		airport = new JComboBox(airportList);
		
		//add labels and submit button
		JLabel dateLabel = new JLabel("Select date: ");
		JLabel airportLabel = new JLabel("Select airport: ");
		search = new JButton("Find records");
		
		//add action listeners
		date.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		month.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		year.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		airport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		search.addActionListener(this);

		//set ComboBox properties
		airport.setSize(220, airport.getPreferredSize().height);
		airport.setEditable(false);
	
		date.setSize(2, date.getPreferredSize().height);
		date.setEditable(false);
			
		month.setSize(10, month.getPreferredSize().height);
		month.setEditable(false);
	
		year.setSize(5, year.getPreferredSize().height);
		year.setEditable(false);

		//flow layout
		Container mainMenu = getContentPane();
		mainMenu.setLayout(new FlowLayout());
		mainMenu.add(dateLabel);
		mainMenu.add(date);
		mainMenu.add(month);
		mainMenu.add(year);
		mainMenu.add(airportLabel);
		mainMenu.add(airport);
		mainMenu.add(search);
	}
	
	public void actionPerformed(ActionEvent e) {	
		//create object for the second GUI frame
		DataDisplayFrame datadisplay = new DataDisplayFrame();
		
		selectedYear = Integer.parseInt(year.getSelectedItem().toString());
		selectedDate = Integer.parseInt(date.getSelectedItem().toString());

		//convert the selected month into an integer value
		if (month.getSelectedItem().toString() == "January") {
			selectedMonth = 1;
		} else if (month.getSelectedItem().toString() == "February") {
			selectedMonth = 2;
		} else if (month.getSelectedItem().toString() == "March") {
			selectedMonth = 3;
		} else if (month.getSelectedItem().toString() == "April") {
			selectedMonth = 4;
		} else if (month.getSelectedItem().toString() == "May") {
			selectedMonth = 5;
		} else if (month.getSelectedItem().toString() == "June") {
			selectedMonth = 6;
		} else if (month.getSelectedItem().toString() == "July") {
			selectedMonth = 7;
		} else if (month.getSelectedItem().toString() == "August") {
			selectedMonth = 8;
		} else if (month.getSelectedItem().toString() == "September") {
			selectedMonth = 9;
		} else if (month.getSelectedItem().toString() == "October") {
			selectedMonth = 10;
		} else if (month.getSelectedItem().toString() == "November") {
			selectedMonth = 11;
		} else {
			selectedMonth = 12;
		}
				
		//convert the selected airport into an airport code
		if (airport.getSelectedItem().toString() == "London Heathrow") {
			selectedAirport = "EGLL";
		} else if (airport.getSelectedItem().toString() == "Manchester") {
			selectedAirport = "EGCC";
		}
		
		//retrieve the data from the wunderground server and scan it into the Data Storage ArrayList
		WeatherViewer.readURL(selectedAirport, selectedYear, selectedMonth, selectedDate);
		DataStorage data = new DataStorage();
		try {
			data.scanData();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//tests if the user selected the 29thFebruary on a leap year. If so, open the second GUI frame.
		if ( (selectedYear == 2012 && selectedMonth == 2 && selectedDate == 29) || 
			 (selectedYear == 2016 && selectedMonth == 2 && selectedDate == 29) ) {
			datadisplay.setVisible(true);
		
		//if otherwise a user selects a date greater than 30th (or 28th for February) for months that have 30 days, pop up an error message.
		} else if ( (selectedMonth == 4 && selectedDate > 30) || (selectedMonth == 6 && selectedDate > 30) || 
				 	(selectedMonth == 9 && selectedDate > 30) || (selectedMonth == 9 && selectedDate > 30) || 
				 	(selectedMonth == 2 && selectedDate > 28) ) {
			JOptionPane.showMessageDialog(null, "You have selected an invalid date. Please try again.", "Invalid date", 
					JOptionPane.ERROR_MESSAGE);
			
		//if a user selects a date in the future, or no information is available, pop up an error message.
		} else if (DataStorage.observations.size() == 0) {
				JOptionPane.showMessageDialog(null, "You have selected a date in the future, or the data for the date selected is not available. Please try again.", 
			    		"Invalid date", JOptionPane.ERROR_MESSAGE);
		} else {
		//if all other validation checks have been passed, open the second GUI frame.
		datadisplay.setVisible(true);
		}
	}
}