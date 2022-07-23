package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.viewmodel.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var model: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        init()
    }

    private fun init(){
        binding.nextBtn.isEnabled = false
        setListeners()
        setClickListener()
    }

    private fun setListeners(){
        binding.panEdt.addTextChangedListener(panTextWatcher)
        binding.dateEdt.addTextChangedListener(dateTextWatcher)
        binding.monthEdt.addTextChangedListener(monthTextWatcher)
        binding.yearEdt.addTextChangedListener(yearTextWatcher)

        binding.panEdt.setOnFocusChangeListener { view, b ->
            if(b){
                binding.panEdt.background = resources.getDrawable(R.drawable.pan_selected)
            }
            else{
                binding.panEdt.background = resources.getDrawable(R.drawable.pan_unselected)
            }
        }

        binding.dateEdt.setOnFocusChangeListener { view, b ->
            if(b){
                binding.dateEdt.background = resources.getDrawable(R.drawable.bday_selected)
            }
            else{
                binding.dateEdt.background = resources.getDrawable(R.drawable.bday_unselected)
            }
        }

        binding.monthEdt.setOnFocusChangeListener { view, b ->
            if(b){
                binding.monthEdt.background = resources.getDrawable(R.drawable.bday_selected)
            }
            else{
                binding.monthEdt.background = resources.getDrawable(R.drawable.bday_unselected)
            }
        }

        binding.yearEdt.setOnFocusChangeListener { view, b ->
            if(b){
                binding.yearEdt.background = resources.getDrawable(R.drawable.bday_selected)
            }
            else{
                binding.yearEdt.background = resources.getDrawable(R.drawable.bday_unselected)
            }
        }
    }

    private val panTextWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
            binding.nextBtn.isEnabled = model.checkValidity()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            model.setPanNumber(s.toString())
        }
    }

    private val dateTextWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
            binding.nextBtn.isEnabled = model.checkValidity()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            model.setDate(s.toString())
        }
    }

    private val monthTextWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
            binding.nextBtn.isEnabled = model.checkValidity()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            model.setMonth(s.toString())
        }
    }

    private val yearTextWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
            binding.nextBtn.isEnabled = model.checkValidity()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            model.setYear(s.toString())
        }
    }

    private fun setClickListener(){
        binding.nextBtn.setOnClickListener {
            var bool = model.checkValidity()
            if(bool) {
                binding.nextBtn.isEnabled = bool
                finish()
            }
            model.textLiveData.observe(this) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        binding.noPanTv.setOnClickListener {
            finish()
        }
    }
}