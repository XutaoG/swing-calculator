package application;

public class LogicManager 
{
	private int maxDigits = 9;
	private String currentText;
	private double currentResult;
	private double currentValue;
	private String operator;
	private boolean hasDecimal;
	private boolean toBeReset;

	public LogicManager()
	{
		currentText = "0";
		currentResult = 0;
		currentValue = 0;
		operator = null;
		hasDecimal = false;
		toBeReset = false;
	}

	public String onButtonClicked(String val)
	{
		if (isNumeric(val.charAt(0)))
		{
			onNumberButton(val);
		}
		else if (val.compareTo(Keys.CLEAR) == 0)
		{
			onACButton();
		}
		else if (val.compareTo(Keys.DELETE) == 0)
		{
			onDelButton();
		}
		else if (isOperator(val))
		{
			onOperatorButton(val);
		}
		else if (val.compareTo(Keys.EQUAL) == 0)
		{
			onEqualButton();
		}
		else if (val.compareTo(Keys.NEGATIVE) == 0)
		{
			onChangeSignButton();
		}
		else if (val.compareTo(Keys.PERCENTAGE) == 0)
		{
			onPercentageButton();
		}
		updateFields();
		// Debugging
		// System.out.printf("Current result: %10f, Current value: %10f\n", currentResult, currentValue);
		return currentText;
	}

	private void updateFields()
	{
		if (currentText.compareTo(Keys.ERROR) != 0)
		{
			// System.out.println(currentText);
			currentValue = Double.parseDouble(currentText);
		}
	}

	private void onOperatorButton(String val)
	{
		onEqualButton();

		operator = val;
		hasDecimal = false;
		toBeReset = true;
		currentResult = Double.parseDouble(currentText);
	}

	private void onNumberButton(String val)
	{
		// toBeReset is true if an operator has been pressed and the next number resets the display
		if (toBeReset)
		{
			toBeReset = false;
			currentText = val;
			return;
		}

		// If currentText has reached the maximum length
		if (currentText.length() >= maxDigits)
		{
			return;
		}

		// If currentText displays an error
		if (currentText.compareTo(Keys.ERROR) == 0)
		{
			currentText = val;
			return;
		}

		if (val.compareTo(Keys.DECIMAL_POINT) == 0)
		{
			// add new decimal point if currently has no decimal point
			if (hasDecimal == false)
			{	
				hasDecimal = true;
				currentText += val;
				return;
			}
			// If currentText has a decimal point already, do not add new decimal point
			else 
			{
				return;
			}
		}

		// If currentText is 0, replace currentText with new value
		if (currentText.compareTo("0") == 0)
		{
			currentText = val;
			return;
		}
		else if (currentText.compareTo("-0") == 0)
		{
			currentText = "-" + val;
		}

		// Concatenate currentText with new val inserted after
		currentText += val;
	}

	private void onEqualButton()
	{
		// No operator button has been pressed
		if (operator == null)
		{
			return;
		}
		
		if (operator.compareTo(Keys.PLUS) == 0)
		{
			currentResult += currentValue;
		}
		else if (operator.compareTo(Keys.MINUS) == 0)
		{
			currentResult -= currentValue;
		}
		else if (operator.compareTo(Keys.MULTIPLY) == 0)
		{
			currentResult *= currentValue;
		}
		else if (operator.compareTo(Keys.DIVIDE) == 0)
		{
			// If division by 0
			if (currentValue == 0)
			{
				onACButton();
				currentText = Keys.ERROR;
				return;
			}
			else
			{
				currentResult /= currentValue;
			}
		}
		operator = null;

		// If currentResult is an integer
		if (currentResult % 1 == 0) 
		{
			currentText = String.valueOf((int) currentResult);
		}
		// currentResult is a decimal number
		else
		{
			currentText = String.valueOf(currentResult);
		}
	}

	private void onChangeSignButton()
	{
		// currentText is positive, so add a negative sign
		if (currentText.charAt(0) != '-')
		{
			currentText = "-" + currentText;
		}
		// currentText is negative, so make is positive
		else
		{
			currentText = currentText.substring(1, currentText.length());
		}
	}

	private void onPercentageButton()
	{
		onOperatorButton(Keys.DIVIDE);
		toBeReset = false;
		currentValue = 100;
		onEqualButton();

		// currentResult is a decimal integer
		if (currentResult % 1 == 0) 
		{
			hasDecimal = false;
		}
		// currentResult is a decimal number
		else
		{
			hasDecimal = true;
		}
	}

	private String onACButton()
	{
		currentText = "0";
		currentResult = 0;
		currentValue = 0;
		operator = null;
		hasDecimal = false;
		toBeReset = false;
		return "0";
	}

	private void onDelButton()
	{
		// currentText only contain a zero, nothing should be deleted
		if (currentText.compareTo("0") == 0) 
		{
			currentText = "0";
		}
		else if (currentText.compareTo("-0") == 0)
		{
			currentText = "0";
		}
		// If only a single number is present, reset display to 0
		else if (currentText.length() == 1) 
		{
			currentText = "0";
		}
		// If deleting only leaves a single negative sign
		else if (currentText.charAt(0) == '-' && currentText.length() == 2)
		{
			currentText = "-0";
		}
		// If decimal point is removed, allow new decimal point to be re-inserted
		else if (currentText.charAt(currentText.length() - 1) == '.') 
		{
			hasDecimal = false;
			currentText = currentText.substring(0, currentText.length() - 1);
		}
		else
		{
			currentText = currentText.substring(0, currentText.length() - 1);
		}
	}

	private boolean isNumeric(char c) 
	{ 
		if (Character.isDigit(c) || c == '.')
		{
			return true;
		}
		return false;
	}

	private boolean isOperator(String str)
	{
		if (str.compareTo(Keys.PLUS) == 0 || str.compareTo(Keys.MINUS) == 0)
		{
			return true;
		}
		else if (str.compareTo(Keys.MULTIPLY) == 0 || str.compareTo(Keys.DIVIDE) == 0)
		{
			return true;
		}
		return false;
	}
}
