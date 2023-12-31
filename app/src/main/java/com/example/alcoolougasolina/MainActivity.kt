package com.example.alcoolougasolina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var percentual: Double = 0.7
    private lateinit var edAlcool: EditText
    private lateinit var edGasolina: EditText
    private lateinit var swPercentual: Switch
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        edAlcool = findViewById(R.id.edAlcool)
        edGasolina = findViewById(R.id.edGasolina)
        swPercentual = findViewById(R.id.swPercentual)
        tvResultado = findViewById(R.id.tvResultado)
        val btCalc = findViewById<Button>(R.id.btCalcular)


        if (savedInstanceState != null) {
            percentual = savedInstanceState.getDouble("percentual", 0.7)
            swPercentual.isChecked = percentual == 0.7
        }


        swPercentual.setOnCheckedChangeListener { _, isChecked ->
            percentual = if (isChecked) {
                swPercentual.text = "75%"
                0.75
            } else {
                swPercentual.text = "70%"
                0.70
            }
        }


        btCalc.setOnClickListener {
            val alcoolText = edAlcool.text.toString()
            val gasolinaText = edGasolina.text.toString()

            if (alcoolText.isNotEmpty() && gasolinaText.isNotEmpty()) {
                val alcoolPrice = alcoolText.toDouble()
                val gasolinaPrice = gasolinaText.toDouble()

                val resultado = if (alcoolPrice / gasolinaPrice <= percentual) {
                    tvResultado.text = "Álcool é a melhor escolha!"
                } else {
                    tvResultado.text = "Gasolina é a melhor escolha!"
                }
            } else {
                tvResultado.text = "Por favor preencha os preços dos combustíveis."
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble("percentual", percentual)
    }
}
