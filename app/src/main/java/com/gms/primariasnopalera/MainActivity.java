package com.gms.primariasnopalera;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gms.primariasnopalera.databinding.ActivityMainBinding;
import com.gms.primariasnopalera.util.Functions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    Functions functions = new Functions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnIniciarSesion.setOnClickListener(v -> {
            mAuth = FirebaseAuth.getInstance();
            String correo = binding.txtCorreo.getText().toString();
            String contrania = binding.txtContrasenia.getText().toString();
            binding.cor.setError(null);
            binding.con.setError(null);
            if (correo.equals("") && contrania.equals("")) {
                binding.cor.setError("Te pasas");
                binding.con.setError("No llenaste ningún campo");
            } else if (correo.equals("")){
                binding.cor.setError("Ups, te faltó aquí");
            } else if (contrania.equals("")){

                binding.con.setError("No olvides que aquí va tu secreto");
            }
            else {

                mAuth.signInWithEmailAndPassword(correo, contrania)

                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);
                                    functions.snackBar(view, "Logrado", MainActivity.this);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    functions.snackBar(view, "Correo o contraseña equivocada", MainActivity.this);
                                    //updateUI(null);
                                }
                            }
                        });
            }
        });

    }
}