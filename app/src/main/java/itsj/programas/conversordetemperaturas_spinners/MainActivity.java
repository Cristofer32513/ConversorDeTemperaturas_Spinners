package itsj.programas.conversordetemperaturas_spinners;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerEntrada, spinnerSalida;
    EditText cajaEntrada, cajaSalida;
    DecimalFormat redondear =new DecimalFormat("###,###,##0.####");
    String respaldo="";
    double tempEntrada, tempSalida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
