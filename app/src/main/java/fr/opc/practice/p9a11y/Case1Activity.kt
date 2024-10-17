package fr.opc.practice.p9a11y

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.opc.practice.p9a11y.databinding.ActivityCase1Binding

class Case1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var quantity = 0

        // Post à la racine pour éviter que TalkBack lise le nom de l'activité
        binding.root.post {
            // Demander à TalkBack de ne pas lire le nom de l'activité
            binding.root.announceForAccessibility("")  // Message vide
            // Rediriger le focus vers quantityText
            binding.casTitle1.requestFocus()
        }

        binding.quantityText.text = "$quantity"
        binding.quantityText.contentDescription = getString(R.string.quantity_prefix) + quantity
        binding.addButton.setOnClickListener {
            quantity++
            binding.quantityText.text = "$quantity"
            binding.quantityText.contentDescription =
                getString(R.string.quantity_prefix) + quantity
            binding.quantityText.announceForAccessibility(getString(R.string.new_quantity_announce) + quantity)
        }

        binding.removeButton.setOnClickListener {
            if (quantity > 0) {
                quantity--
                binding.quantityText.text = "$quantity"
                binding.quantityText.contentDescription =
                    getString(R.string.quantity_prefix) + quantity
                binding.quantityText.announceForAccessibility(getString(R.string.new_quantity_announce) + quantity)
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.impossible_d_avoir_une_quantit_n_gative),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Mettre l'importance d'accessibilité à NO pour empêcher TalkBack de lire le nom de l'activité
        binding.root.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO
        // Demander à TalkBack de ne pas lire le nom de l'activité
        binding.root.announceForAccessibility("") // Annonce vide
        // Rediriger le focus vers quantityText
        binding.casTitle1.requestFocus()
    }

}
