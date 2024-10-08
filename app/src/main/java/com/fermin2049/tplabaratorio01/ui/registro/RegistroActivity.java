package com.fermin2049.tplabaratorio01.ui.registro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.fermin2049.tplabaratorio01.R;
import com.fermin2049.tplabaratorio01.databinding.ActivityRegistroBinding;
import com.fermin2049.tplabaratorio01.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private RegistroActivityViewModel registroActivityViewModel;
    private ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        registroActivityViewModel = new ViewModelProvider(this).get(RegistroActivityViewModel.class);

        // Observamos los datos del usuario
        registroActivityViewModel.getUsuario().observe(this, usuario -> {
            binding.etDni.setText(String.valueOf(usuario.getDni()));
            binding.etNombre.setText(usuario.getNombre());
            binding.etApellido.setText(usuario.getApellido());
            binding.etMail.setText(usuario.getMail());
            binding.etPassword.setText(usuario.getPassword());
        });

        // Cargar los datos del usuario
        registroActivityViewModel.cargarDatosUsuario();

        // Acción del botón "Guardar"
        binding.btnGuardar.setOnClickListener(v -> {
            Long DNI = Long.valueOf(binding.etDni.getText().toString());
            String nombre = binding.etNombre.getText().toString();
            String apellido = binding.etApellido.getText().toString();
            String correo = binding.etMail.getText().toString();
            String password = binding.etPassword.getText().toString();

            Usuario usuario = new Usuario(DNI, nombre, apellido, correo, password);
            registroActivityViewModel.guardarUsuario(usuario);
            Toast.makeText(this, "Campos guardados correctamente", Toast.LENGTH_SHORT).show();
        });
    }
}