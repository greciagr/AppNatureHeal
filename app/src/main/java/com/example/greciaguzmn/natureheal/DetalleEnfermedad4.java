package com.example.greciaguzmn.natureheal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class DetalleEnfermedad4 extends AppCompatActivity {

    TextView txtNombre;

    //Todos los Text del layout
    TextView txtTitulo;
    TextView txtDescripcion;
    TextView txtPrecaucion;
    TextView txtRecomendacion;
    TextView txtSintomas;
    //Mandamos a buscar la referencia a la raiz de nuestra BD no relacional
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    //Recorremos la BD hasta llegar al dato que nos interesa
    private DatabaseReference dbFiebre = mRootRef.child("enfermedades").child("fiebre");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_enfermedad4);
        dbFiebre.keepSynced(true);
        txtNombre = (TextView) findViewById(R.id.dtituloenfermedad4);
        txtNombre.setText(getIntent().getStringExtra("nombreEnfermedad"));
        // Todos los TextView del layout
        txtTitulo = (TextView) findViewById(R.id.dtituloenfermedad4);
        txtDescripcion = (TextView) findViewById(R.id.parrafoFiebre);
        txtPrecaucion = (TextView) findViewById(R.id.parrafo1PreFiebre);
        txtRecomendacion = (TextView) findViewById(R.id.parrafoRecFiebre);
        txtSintomas = (TextView) findViewById(R.id.parrafoSintomasFiebre);
    }

    //Este es el metodo cuando se ejecuta la aplicacion.
    @Override
    protected void onStart() {
        super.onStart();

        dbFiebre.addValueEventListener(new ValueEventListener() {
            //Este metodo sirve para cuando inicia y cuando sucede un cambio
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Nuestro respectivo Map que le hacemos un pequeño casting para soporte toda la info de gripe
                Map<String, String> map = (Map)dataSnapshot.getValue();
                //Le pedimos el dato con su clave especifica
                String titulo = map.get("nombre");
                String descripcion = map.get("descripcion");
                String sintomas = map.get("sintomas");
                String precaucion = map.get("precaucion");
                String recomendacion = map.get("recomendacion");
                //Agregamos los cambios
                txtTitulo.setText(titulo);
                txtDescripcion.setText(descripcion);
                txtSintomas.setText(sintomas);
                txtPrecaucion.setText(precaucion);
                txtRecomendacion.setText(recomendacion);
            }

            //Este metodo sirve para cuando da error
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
