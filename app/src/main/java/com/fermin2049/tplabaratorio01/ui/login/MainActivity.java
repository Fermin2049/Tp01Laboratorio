package com.fermin2049.tplabaratorio01.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.fermin2049.tplabaratorio01.R;
import com.fermin2049.tplabaratorio01.databinding.ActivityMainBinding;
import com.fermin2049.tplabaratorio01.ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // Observamos el cambio en el usuario
        mainActivityViewModel.getUsuario().observe(this, usuario -> {
            navegarARegistro(); // Si hay un usuario, navegamos sin usar condicionales
        });

        // Observamos los mensajes de error
        mainActivityViewModel.getMensaje().observe(this, mensaje -> {
            mostrarMensajeError(mensaje); // Si hay un mensaje, lo mostramos
        });

        // Acción del botón Ingresar
        binding.btnIngresar.setOnClickListener(v -> {
            String correo = binding.etMail.getText().toString();
            String password = binding.etPassword.getText().toString();
            mainActivityViewModel.ingresar(correo, password);
        });

        // Acción del botón Registrar
        binding.btnRegistrar.setOnClickListener(v -> {
            mainActivityViewModel.registrar();
        });
    }

    private void navegarARegistro() {
        Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
        startActivity(intent);
    }

    private void mostrarMensajeError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}