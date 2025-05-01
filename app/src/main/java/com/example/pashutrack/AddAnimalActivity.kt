package com.example.pashutrack

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddAnimalActivity : AppCompatActivity() {

    private lateinit var etAnimalName: EditText
    private lateinit var etAnimalType: EditText
    private lateinit var etAnimalAge: EditText
    private lateinit var etAnimalBreed: EditText
    private lateinit var btnAddAnimal: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_animal)

        etAnimalName = findViewById(R.id.etAnimalName)
        etAnimalType = findViewById(R.id.etAnimalType)
        etAnimalAge = findViewById(R.id.etAnimalAge)
        etAnimalBreed = findViewById(R.id.etAnimalBreed)
        btnAddAnimal = findViewById(R.id.btnAddAnimal)

        btnAddAnimal.setOnClickListener {
            val name = etAnimalName.text.toString().trim()
            val type = etAnimalType.text.toString().trim()
            val age = etAnimalAge.text.toString().trim()
            val breed = etAnimalBreed.text.toString().trim()

            if (name.isEmpty() || type.isEmpty() || age.isEmpty() || breed.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // You can save the data to a database or API here
                Toast.makeText(this, "Animal added: $name ($type)", Toast.LENGTH_SHORT).show()

                // Clear fields
                etAnimalName.text.clear()
                etAnimalType.text.clear()
                etAnimalAge.text.clear()
                etAnimalBreed.text.clear()
            }
        }
    }
}
