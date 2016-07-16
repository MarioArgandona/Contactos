package marioargandona.com.miscontactos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class DetalleContacto extends AppCompatActivity {

    private TextView tvNombreCardView;
    private TextView tvTelefonoCardView;
    private TextView tvEmailCardView;
    private TextView tvDescripcionCardView;
    private TextView tvFechaCardView;
    private String nombre = "";
    private String telefono = "";
    private String email = "";
    private String descripcion = "";
    private String dia = "";
    private String mes = "";
    private String anio = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        validamosWidgets();

        Bundle parametros = getIntent().getExtras();

        if (parametros != null) {
            nombre = parametros.getString(getResources().getString(R.string.variable_nombre));
            telefono = parametros.getString(getResources().getString(R.string.variable_telefono));
            email = parametros.getString(getResources().getString(R.string.variable_email));
            descripcion = parametros.getString(getResources().getString(R.string.variable_descripcion));
            dia = parametros.getString(getResources().getString(R.string.variable_dia));
            mes = parametros.getString(getResources().getString(R.string.variable_mes));
            anio = parametros.getString(getResources().getString(R.string.variable_anio));

            tvNombreCardView.setText(nombre);
            tvTelefonoCardView.setText(getResources().getString(R.string.cadena_telefono) + " " + telefono);
            tvEmailCardView.setText(getResources().getString(R.string.cadena_email) + " " + email);
            tvDescripcionCardView.setText(getResources().getString(R.string.cadena_descripcion) + " " + descripcion);
            tvFechaCardView.setText(getResources().getString(R.string.cadena_fecha_naciemiento) + " " + convertirFecha(dia,mes,anio));
        }

    }


    private String convertirFecha(String dia , String mes , String anio)
    {
        String fecha = "";

        Integer mesEscrito = (Integer.valueOf(mes)) + 1;

        fecha = dia + "/" + mesEscrito + "/" + anio;
        return fecha;
    }


    //public void llamar(View v) {
    //    String telefono = tvTelefono.getText().toString();
    //PARA EJECUTAR LA LLAMADA, SE DEBERÁ EJECUTAR UN INTENT IMPLICITO YA QUE OCUPA RECURSOS DEL DISPOSITIVO
    //startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono)));
    //}


    //public void email(View v)
    //{
    //    String email = tvEmail.getText().toString();
    //    Intent emailIntent = new Intent((Intent.ACTION_SEND));
    //    emailIntent.setData(Uri.parse("mailto:"));
    //    emailIntent.putExtra(Intent.EXTRA_EMAIL,email);
    //    emailIntent.setType("message/rfc822");
    //    startActivity(Intent.createChooser(emailIntent,"Email"));
    //}


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    private void validamosWidgets()
    {
        tvNombreCardView =      (TextView)findViewById(R.id.txNombreCardView);
        tvTelefonoCardView =    (TextView)findViewById(R.id.txTelefonoCardView);
        tvEmailCardView =       (TextView)findViewById(R.id.txEmailCardView);
        tvDescripcionCardView = (TextView)findViewById(R.id.txDescripcionCardView);
        tvFechaCardView =       (TextView)findViewById(R.id.txFechaCardView);
    }


    public void enviarInformacionRegreso(View v)
    {
        Intent intent = new Intent(this , MainActivity.class);
        intent.putExtra(getResources().getString(R.string.variable_nombre) , nombre);
        intent.putExtra(getResources().getString(R.string.variable_telefono) , telefono);
        intent.putExtra(getResources().getString(R.string.variable_email), email);
        intent.putExtra(getResources().getString(R.string.variable_descripcion) , descripcion);
        intent.putExtra(getResources().getString(R.string.variable_dia) , dia);
        intent.putExtra(getResources().getString(R.string.variable_mes) , mes);
        intent.putExtra(getResources().getString(R.string.variable_anio) , anio);
        startActivity(intent);
    }
}
