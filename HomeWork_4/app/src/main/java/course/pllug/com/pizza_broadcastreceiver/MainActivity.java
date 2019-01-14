package course.pllug.com.pizza_broadcastreceiver;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buy, call;
    private TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        initView();
        initListener();
    }

    private void initView(){
        status = findViewById (R.id.status_product);
        buy = findViewById (R.id.buy_product);
        call = findViewById (R.id.call);
    }
    private void initListener() {
        buy.setOnClickListener (this);
        call.setOnClickListener (this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buy_product:
                status.setText ("замовлено");
                status.setTextColor (Color.GREEN);
                break;
            case R.id.call:
                makeCall();
                break;
        }

    }

    private void makeCall() {
        String number = "+380987654321";
        Intent intent = new Intent (Intent.ACTION_DIAL, Uri.fromParts ("tel", number, null));
        startActivity (intent);
    }
}