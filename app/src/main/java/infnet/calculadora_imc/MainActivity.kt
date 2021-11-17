package infnet.calculadora_imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val imagemMenino = findViewById<ImageView>(R.id.menino)
        val imagemMenina = findViewById<ImageView>(R.id.menina)
        val peso = findViewById<EditText>(R.id.peso)
        val altura = findViewById<EditText>(R.id.altura)
        val botaoCalculo = findViewById<Button>(R.id.botao_calculo)
        val imc = findViewById<TextView>(R.id.imc)
        val imcStatus = findViewById<TextView>(R.id.imc_status)
        val imcView = findViewById<LinearLayout>(R.id.imcView)
        val botaoCalculoNovamente = findViewById<TextView>(R.id.calcule_novamente)

        imagemMenino.setOnClickListener {
            imagemMenino.setImageResource(R.drawable.menino_img)
            imagemMenina.setImageResource(R.drawable.menina_img2)
        }

        imagemMenina.setOnClickListener {
            imagemMenino.setImageResource(R.drawable.menino_img2)
            imagemMenina.setImageResource(R.drawable.menina_img)
        }

        botaoCalculo.setOnClickListener {
            var valorPeso = 0.0
            var valorAltura = 0.0
            if (peso.text.toString().isNotEmpty()) {
                valorPeso = peso.text.toString().toDouble()
            }
            if (altura.text.toString().isNotEmpty()) {
                valorAltura = (altura.text.toString().toDouble() / 100)
            }
            if (valorPeso > 0.0 && valorAltura > 0.0) {
                val imcValor = String.format("%.2f", valorPeso / valorAltura.pow(2))
                imc.text = imcValor
                imcStatus.text = imcStatusValor(valorPeso / valorAltura.pow(2))
                imcView.visibility = VISIBLE
                botaoCalculo.visibility = GONE
            } else
                Toast.makeText(this, "Informe seu peso e altura. O valor deve ser maior do que 0", Toast.LENGTH_LONG).show()
        }

        botaoCalculoNovamente.setOnClickListener {
            imcView.visibility = GONE
            botaoCalculo.visibility = VISIBLE
            peso.text.clear()
            peso.text.clear()
            peso.requestFocus()
        }
    }

    private fun imcStatusValor(imc: Double): String {
        lateinit var imcStatus: String
        if (imc < 18.5)
            imcStatus = "Subpeso"
        else if (imc >= 18.5 && imc < 25)
            imcStatus = "Normal"
        else if (imc >= 25 && imc < 30)
            imcStatus = "Sobrepeso"
        else if (imc >= 30 && imc < 34.9)
            imcStatus = "Obesidade grau 1"
        else if (imc >= 34.9 && imc < 39.9)
            imcStatus = "Obesidade grau 2"
        else if (imc >= 39.9)
            imcStatus = "Obesidade grau 3"
        return imcStatus
    }
}