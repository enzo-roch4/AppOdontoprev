package br.com.kenzo.appodontoprev

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var buttonLogin: Button
    private lateinit var editTextNome: EditText
    private lateinit var editTextCPF: EditText
    private var destino: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Recupera o destino da intent que foi passado pela MainActivity
        destino = intent.getStringExtra("destino")

        buttonLogin = findViewById(R.id.buttonLogin)
        editTextNome = findViewById(R.id.editTextTextNome)
        editTextCPF = findViewById(R.id.editTextTextCPF)

        buttonLogin.setOnClickListener {

            val nome = editTextNome.text.toString()
            val cpf = editTextCPF.text.toString()

            if (validaLogin(nome, cpf)) {
                // Se a validação foi bem-sucedida, redireciona para a atividade que selecionou inicialment
                when (destino) {
                    "VerificacaoActivity" -> {
                        val intent = Intent(this, VerificacaoActivity::class.java)
                        startActivity(intent)
                    }
                    "StatusActivity" -> {
                        val intent = Intent(this, StatusActivity::class.java)
                        startActivity(intent)
                    }
                    else -> {
                        Toast.makeText(this, "Destino inválido!", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Login inválido!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    // Função de validação de login
    private fun validaLogin(nome: String, cpf: String): Boolean {
        return nome.isNotEmpty() && cpf.length == 11
    }
}
