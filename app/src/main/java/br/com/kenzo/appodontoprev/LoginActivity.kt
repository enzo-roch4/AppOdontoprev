package br.com.kenzo.appodontoprev

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.kenzo.appodontoprev.api.RetrofitClient
import br.com.kenzo.appodontoprev.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

            // Validação de nome e cpf
            if (!validaLogin(nome, cpf)) {
                // Se a validação falhar, vai para a tela de erro
                val intent = Intent(this, ErroActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                createUserOnApi(User(name = nome, cpf = cpf))
            }
        }
    }

    // Função de validação que verifica nome e CPF
    private fun validaLogin(nome: String, cpf: String): Boolean {
        // Exemplo simples de validação: nome não pode estar vazio e CPF deve ter 11 caracteres
        return nome.isNotEmpty() && cpf.length == 11
    }

    private fun createUserOnApi(user: User) {
        val apiService = RetrofitClient.instance.createUser(user)
        apiService.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    // Direciona o usuário para a tela correta após o cadastro
                    when (destino) {
                        "VerificacaoActivity" -> {
                            startActivity(Intent(this@LoginActivity, VerificacaoActivity::class.java))
                        }
                        "StatusActivity" -> {
                            startActivity(Intent(this@LoginActivity, StatusActivity::class.java))
                        }
                        else -> {
                            Toast.makeText(this@LoginActivity, "Destino inválido!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Erro ao criar usuário na API!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Erro de conexão: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
