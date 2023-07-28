# CurrencyConverter
Simple currency converter created to gain experience in Android development

# Usage
1. When the app starts, select the currency you want to convert from (USD, Euro, or Pound) in the main activity.
2. Enter a value in the selected currency's EditText field.
3. The equivalent values in the other two currencies (Euro and Pound) will be displayed dynamically as you enter the value.
4. To switch to another currency, return to the main activity and choose a different currency.

# How it works
-  The app uses static conversion values for converting YEN, CAD and EUR

- It uses a TextWatcher event listener to listen for any user change when entering USD values, dynamically updating the conversion value

- Similarly, it uses another TextWatcher for the foreign currency EditText to calculate the USD value based on the entered foreign currency value.
