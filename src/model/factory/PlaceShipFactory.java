package model.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import model.ModelException;
import model.Strategy.PlaceShipStrategy;

/*depending on which option picked for ship placement by the user, the computer
 * will place ships using either HardPlaceShipStrategy or PlaceStrategy
 */
public class PlaceShipFactory {

	public PlaceShipStrategy getPlaceShipStrategy() {
		Properties properties = new Properties();
		InputStream input;
		try {
			input = new FileInputStream(new File("src/StrategyProperties.properties"));
			properties.load(input);
		} catch (IOException e) {
			throw new ModelException("Properties file not found.");
		}

		String className = (String) properties.get("placeShipStrategy");

		//error handling
		try {
			Class<?> clazz = Class.forName(className);
			return (PlaceShipStrategy) clazz.getConstructor().newInstance();
		} catch (Exception e) {
			throw new ModelException("Strategy not found.");
		}

	}

}
