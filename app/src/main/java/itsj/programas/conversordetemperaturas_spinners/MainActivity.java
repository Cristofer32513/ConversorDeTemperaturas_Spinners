package itsj.programas.conversordetemperaturas_spinners;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

        spinnerEntrada=findViewById(R.id.spinner_entrada);

        spinnerSalida=findViewById(R.id.spinner_salida);

        cajaEntrada=findViewById(R.id.caja_entrada);
        
        cajaSalida=findViewById(R.id.caja_salida);

        ArrayAdapter adaptador=ArrayAdapter.createFromResource(this, R.array.spiner_entrada, android.R.layout.simple_spinner_item);
        spinnerEntrada.setAdapter(adaptador);

        ArrayAdapter adaptador2=ArrayAdapter.createFromResource(this, R.array.entrada_no_seleccionada, android.R.layout.simple_spinner_item);
        spinnerSalida.setAdapter(adaptador2);
    }
}
