package model.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import model.ModelException;
import model.Strategy.HitShipStrategy;

/*depending on which option picked for hit strategy by the user, the computer
 * will place ships using either use hit ships randomly or use an ordered strategy
 */
public class HitShipFactory {

	public HitShipStrategy getHitShipStrategy() {
		Properties properties = new Properties();
		InputStream input;
		try {
			input = new FileInputStream(new File("src/StrategyProperties.properties"));
			properties.load(input);
		} catch (IOException e) {
			throw new ModelException("Properties file not found (HitShipFactory)");
		}

		String className = (String) properties.get("hitShipStrategy");
//error handling
		try {
			Class<?> cl = Class.forName(className);
			return (HitShipStrategy) cl.getConstructor().newInstance();
		} catch (Exception e) {
			throw new ModelException("Strategy not found (PlaceShipFactory)");
		}

	}

}