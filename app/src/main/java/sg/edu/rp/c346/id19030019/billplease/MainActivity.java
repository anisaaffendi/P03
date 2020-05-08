package sg.edu.rp.c346.id19030019.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText etAmount;
    EditText etPax;
    EditText etDiscount;
    TextView tvTotal;
    TextView tvEach;
    ToggleButton tbSvs;
    ToggleButton tbgst;
    Button btnSplit;
    Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount=findViewById(R.id.editTextAmount);
        etPax=findViewById(R.id.editTextPax);
        etDiscount=findViewById(R.id.editTextDiscount);
        tvTotal=findViewById(R.id.textViewtotal);
        tvEach=findViewById(R.id.idtextViewEach);
        tbSvs=findViewById(R.id.tbSvs);
        tbgst=findViewById(R.id.tbgst);
        btnSplit=findViewById(R.id.buttonSplit);
        btnReset=findViewById(R.id.buttonReset);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etAmount.getText().toString().trim().length() == 0) {
                    return;
                }
                if (etPax.getText().toString().trim().length() == 0) {
                    return;
                }
                double amt = 0.0;
                if (!tbSvs.isChecked() && !tbgst.isChecked()) {
                    amt = Double.parseDouble(etAmount.getText().toString());
                } else if (!tbSvs.isChecked() && tbgst.isChecked()) {
                    amt = Double.parseDouble(etAmount.getText().toString()) * 1.07;
                } else if (tbSvs.isChecked() && !tbgst.isChecked()) {
                    amt = Double.parseDouble(etAmount.getText().toString()) * 1.1;
                } else {
                    amt = Double.parseDouble(etAmount.getText().toString()) * 1.17;
                    double discount = Double.parseDouble(etDiscount.getText().toString().trim());
                    amt = amt * (1 - discount / 100);
                }

                tvTotal.setText("Total Bill $ " + String.format("%.2f", amt));
                int noofPax = Integer.parseInt(etPax.getText().toString());
                tvEach.setText("Each Pays $ " + String.format("%.2f",amt/noofPax));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAmount.setText("");
                etPax.setText("");
                etDiscount.setText("");
            }
        });


    }
}
