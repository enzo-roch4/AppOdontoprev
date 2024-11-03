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


        FirebaseDatabase.getInstance().setPersistenceEnabled(true)



    }
    private fun writeNewUser(userId: String, name: String, cpf: String) {
        val user = User(name, cpf)
        val database = FirebaseDatabase.getInstance().reference

        database.child("users").child(userId).setValue(user)
            .addOnSuccessListener {
                Log.d("Firebase", "Dados salvos com sucesso.")
            }
            .addOnFailureListener { exception ->
                Log.e("Firebase", "Erro ao salvar os dados.", exception)
            }
    }


    private fun readUser(userId: String) {
        val database = FirebaseDatabase.getInstance().reference

        database.child("users").child(userId).get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val user = snapshot.getValue(User::class.java)
                    Log.d("Firebase", "Nome: ${user?.name}, CPF: ${user?.cpf}")
                } else {
                    Log.d("Firebase", "Usuário não encontrado.")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Firebase", "Erro ao ler os dados.", exception)
            }
    }
}


