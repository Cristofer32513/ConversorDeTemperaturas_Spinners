package itsj.programas.conversordetemperaturas_spinners;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnKeyListener {

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
        spinnerEntrada.setOnItemSelectedListener(this);

        spinnerSalida=findViewById(R.id.spinner_salida);
        spinnerSalida.setOnItemSelectedListener(this);

        cajaEntrada=findViewById(R.id.caja_entrada);
        cajaEntrada.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction()==KeyEvent.ACTION_DOWN) {
                    if(keyCode==KeyEvent.KEYCODE_PERIOD) {
                        if(cajaEntrada.getText().toString().contains(".")) {
                            Toast.makeText(getApplicationContext(), "Solo puedes ingresar valores reales.", Toast.LENGTH_SHORT).show();
                            cajaEntrada.setText(respaldo);
                            cajaEntrada.setSelection(cajaEntrada.getText().toString().length());
                            return true;
                        }
                        else
                            return false;
                    }
                    else
                        return false;
                }
                else if(event.getAction()==KeyEvent.ACTION_UP) {
                    if(keyCode!=KeyEvent.KEYCODE_0 && keyCode!=KeyEvent.KEYCODE_1 && keyCode!=KeyEvent.KEYCODE_2 && keyCode!=KeyEvent.KEYCODE_3 &&
                            keyCode!=KeyEvent.KEYCODE_4 && keyCode!=KeyEvent.KEYCODE_5 && keyCode!=KeyEvent.KEYCODE_6 && keyCode!=KeyEvent.KEYCODE_7 &&
                            keyCode!=KeyEvent.KEYCODE_8 && keyCode!=KeyEvent.KEYCODE_9 && keyCode!=KeyEvent.KEYCODE_DEL && keyCode!=KeyEvent.KEYCODE_PERIOD) {
                        Toast.makeText(getApplicationContext(), "Solo puedes ingresar numeros.", Toast.LENGTH_SHORT).show();
                        if(cajaEntrada.getText().toString().length()==0)
                            cajaEntrada.setText("");
                        else {
                            cajaEntrada.setText(respaldo);
                            cajaEntrada.setSelection(cajaEntrada.getText().toString().length());
                        }
                        return true;
                    }
                    else {
                        respaldo=cajaEntrada.getText().toString();
                        if(!cajaEntrada.getText().toString().equals("")) {
                            if(cajaEntrada.getText().toString().equals(".")) {
                                cajaEntrada.setText("0.");
                                cajaEntrada.setSelection(cajaEntrada.getText().toString().length());
                            }
                            tempEntrada=Double.parseDouble(cajaEntrada.getText().toString());
                            tempSalida=0;
                            if(spinnerEntrada.getSelectedItem()!=null && spinnerSalida.getSelectedItem()!=null &&
                                    spinnerEntrada.getSelectedItemPosition()!=0 &&
                                    spinnerSalida.getSelectedItemPosition()!=0)
                                mostrarResultado();
                            else
                                cajaSalida.setText("");
                        }
                        else
                            cajaSalida.setText("");
                        return false;
                    }
                }
                else
                    return true;
            }
        });

        cajaSalida=findViewById(R.id.caja_salida);

        ArrayAdapter adaptador=ArrayAdapter.createFromResource(this, R.array.spiner_entrada, android.R.layout.simple_spinner_item);
        spinnerEntrada.setAdapter(adaptador);

        ArrayAdapter adaptador2=ArrayAdapter.createFromResource(this, R.array.entrada_no_seleccionada, android.R.layout.simple_spinner_item);
        spinnerSalida.setAdapter(adaptador2);
    }

    public void mostrarResultado(){
        if(spinnerEntrada.getSelectedItemPosition()==1 && spinnerSalida.getSelectedItemPosition()==1)
            tempSalida=(tempEntrada*1.8)+32;
        else if(spinnerEntrada.getSelectedItemPosition()==1 && spinnerSalida.getSelectedItemPosition()==2)
            tempSalida=tempEntrada+273.15;
        else if(spinnerEntrada.getSelectedItemPosition()==1 && spinnerSalida.getSelectedItemPosition()==3)
            tempSalida=(tempEntrada*1.8)+491.67;


        else if(spinnerEntrada.getSelectedItemPosition()==2 && spinnerSalida.getSelectedItemPosition()==1)
            tempSalida=(tempEntrada-32)/1.8;
        else if(spinnerEntrada.getSelectedItemPosition()==2 && spinnerSalida.getSelectedItemPosition()==2)
            tempSalida=(tempEntrada+459.67)/1.8;
        else if(spinnerEntrada.getSelectedItemPosition()==2 && spinnerSalida.getSelectedItemPosition()==3)
            tempSalida=tempEntrada+459.67;


        else if(spinnerEntrada.getSelectedItemPosition()==3 && spinnerSalida.getSelectedItemPosition()==1)
            tempSalida=tempEntrada-273.15;
        else if(spinnerEntrada.getSelectedItemPosition()==3 && spinnerSalida.getSelectedItemPosition()==2)
            tempSalida=((tempEntrada-273.15)*1.8)+32;
        else if(spinnerEntrada.getSelectedItemPosition()==3 && spinnerSalida.getSelectedItemPosition()==3)
            tempSalida=tempEntrada*1.8;


        else if(spinnerEntrada.getSelectedItemPosition()==4 && spinnerSalida.getSelectedItemPosition()==1)
            tempSalida=(tempEntrada-491.67)/1.8;
        else if(spinnerEntrada.getSelectedItemPosition()==4 && spinnerSalida.getSelectedItemPosition()==2)
            tempSalida=tempEntrada-459.67;
        else if(spinnerEntrada.getSelectedItemPosition()==4 && spinnerSalida.getSelectedItemPosition()==3)
            tempSalida=tempEntrada/1.8;

        cajaSalida.setText(redondear.format(tempSalida));
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.spinner_entrada) {
            ArrayAdapter adaptador=ArrayAdapter.createFromResource(this, R.array.entrada_no_seleccionada, android.R.layout.simple_spinner_item);
            if(spinnerEntrada.getSelectedItemPosition()==0) {
                adaptador = ArrayAdapter.createFromResource(this, R.array.entrada_no_seleccionada, android.R.layout.simple_spinner_item);
                spinnerSalida.setEnabled(false);
            }
            else if(spinnerEntrada.getSelectedItemPosition()==1) {
                adaptador = ArrayAdapter.createFromResource(this, R.array.entrada_c_seleccionada, android.R.layout.simple_spinner_item);
                spinnerSalida.setEnabled(true);
            }
            else if(spinnerEntrada.getSelectedItemPosition()==2) {
                adaptador = ArrayAdapter.createFromResource(this, R.array.entrada_f_seleccionada, android.R.layout.simple_spinner_item);
                spinnerSalida.setEnabled(true);
            }
            else if(spinnerEntrada.getSelectedItemPosition()==3) {
                adaptador = ArrayAdapter.createFromResource(this, R.array.entrada_k_seleccionada, android.R.layout.simple_spinner_item);
                spinnerSalida.setEnabled(true);
            }
            else if(spinnerEntrada.getSelectedItemPosition()==4) {
                adaptador = ArrayAdapter.createFromResource(this, R.array.entrada_r_seleccionada, android.R.layout.simple_spinner_item);
                spinnerSalida.setEnabled(true);
            }

            spinnerSalida.setAdapter(adaptador);
        }
        else if(parent.getId()==R.id.spinner_salida){
            if(!cajaEntrada.getText().toString().equals("")){
                if(cajaEntrada.getText().toString().equals("."))
                    cajaEntrada.setText("0.");
                tempEntrada=Double.parseDouble(cajaEntrada.getText().toString());
                tempSalida=0;

                if(spinnerSalida.getSelectedItem()==null)
                    cajaSalida.setText("");
                else{
                    if(spinnerSalida.getSelectedItemPosition()==0)
                        cajaSalida.setText("");
                    else
                        mostrarResultado();
                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
