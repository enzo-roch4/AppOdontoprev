package br.com.kenzo.appodontoprev

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ErroActivity : AppCompatActivity() {

    private lateinit var buttonVoltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_erro)

        buttonVoltar = findViewById(R.id.buttonVoltar)

        buttonVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finaliza a tela de erro
        }
    }
}