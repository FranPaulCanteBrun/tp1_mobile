package com.example.app_tp1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.app_tp1.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private IncrementarViewModel incrementarViewModel;
    private int res = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Inicializamos nuestro ViewModel
        incrementarViewModel = new ViewModelProvider(this).get(IncrementarViewModel.class);
        Log.d("TAG1", "onCreate()");
        // Mostrar el valor de los editText ya que este metodo se ejecutara varias veces
        binding.tvResCon.setText("Con ViewModel: " + incrementarViewModel.getResultado());
        binding.tvResSin.setText("Sin ViewModel: " + res);
        tarea();
    }

    public void tarea() {
        binding.btnIncrementarSin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                // Actualizar solo la variable local (no se mantiene tras rotacion)
                res = Incrementar.incrementar(res);
                binding.tvResSin.setText("Sin ViewModel: " + res);
            }
        });

        binding.btnIncrementarCon.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                // Incrementar el valor almacenado en el ViewModel (se mantiene tras rotacion)
                incrementarViewModel.setResultado(Incrementar.incrementar(incrementarViewModel.getResultado()));
                binding.tvResCon.setText("Con ViewModel: " + incrementarViewModel.getResultado());
            }
        });
    }

}