package szymon.wolyniec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import szymon.wolyniec.databinding.CalculatorActivityBinding;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String DISPLAY_KEY = "display";
    public static final String ACCUMULATOR_KEY = "accumulator";
    public static final String OPERATION_KEY = "operation";
    private CalculatorActivityBinding binding;
    private String display = "0";
    private double accumululator = 0.0;
    private Operation currentOperation = Operation.NONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CalculatorActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.button1.setOnClickListener(this);
        binding.button2.setOnClickListener(this);
        binding.button3.setOnClickListener(this);
        binding.button4.setOnClickListener(this);
        binding.button5.setOnClickListener(this);
        binding.button6.setOnClickListener(this);
        binding.button7.setOnClickListener(this);
        binding.button8.setOnClickListener(this);
        binding.button9.setOnClickListener(this);
        binding.button10.setOnClickListener(this);
        binding.button11.setOnClickListener(this);
        binding.button12.setOnClickListener(this);
        binding.button13.setOnClickListener(this);
        binding.button14.setOnClickListener(this);
        binding.button15.setOnClickListener(this);
        binding.button16.setOnClickListener(this);

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            binding.button17.setOnClickListener(this);
            binding.button18.setOnClickListener(this);
            binding.button19.setOnClickListener(this);
            binding.button20.setOnClickListener(this);
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(DISPLAY_KEY, display);
        outState.putDouble(ACCUMULATOR_KEY, accumululator);
        outState.putString(OPERATION_KEY, currentOperation.name());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        display = savedInstanceState.getString(DISPLAY_KEY, "0");
        accumululator = savedInstanceState.getDouble(ACCUMULATOR_KEY);
        currentOperation = (Operation.valueOf(savedInstanceState.getString(OPERATION_KEY)));

        updateDisplay();
    }

    private void updateDisplay()
    {
        binding.display.setText(display);
    }
    @Override
    public void onClick(View v) {
        androidx.appcompat.widget.AppCompatButton b = (androidx.appcompat.widget.AppCompatButton) v;
        String key = b.getText().toString();

        //Log.d("SWTEST", key);

        switch (key) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                if (display.equals("0")) {
                    display = "";
                }
                display += key;
                break;
            case ".":
                if(!display.contains("."))
                    display += key;
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                calculateOperation(key);
                break;
            case "SQRT(X)":
                calculateSquareRoot();
                break;
            case "X^2":
                calculateSquare();
                break;
            case "=":
                calculateResult();
                break;
            case "CE":
                clearOne();
                break;
            case "C":
                clearAll();
                break;
        }

        updateDisplay();
    }

    private void calculateSquare() {
        double displayValue = Double.parseDouble(display);
        displayResult(displayValue * displayValue);
    }

    private void calculateSquareRoot() {
        double displayValue = Double.parseDouble(display);
        displayResult(Math.sqrt(displayValue));
    }

    private void clearAll() {
        display = "0";
        accumululator = 0.0;
        currentOperation = Operation.NONE;
    }

    private void clearOne() {
        if (display.length() > 1)
            display = display.substring(0, display.length() - 1);
        else
            display = "0";
    }

    private void calculateResult() {
        double displayValue = Double.parseDouble(display);

        switch (currentOperation) {
            case ADD:
                displayResult(accumululator + displayValue);
                break;
            case SUBSTRACT:
                displayResult(accumululator - displayValue);
                break;
            case MULTIPLY:
                displayResult(accumululator * displayValue);
                break;
            case DIVIDE:
                displayResult(accumululator / displayValue);
                break;
        }

        accumululator = 0.0;
        currentOperation = Operation.NONE;
    }

    private void displayResult(double result) {

        if (result == (long) result) {
            display = String.format("%d", (long) result);
        } else {
            display = String.format("%s", result);
        }

    }

    private void calculateOperation(String key) {
        currentOperation = Operation.operationFromKey(key);
        accumululator = Double.parseDouble(display);
        display = "0";
    }


}