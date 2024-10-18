package fr.opc.practice.p9a11y

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import fr.opc.practice.p9a11y.databinding.ActivityCase3Binding

class Case3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.pseudoTitle.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
        binding.pseudoEdit.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
        binding.pseudoValidation.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO
        binding.validateButton.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES

        binding.pseudoEdit.doOnTextChanged { text, _, _, _ ->
            text?.length?.let { textLength ->
                if (textLength < 3) {
                    binding.validateButton.isEnabled = false
                    binding.pseudoEdit.backgroundTintList = ColorStateList.valueOf(
                        resources.getColor(R.color.red400, theme))
                    binding.pseudoValidation.setText(R.string.nameMinimalLength)
                    binding.pseudoEdit.announceForAccessibility(getString(R.string.nameMinimalLength))
                } else {
                    binding.validateButton.isEnabled = true
                    binding.pseudoEdit.backgroundTintList = ColorStateList.valueOf(
                        resources.getColor(R.color.green400, theme))
                    binding.pseudoValidation.setText(R.string.nameValid)
                    binding.pseudoEdit.announceForAccessibility(getString(R.string.nameValid))
                }
            }
        }
    }
}
