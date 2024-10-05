package br.com.kenzo.appodontoprev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.EditText


class VerificacaoActivity : AppCompatActivity() {
    private lateinit var buttonVoltar: Button
    private lateinit var buttonEnviaForm: Button
    private lateinit var editTextCPF: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verificacao)

        buttonVoltar = findViewById(R.id.buttonVoltar)
        buttonEnviaForm = findViewById(R.id.buttonEnviaForm)
        editTextCPF = findViewById(R.id.editTextTextCPF)



        
        buttonVoltar.setOnClickListener {
            finish()  // Encerra a atividade atual e faz o retorno
        }
        buttonEnviaForm.setOnClickListener {
            val cpf = editTextCPF.text.toString()

            // Validar CPF
            if (isValidCPF(cpf)) {
                // CPF é válido, mostrar uma mensagem de sucesso
                Toast.makeText(this, "CPF válido! Formulário enviado.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ErroActivity::class.java)
                startActivity(intent)
            }
        }

    }
    private fun isValidCPF(cpf: String): Boolean {
        return cpf.length == 11 && cpf.all { it.isDigit() }
    }

}