package uk.ac.sheffield.aca15npm;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DataDisplayFrame extends JFrame implements ActionListener {
	private Container contentDisplay;
	private JPanel buttonsPanel;
	private JButton temperature;
	private JButton pressure;
	private JButton wind;
	private JButton precipitation;
	private JButton quit;
	private GraphPanel gpanel;
	private String buttonPressed;
	
	public DataDisplayFrame() {
		//set GUI properties
		setTitle("Airport Weather Data Viewer");
		setSize(1200,600);
		setLocation(new Point(150,80));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		//Container for the frame
		contentDisplay = getContentPane();
		contentDisplay.setLayout(new BorderLayout());
		
		//Graph Panel for graphs to be plotted on
		gpanel = new GraphPanel(buttonPressed);

		//buttons panel for the buttons to be added to
		buttonsPanel = new JPanel();
		
		//buttons for the buttons panel
		temperature = new JButton("Temperature");
		pressure = new JButton("Pressure");
		wind = new JButton("Wind Data");
		precipitation = new JButton("Total Precipitation");		
		quit = new JButton("Exit Program");
		//set buttons panel layout and add the buttons
		buttonsPanel.setLayout(new GridLayout(0,1));
		buttonsPanel.add(temperature);
		buttonsPanel.add(pressure);
		buttonsPanel.add(wind);
		buttonsPanel.add(precipitation);
		buttonsPanel.add(quit);
		
		//add action listeners
		temperature.addActionListener(this);
		pressure.addActionListener(this);
		wind.addActionListener(this);
		precipitation.addActionListener(this);
		quit.addActionListener(this);
		
		//add components to the container
		contentDisplay.add(buttonsPanel, BorderLayout.EAST);
		contentDisplay.add(gpanel, BorderLayout.CENTER);
	}
	
	public void actionPerformed(ActionEvent e) {
		String buttonPressed = e.getActionCommand();
		gpanel = new GraphPanel(buttonPressed);
		//add updated gpanel to the container
		contentDisplay.add(gpanel, BorderLayout.CENTER);
		validate();
		gpanel.setVisible(true);
	}
}