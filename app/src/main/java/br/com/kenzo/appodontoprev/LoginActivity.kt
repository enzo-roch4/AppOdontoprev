package br.com.kenzo.appodontoprev

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.kenzo.appodontoprev.api.UserController

class LoginActivity : AppCompatActivity() {

    private lateinit var buttonLogin: Button
    private lateinit var editTextCPF: EditText
    private lateinit var editTextNome: EditText
    private var destino: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin = findViewById(R.id.buttonLogin)
        editTextCPF = findViewById(R.id.editTextTextCPF)
        editTextNome = findViewById(R.id.editTextTextNome)

        destino = intent.getStringExtra("destino")


        buttonLogin.setOnClickListener {
            val nome = editTextNome.text.toString()
            val cpf = editTextCPF.text.toString()
            val user = User(name = nome, cpf = cpf)
            val userController = UserController()

            userController.createUser(user) { response ->
                if (response.startsWith("Error")) {
                    // Lidar com o erro
                } else {
                    // Usuário criado com sucesso
                }
            }


            // Validação de nome e cpf
            if (!validaLogin(nome, cpf)) {
                // Se a validação falhar, vai para a tela de erro
                val intent = Intent(this, ErroActivity::class.java)
                startActivity(intent)
                finish() // Finaliza a tela atual
            } else {
                // caso login seja valido aqui ele direciona para a atela selecionada
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
                finish()
            }

        }
    }

    // Função de validação que verifica nome e CPF
    private fun validaLogin(nome: String, cpf: String): Boolean {
        // Exemplo simples de validação: nome não pode estar vazio e CPF deve ter 11 caracteres
        return nome.isNotEmpty() && cpf.length == 11
    }
}


