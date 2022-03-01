package com.example.calculadoradepropina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculadoradepropina.databinding.ActivityMainBinding
import android.widget.Toast
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.button15.setOnClickListener {
            interfaceCalculatetip(.15)
            //Toast.makeText(this, "Seleccionaste: 25%", Toast.LENGTH_SHORT).show()
        }
        binding.button25.setOnClickListener {
            interfaceCalculatetip(.25)
            //Toast.makeText(this, "Seleccionaste: 25%", Toast.LENGTH_SHORT).show()
        }
        binding.button35.setOnClickListener {
            interfaceCalculatetip(.35)
            //Toast.makeText(this, "Seleccionaste: 35%", Toast.LENGTH_SHORT).show()
        }
        binding.roundedTipButton.setOnClickListener {
            roundedTip()
        }
    }

    private fun roundedTip() {
        val amount = binding.totalAmount.text.toString()
        val amountValue = amount.toDoubleOrNull()

        if (amountValue == null || amountValue == 0.0) {
            binding.totalTip.text = "0.0"
            binding.totalAmount.text ="0.0"
            Toast.makeText(this, "¡No has ingresado un total!, intenta de nuevo.", Toast.LENGTH_SHORT).show()
            return
        }
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        val roundedTip = df.format(amountValue)
        binding.roundedAmountText.text = roundedTip
        Toast.makeText(this, "¡Redondeo final!", Toast.LENGTH_SHORT).show()
    }

    private fun interfaceCalculatetip(percentage: Double) {
        val amount = binding.editTextNumberDecimal.text.toString()
        val amountValue = amount.toDoubleOrNull()

        if (amountValue == null || amountValue == 0.0) {
            binding.totalTip.text = "0.0"
            binding.totalAmount.text ="0.0"
            Toast.makeText(this, "¡No has ingresado un total!, intenta de nuevo.", Toast.LENGTH_SHORT).show()
            return
        } else if (amountValue < 0) {
            binding.totalTip.text = "0.0"
            binding.totalAmount.text ="0.0"
            Toast.makeText(this, "El total no puede ser negativo, intenta de nuevo.", Toast.LENGTH_SHORT).show()
            return
        }
        binding.totalTip.text = "${amountValue * percentage}"
        binding.totalAmount.text = "${amountValue * (1 + percentage)}"
        Toast.makeText(this, "Seleccionaste: ${percentage * 100}%", Toast.LENGTH_SHORT).show()
    }
}