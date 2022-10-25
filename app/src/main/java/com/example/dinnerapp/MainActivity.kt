package com.example.dinnerapp

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.dinnerapp.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val foodArray: ArrayList<String> = ArrayList()
        foodArray.add("sushi")
        foodArray.add("pasta")
        foodArray.add("ramen")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, foodArray)
        binding.spinner.adapter = adapter

        binding.addButton.setOnClickListener {
            addDelete(it, true, foodArray)
        }
        binding.deleteButton.setOnClickListener{
            addDelete(it, false, foodArray)
        }
        binding.randomButton.setOnClickListener{
            randomize(it, foodArray)
        }
    }

    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    private fun addDelete(view: View, addBool : Boolean, foodArray : ArrayList<String>) {
        if(addBool) {
            val food : String = binding.Entry.text.toString()
            if(food.replace(" ", "") != "" && food.lowercase() !in foodArray) {
                foodArray.add(food.lowercase())
                toast("$food added!")
            } else if(food.lowercase() in foodArray) {
                toast("$food is already in list!")
            } else if(food.replace(" ", "") == "") {
                toast("You are adding nothing!")
            }
        }
        if(!addBool) {
            val selected = binding.spinner.selectedItem
            foodArray.remove(selected)
            toast("$selected removed")
        }
    }

    private fun randomize(view: View, foodArray: ArrayList<String>) {
        val choice = Random.nextInt(0, foodArray.size-1)
        val urchoice = foodArray[choice]

        binding.randomChoice.text = urchoice
    }
}