package course.pllug.com.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private int[] numberButton = {R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9};
    private int[] operatorButton = {R.id.button_div, R.id.button_mul, R.id.button_min, R.id.button_plus};

    private TextView result;

    private boolean lastNumeric;
    private boolean stateError;
    private boolean lastDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        this.result = findViewById (R.id.txt);

        setNumberOnClickListener();
        // Find and set OnClickListener to operator buttons, equal button and decimal point button
        setOperatorOnClickListener();
    }

    private void setNumberOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (stateError){
                    result.setText (button.getText ());
                    stateError = false;
                } else {
                    result.append (button.getText ());
                }
                lastNumeric = true;
            }
        };

        for (int id: numberButton) {
            findViewById (id).setOnClickListener (listener);
        }
    }

    private void setOperatorOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(lastNumeric && !stateError) {
                    Button button = (Button) v;
                    result.append (button.getText ());
                    lastNumeric = false;
                    lastDot = false;
                }
            }
        };

        for (int id: operatorButton) {
            findViewById (id).setOnClickListener (listener);
        }

        // Decimal point
        findViewById(R.id.button_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric && !stateError && !lastDot) {
                    result.append(".");
                    lastNumeric = false;
                    lastDot = true;
                }
            }
        });

        findViewById (R.id.button_clean).setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                result.setText ("");
                lastNumeric = false;
                stateError = false;
                lastDot = false;
                }
        });

        findViewById (R.id.button_result).setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                onEqual();
            }
        });
    }

    private void onEqual() {
        // If the current state is error, nothing to do.
        // If the last input is a number only, solution can be found.
        if (lastNumeric && !stateError) {
            // Read the expression
            String txt = result.getText().toString();
            // Create an Expression (A class from exp4j library)
            Expression expression = new ExpressionBuilder(txt).build();
            try {
                // Calculate the result and display
                double res = expression.evaluate();
                result.setText(Double.toString(res));
                lastDot = true; // Result contains a dot
            } catch (ArithmeticException ex) {
                // Display an error message
                result.setText("Error");
                stateError = true;
                lastNumeric = false;
            }
        }
    }
}