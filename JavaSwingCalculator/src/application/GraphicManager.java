package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GraphicManager extends JFrame implements ActionListener
{
	LogicManager myLogicManager;
	JPanel displayPanel;
	JLabel display;
	JPanel buttonsPanel;
	JButton[] buttons;
	
	public GraphicManager()
	{
		myLogicManager = new LogicManager();

		this.setTitle("Calculator");
		this.setIconImage(Keys.CALC_ICON.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		initializeDisplayPanel();
		initializeButtonsPanel();

		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}

	private void initializeDisplayPanel()
	{
		displayPanel = new JPanel();
		displayPanel.setPreferredSize(new Dimension(Keys.DISPLAY_PANEL_X, Keys.DISPLAY_PANEL_Y - Keys.MARGIN));
		displayPanel.setBackground(Color.BLACK);
		displayPanel.setLayout(null);

		display = new JLabel();
		display.setBounds(Keys.MARGIN, Keys.MARGIN, Keys.DISPLAY_PANEL_X - 2 * Keys.MARGIN, Keys.DISPLAY_PANEL_Y - 2 * Keys.MARGIN);
		display.setOpaque(true);
		display.setBackground(Color.BLACK);
		display.setHorizontalAlignment(JLabel.RIGHT);
		display.setText("0");
		display.setFont(Keys.DISPLAY_FONT);
		display.setForeground(Color.WHITE);
		display.setVerticalAlignment(JLabel.BOTTOM);

		displayPanel.add(display);
		this.add(displayPanel, BorderLayout.NORTH);
	}

	private void initializeButtonsPanel()
	{
		String[][] functions = {
			{Keys.CLEAR, Keys.DELETE, Keys.PERCENTAGE, Keys.DIVIDE},
			{"7", "8", "9", Keys.MULTIPLY},
			{"4", "5", "6", Keys.MINUS},
			{"1", "2", "3", Keys.PLUS},
			{Keys.DECIMAL_POINT, "0", Keys.NEGATIVE, Keys.EQUAL}
		};

		buttonsPanel = new JPanel();
		buttonsPanel.setPreferredSize(new Dimension(Keys.BUTTON_LENGTH * 4, Keys.BUTTON_LENGTH * 5));
		buttonsPanel.setBackground(Color.BLACK);
		buttonsPanel.setLayout(null);

		// initialize buttons
		buttons = new JButton[20];

		int i, j, counter = 0;
		for (i = 0; i < 5; i++)
		{
			for (j = 0; j < 4; j++, counter++)
			{
				buttons[counter] = new JButton();
				// buttons[counter].setFocusPainted(false);
				// buttons[counter].setBorderPainted(false);
				buttons[counter].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

				buttons[counter].setBounds(j * Keys.BUTTON_LENGTH, i * Keys.BUTTON_LENGTH, Keys.BUTTON_LENGTH, Keys.BUTTON_LENGTH);
				buttons[counter].setFocusable(false);
				buttons[counter].setText(functions[i][j]);
				buttons[counter].setFont(Keys.KEY_FONT);

				buttons[counter].setForeground(Color.WHITE);
				buttons[counter].setBackground(Keys.LIGHT_GRAY);

				if (i == 0)
				{
					buttons[counter].setForeground(Color.BLACK);
					buttons[counter].setBackground(Keys.DARK_GRAY);
				}
				if (j == 3)
				{
					buttons[counter].setForeground(Color.WHITE);
					buttons[counter].setBackground(Keys.BLUE);
				}

				buttons[counter].addActionListener(this);
				buttonsPanel.add(buttons[counter]);
			}
		}
		this.add(buttonsPanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		int i;
		String returnValue;

		for (i = 0; i < 20; i++)
		{
			if (e.getSource() == buttons[i])
			{
				returnValue = myLogicManager.onButtonClicked(buttons[i].getText());
				display.setText(formatOutput(returnValue));
			}
		}
	}

	private String formatOutput(String str)
	{	
		if (str.length() >= 10)
		{
			return str.substring(0, 10);
		}
		return str;
	}
}
