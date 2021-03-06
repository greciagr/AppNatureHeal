package com.example.greciaguzmn.natureheal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class Remedios extends AppCompatActivity {

    private RecyclerView nlistenremedios;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remedios);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("remedios");
        mDatabase.keepSynced(true);

        nlistenremedios = (RecyclerView) findViewById(R.id.listaremedios);
        nlistenremedios.setHasFixedSize(true);
        nlistenremedios.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<EnfermedadRemedio, RemedioViewHolder> FirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<EnfermedadRemedio, RemedioViewHolder>(
                EnfermedadRemedio.class, R.layout.lista_view2, RemedioViewHolder.class,mDatabase) {


            @Override
            protected void populateViewHolder(RemedioViewHolder viewHolder, EnfermedadRemedio model, final int position) {

                final String post_key = model.getNombre();

                viewHolder.setTitle(model.getNombre());
                viewHolder.setImage(getApplicationContext(), model.getImagen());


                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ((post_key.equals("Te de lavanda"))) {
                            Intent intent = new Intent(getApplicationContext(), Detalle_remedio.class);
                            startActivity(intent);
                        }else if ((post_key.equals("Perejil con limon"))) {
                            Intent intent = new Intent(getApplicationContext(), detalles_remedio2.class);
                            startActivity(intent);
                        }else if ((post_key.equals("Vinagre de manzana"))) {
                            Intent intent = new Intent(getApplicationContext(), detalle_remedio3.class);
                            startActivity(intent);
                        }else if ((post_key.equals("Aceite de coco"))) {
                            Intent intent = new Intent(getApplicationContext(), detalle_remedio4.class);
                            startActivity(intent);
                        }
                    }
                });

            }
        };

        nlistenremedios.setAdapter(FirebaseRecyclerAdapter);
    }

    public static class RemedioViewHolder extends RecyclerView.ViewHolder{

        View mview;
        TextView post_title;

        public RemedioViewHolder(View itemView) {
            super(itemView);

            mview = itemView ;

            post_title = (TextView) mview.findViewById(R.id.postTitle2);

            post_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void setTitle(String title) {

            TextView postTitle = (TextView) mview.findViewById(R.id.postTitle2);
            postTitle.setText(title);
        }

        public void setImage (final Context ctx, final String image) {

            final ImageView post_image = (ImageView) mview.findViewById(R.id.postImage2);
            Picasso.with(ctx).load(image).networkPolicy(NetworkPolicy.OFFLINE).into(post_image, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Picasso.with(ctx).load(image).into(post_image);
                }
            });

        }


    }

}
