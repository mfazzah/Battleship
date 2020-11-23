package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Direction;
import model.ShipType;

@SuppressWarnings("serial")
public class ShipBoardJPanel extends JPanel {

	private JLabel shipsLabel;
	private JComboBox<Object> possibleShipsBox;
	private JLabel directionLabel;
	private JRadioButton verticalButton;
	private JRadioButton horizontalButton;
	private ButtonGroup group;

	public ShipType selectedShipType = ShipType.CARRIER;
	public Direction shipDirection = Direction.VERTICAL;
//allows user to choose which of five types of ships to place, and the orientation in which to place them on the board
	public ShipBoardJPanel() {
		this.shipsLabel = new JLabel("Available ships:");
		List<ShipType> listShipTypes = ShipType.getAllShipTypes();
		this.possibleShipsBox = new JComboBox<>(listShipTypes.toArray());
		this.directionLabel = new JLabel("Direction:");
		this.verticalButton = new JRadioButton("Vertical", true);
		this.horizontalButton = new JRadioButton("Horizontal");

		this.group = new ButtonGroup();
		this.group.add(horizontalButton);
		this.group.add(verticalButton);

		possibleShipsBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedShipType = (ShipType) possibleShipsBox.getSelectedItem();
			}
		});

		verticalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shipDirection = Direction.VERTICAL;
			}
		});

		horizontalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shipDirection = Direction.HORIZONTAL;
			}
		});

		this.setLayout(new GridBagLayout());
		//gui constraints for ship direction
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 0;
		this.add(shipsLabel, c);

		c.gridx = 0;
		c.gridy = 1;

		this.add(possibleShipsBox, c);

		c.gridx = 0;
		c.gridy = 2;
		this.add(this.directionLabel, c);

		c.gridy = 3;
		this.add(horizontalButton, c);
		c.gridx = 1;
		c.gridy = 3;
		this.add(verticalButton, c);

	}

	//returns selected ship type 
	public ShipType getSelectedShipType() {
		return this.selectedShipType;
	}

	
	//returns selected direction
	public Direction getSelectedDirection() {
		return this.shipDirection;
	}
}
