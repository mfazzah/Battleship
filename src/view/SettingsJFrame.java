package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Strategy.HitStrategy;
import model.Strategy.PlaceStrategy;

/* GUI for the settings window
 * */
@SuppressWarnings("serial")
public class SettingsJFrame extends JFrame {

	private JButton confirmButton;

	private JComboBox<HitStrategy> hitComboBox;
	private JComboBox<PlaceStrategy> placeComboBox;
	private JComboBox<String> shipsVisibleBox;

	private JLabel hitLabel;
	private JLabel placeLabel;
	private JLabel shipsVisibleLabel;

	private boolean isShipsVisible = false;

	public final static int HEIGHT_FRAME = 500;
	public final static int WIDTH_FRAME = 500;

	public SettingsJFrame() {
		super();
		this.launch();
	}

	public void launch() {
		this.DefaultValuesToProperties();
		this.setSize(WIDTH_FRAME, HEIGHT_FRAME);
		this.setResizable(false);
		this.setLayout(null);
		this.setTitle("Settings");

		this.completeLabelHitStrategy();
		this.completeBoxHitStrategy();
		this.completeConfirmButton();

		this.completeBoxPlaceStrategy();
		this.completeLabelPlaceStrategy();

		this.completeShipsVisibleLabel();
		this.completeShipsVisibleBox();

		this.addActionListenerToHitComboBox();
		this.addActionListenerToPlaceCombobox();
		this.addActionListenerToVisibleheidComboBox();
		this.addActionListenerTopConfirmButton();
	}

	private void completeConfirmButton() {
		this.confirmButton = new JButton("Confirm");
		this.confirmButton.setLocation(25, 400);
		this.confirmButton.setSize(new Dimension(450, 40));
		this.add(confirmButton);
	}

	private void completeBoxHitStrategy() {
		this.hitComboBox = new JComboBox<>(HitStrategy.values());
		this.hitComboBox.setLocation(25, 120);
		this.hitComboBox.setSize(new Dimension(450, 40));
		this.add(hitComboBox);
	}

	private void completeLabelHitStrategy() {
		this.hitLabel = new JLabel("Choose a shooting strategy:");
		this.hitLabel.setLocation(25, 75);
		this.hitLabel.setSize(new Dimension(450, 40));
		this.add(hitLabel);
	}

	private void addActionListenerToHitComboBox() {
		this.hitComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeHitToProperties(((HitStrategy) hitComboBox.getSelectedItem()).getFullClassName());
			}
		});
	}

	private void completeBoxPlaceStrategy() {
		this.placeComboBox = new JComboBox<>(PlaceStrategy.values());
		this.placeComboBox.setLocation(25, 200);
		this.placeComboBox.setSize(new Dimension(450, 40));
		this.add(placeComboBox);
	}

	private void completeLabelPlaceStrategy() {
		this.placeLabel = new JLabel("Choose a ship placement strategy:");
		this.placeLabel.setLocation(25, 160);
		this.placeLabel.setSize(new Dimension(450, 40));
		this.add(placeLabel);
	}

	private void addActionListenerToPlaceCombobox() {
		this.placeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writePlaceToProperties(((PlaceStrategy) placeComboBox.getSelectedItem()).getFullClassName());
			}
		});
	}

	private void addActionListenerTopConfirmButton() {
		this.confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
//settings set up for whether computer's ships should be visible or hidden
	private void completeShipsVisibleLabel() {
		this.shipsVisibleLabel = new JLabel("Do you want the computer's ships to be visible?");
		this.shipsVisibleLabel.setLocation(25, 250);
		this.shipsVisibleLabel.setSize(new Dimension(450, 40));
		this.add(shipsVisibleLabel);
	}

	private void completeShipsVisibleBox() {
		String[] possibile = { "NO", "YES" };
		this.shipsVisibleBox = new JComboBox<>(possibile);
		this.shipsVisibleBox.setLocation(25, 290);
		this.shipsVisibleBox.setSize(new Dimension(450, 40));
		this.add(shipsVisibleBox);
	}

	private void addActionListenerToVisibleheidComboBox() {
		this.shipsVisibleBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = (String) shipsVisibleBox.getSelectedItem();
				if (selected.equals("YES")) {
					isShipsVisible = true;
				} else {
					isShipsVisible = false;
				}
			}
		});
	}

	public boolean shipsVisible() {
		return this.isShipsVisible;
	}

	private void DefaultValuesToProperties() {
		this.writeHitToProperties(HitStrategy.RANDOM.getFullClassName());
		this.writePlaceToProperties(PlaceStrategy.RANDOM.getFullClassName());
	}
//takes choices from settings screen for which computer hit strategy to use and writes it to properties file to be used by that instance
	public void writeHitToProperties(String hitStrategy) {
		FileInputStream in = null;
		try {
			in = new FileInputStream("src/StrategyProperties.properties");
		} catch (FileNotFoundException e) {
			System.out.println("Properties not found! (Settings)");
		}
		Properties props = new Properties();
		//if properties can't be loaded send error message
		try {
			props.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Couldn't load properties (Settings)");
		}

		FileOutputStream out = null;
		//if properties can't be found send error message
		try {
			out = new FileOutputStream("src/StrategyProperties.properties");
		} catch (FileNotFoundException e) {
			System.out.println("Properties not found! (Settings)");
		}
		props.setProperty("hitShipStrategy", hitStrategy);
		//if properties can't be saved, send error message
		try {
			props.store(out, null);
			out.close();
		} catch (IOException e) {
			System.out.println("Could not save properties (Settings)");
		}

	}
	//takes choices from settings screen for which computer placement strategy to use and writes it to properties file to be used by that instance

	public void writePlaceToProperties(String placeShipStrategy) {
		FileInputStream in = null;
		try {
			//if properties can't be found for input send error message
			in = new FileInputStream("src/StrategyProperties.properties");
		} catch (FileNotFoundException e) {
			System.out.println("Properties not found! (Settings)");
		}
		Properties props = new Properties();
		try {
			//if properties can't be loaded to be written to send error message
			props.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Could not load properties (Settings)");
		}

		FileOutputStream out = null;
		try {
			//if file can't be found to get output send error message
			out = new FileOutputStream("src/StrategyProperties.properties");
		} catch (FileNotFoundException e) {
			System.out.println("Properties not found! (Settings)");
		}
		props.setProperty("placeShipStrategy", placeShipStrategy);
		try {
			props.store(out, null);
			out.close();
		} catch (IOException e) {
			System.out.println("Could not save properties (Settings)");
		}
	}

}
