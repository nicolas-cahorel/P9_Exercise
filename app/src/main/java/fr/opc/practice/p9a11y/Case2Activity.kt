package fr.opc.practice.p9a11y

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.opc.practice.p9a11y.databinding.ActivityCase2Binding

class Case2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var isFavourite = false
        setFavouriteButtonIcon(isFavourite)

        binding.favoriteButton.setOnClickListener {
            isFavourite = !isFavourite
            setFavouriteButtonIcon(isFavourite)
        }

        binding.addRecipeToBasket.setOnClickListener {
            Toast.makeText(this, getString(R.string.recette_ajout_au_panier), Toast.LENGTH_SHORT)
                .show()
        }

        // Make favoriteButton and addRecipeToBasket ignored by TalkBack
        binding.favoriteButton.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO
        binding.addRecipeToBasket.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO

        binding.recipeCard.setOnClickListener {
            binding.favoriteButton.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
            binding.addRecipeToBasket.importantForAccessibility =
                View.IMPORTANT_FOR_ACCESSIBILITY_YES
            // TODO navigate to recipe screen
        }
    }

    private fun setFavouriteButtonIcon(isFavourite: Boolean) {
        if (isFavourite) {
            binding.favoriteButton.setImageResource(R.drawable.ic_favorite_on)
            binding.favoriteButton.announceForAccessibility(getString(R.string.addedToFavoritesMessage))
            binding.favoriteButton.contentDescription =
                getString(R.string.removeFromFavoritesDescription)
        } else {
            binding.favoriteButton.setImageResource(R.drawable.ic_favorite_off)
            binding.favoriteButton.announceForAccessibility(getString(R.string.removedFromFavoritesMessage))
            binding.favoriteButton.contentDescription =
                getString(R.string.addToFavoritesDescription)
        }
    }
}