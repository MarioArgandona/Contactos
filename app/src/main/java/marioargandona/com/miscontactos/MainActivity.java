package marioargandona.com.miscontactos;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contacto> listaContactos;
    private TextInputEditText txtNombre;
    private TextInputEditText txtTelefono;
    private TextInputEditText txtEmail;
    private TextInputEditText txtDescripcion;
    private DatePicker datePicker;
    private String nombre = "";
    private String telefono = "";
    private String email = "";
    private String descripcion = "";
    private int mYear = 0;
    private int mMonth = 0;
    private int mDay = 0;
    private String dia = "";
    private String mes = "";
    private String anio = "2";
    static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        cargaWidgets();

        txtNombre.setText("");
        txtTelefono.setText("");
        txtDescripcion.setText("");
        txtEmail.setText("");

        Bundle parametros = getIntent().getExtras();

        if(parametros != null)
        {
            nombre = parametros.getString(getResources().getString(R.string.variable_nombre));
            telefono = parametros.getString(getResources().getString(R.string.variable_telefono));
            email = parametros.getString(getResources().getString(R.string.variable_email));
            descripcion = parametros.getString(getResources().getString(R.string.variable_descripcion));
            dia = parametros.getString(getResources().getString(R.string.variable_dia));
            mes = parametros.getString(getResources().getString(R.string.variable_mes));
            anio = parametros.getString(getResources().getString(R.string.variable_anio));

            txtNombre.setText(nombre);
            txtTelefono.setText(telefono);
            txtEmail.setText(email);
            txtDescripcion.setText(descripcion);

            int year    = Integer.valueOf(anio);
            int month   = Integer.valueOf(mes);
            int day     = Integer.valueOf(dia);

            datePicker.updateDate(year,month,day);
        }

        //listaContactos = new ArrayList<Contacto>();
        //cargaContactos();
    }



    private void cargaWidgets()
    {
        txtNombre = (TextInputEditText)findViewById(R.id.txtNombreContacto);
        txtTelefono = (TextInputEditText)findViewById(R.id.txtTelefonoContacto);
        txtEmail = (TextInputEditText)findViewById(R.id.txtEmailContacto);
        txtDescripcion = (TextInputEditText)findViewById(R.id.txtDescripcionContacto);

        //MOSTRAMOS DATEPICKER CON LA FECHA ACTUAL
        //mostrarDatePicker();
        datePicker = (DatePicker)findViewById(R.id.datePicker);
    }



    private void mostrarDatePicker()
    {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        showDialog(DATE_DIALOG_ID);
    }




    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }



    private DatePickerDialog.OnDateSetListener mDateSetListener =
    new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year,
                              int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
        }
    };


    public void enviaDatos(View v)
    {
        mDay = datePicker.getDayOfMonth();
        mMonth = datePicker.getMonth();
        mYear = datePicker.getYear();

        Intent intent = new Intent(this , DetalleContacto.class);
        intent.putExtra(getResources().getString(R.string.variable_nombre) , txtNombre.getText().toString());
        intent.putExtra(getResources().getString(R.string.variable_telefono) , txtTelefono.getText().toString());
        intent.putExtra(getResources().getString(R.string.variable_email), txtEmail.getText().toString());
        intent.putExtra(getResources().getString(R.string.variable_descripcion) , txtDescripcion.getText().toString());
        intent.putExtra(getResources().getString(R.string.variable_dia) , "" + mDay);
        intent.putExtra(getResources().getString(R.string.variable_mes) , "" + mMonth);
        intent.putExtra(getResources().getString(R.string.variable_anio) , "" + mYear);
        startActivity(intent);

    }




    //private void cargaContactos()
    //{
    //    if(listaContactos == null)
    //    {
    //        listaContactos = new ArrayList<Contacto>();
    //    }

    //    listaContactos.add(new Contacto("Mario Argandoña" , "5524930478" , "mario@gbts.com.mx"));
    //    listaContactos.add(new Contacto("Adrián Argandoña" , "5572928373" , "adrian@gbts.com.mx"));
    //    listaContactos.add(new Contacto("Monze Reyes" , "5572834678" , "monze@gbts.com.mx"));
    //    listaContactos.add(new Contacto("Rebeca González" , "5582834742" , "rebeca@gbts.com.mx"));
    //    listaContactos.add(new Contacto("Diego Castañeda" , "5591283729" , "diego@gbts.com.mx"));

    //    ArrayList<String> nombresContacto = new ArrayList<>();
    //    for(Contacto contacto : listaContactos)
    //    {
    //        nombresContacto.add(contacto.getNombre());
    //    }
    //
    //    ListView lst = (ListView)findViewById(R.id.lstContactos);
    //    lst.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombresContacto));
    //    //Generamos listener en el listview
    //    lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    //        @Override
    //        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    //            Intent intent = new Intent(MainActivity.this,DetalleContacto.class);
    //            intent.putExtra(getResources().getString(R.string.variable_nombre),listaContactos.get(position).getNombre());
    //            intent.putExtra(getResources().getString(R.string.variable_telefono),listaContactos.get(position).getTelefono());
    //            intent.putExtra(getResources().getString(R.string.variable_email),listaContactos.get(position).getEmail());
    //            startActivity(intent);
    //            finish();
    //        }
    //    });
    //}

}
