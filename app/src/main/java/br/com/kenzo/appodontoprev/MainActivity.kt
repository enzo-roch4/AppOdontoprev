package br.com.kenzo.appodontoprev

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    private lateinit var buttonStatusActivity: Button
    private lateinit var buttonVerificacaoActivity: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonStatusActivity = findViewById(R.id.buttonStatus)
        buttonVerificacaoActivity = findViewById(R.id.buttonVerificacao)

        // Quando o botão de status é clicado, passa "StatusActivity" para a LoginActivity
        buttonStatusActivity.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("destino", "StatusActivity")  // Passa a informação de destino
            startActivity(intent)
        }

        // Quando o botão de verificação é clicado, passa "VerificacaoActivity" para a LoginActivity
        buttonVerificacaoActivity.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("destino", "VerificacaoActivity")  // Passa a informação de destino
            startActivity(intent)
        }



    }



}


