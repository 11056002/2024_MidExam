package im.ntub.myapplicationactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import im.ntub.myapplicationactivity.databinding.ActivityMainBinding

// 組員：11056002 黃安銚、11056032 曾聖荃、1105650 陳一吉

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val selectedDishName = data?.getStringExtra("MainDish")
                val selectedDrinkName = data?.getStringExtra("Drink")
                val desert1 = data?.getStringExtra("desertCookie")
                val desert2 = data?.getStringExtra("desertBuffet")
                val TotalPrice = data?.getIntExtra("TotalPrice", 0)

                binding.txtMainDish.text = selectedDishName
                binding.txtBeverage.text = selectedDrinkName
                binding.txtDesert.text = "$desert1 $desert2"
                binding.txtTotal.text = TotalPrice.toString()

            } else {
                binding.MainDishTxt.text = ""
                binding.txtBeverage.text = ""
                binding.txtDesert.text = ""
            }
        }

        binding.btnOpen.setOnClickListener {
            val intent = Intent(this, SecActivity::class.java)
            launcher.launch(intent)
        }
    }
}
