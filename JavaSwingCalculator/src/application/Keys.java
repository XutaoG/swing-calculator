package application;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;

final class Keys 
{
	// KEYS
	public static final String CLEAR ="ac";
	public static final String DELETE = "del";
	public static final String PERCENTAGE = "%";
	public static final String DIVIDE = "\u00F7";
	public static final String MULTIPLY = "\u00D7";
	public static final String MINUS = "\u2212";
	public static final String PLUS = "+";
	public static final String EQUAL = "=";
	public static final String DECIMAL_POINT = ".";
	public static final String NEGATIVE = "(-)";//"\u00B1";
	public static final String ERROR = "ERROR";

	// COLORS
	public static final Color DARK_GRAY = new Color(163, 163, 163);
	public static final Color LIGHT_GRAY = new Color(92, 92, 92);
	public static final Color BLUE = new Color(107, 111, 179);

	// DIMENSIONS
	public static final int MARGIN = 10;
	public static final int DISPLAY_PANEL_X = 420;
	public static final int DISPLAY_PANEL_Y = 240 - MARGIN;
	public static final int BUTTON_LENGTH = 105;

	// FONT
	public static final Font KEY_FONT = new Font("Arial", Font.PLAIN, 40);
	public static final Font DISPLAY_FONT = new Font("Geneva", Font.PLAIN, 70);

	// ICON
	public static final ImageIcon CALC_ICON = new ImageIcon("src/Images/calculator_50x50.png");

}
