package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.BirthDate
import com.example.myapplication.model.PanNumber

class MainActivityViewModel: ViewModel() {

    private val panModel = PanNumber("")
    private val bdayModel = BirthDate("","","")
    val textLiveData = MutableLiveData<String>()

    fun setPanNumber(pan: String){
        panModel.panNumber = pan
    }

    fun setDate(date: String){
        bdayModel.date = date
    }

    fun setMonth(month: String){
        bdayModel.month = month
    }

    fun setYear(year: String){
        bdayModel.year = year
    }

    fun checkValidity(): Boolean{
        if(panModel.panNumber.length < 10 || bdayModel.date.isEmpty() || bdayModel.date.toInt() > 31 || bdayModel.month.isEmpty() || bdayModel.month.toInt() > 12 || bdayModel.year.isEmpty() || bdayModel.year.toInt() > 2022 || (bdayModel.year.toInt() == 2022 && bdayModel.month.toInt() > 7)) {
            textLiveData.postValue("Invalid Details")
            return false
        }
        textLiveData.postValue("Details submitted successfully")
        return true
    }
}