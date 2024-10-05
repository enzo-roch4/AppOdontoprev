package br.com.kenzo.appodontoprev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ErroActivity : AppCompatActivity() {
    private lateinit var buttonVoltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_erro)

        buttonVoltar = findViewById(R.id.buttonVoltar)
        buttonVoltar.setOnClickListener {
            finish()  // Encerra a atividade atual e retorna para a antrior
        }
    }
}