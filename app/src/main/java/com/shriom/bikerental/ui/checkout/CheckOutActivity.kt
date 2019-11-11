package com.shriom.bikerental.ui.checkout

import android.app.DatePickerDialog
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shriom.bikerental.R
import com.shriom.bikerental.databinding.ActivityCheckoutBinding
import com.shriom.bikerental.net.models.Bike
import com.shriom.bikerental.utils.SharedPref
import org.joda.time.LocalDate

class CheckOutActivity : AppCompatActivity() {

    companion object {
        const val BIKE = "bike"
    }

    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var viewModel: CheckOutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = CheckOutViewModel.Factory(SharedPref.getInstance().getUser()!!, intent.getSerializableExtra(BIKE) as Bike)
        viewModel = ViewModelProviders.of(this, factory).get(CheckOutViewModel::class.java)
        viewModel.calculate()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_checkout)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.setOnPayClick { onPayClick() }
        binding.setOnCheckInDateClick { showCheckInDateDialog() }
        binding.setOnCheckOutDateClick { showCheckOutDateDialog() }


    }



    private fun onPayClick() {
        Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()

        Handler().postDelayed({
            viewModel.createBooking().observe(this, Observer { booking ->
                if( booking != null ){
                    Toast.makeText(this@CheckOutActivity,  "Booking Successful", Toast.LENGTH_SHORT).show()
                    Handler().postDelayed({ this@CheckOutActivity.finish() }, 1000)
                    finish()
                }
            })
        }, 2000)
    }

    private fun showCheckInDateDialog() {

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                viewModel.mCheckInDate.value = LocalDate(year, month + 1, dayOfMonth)
                if (viewModel.mCheckOutDate.value!!.isBefore(viewModel.mCheckInDate.value!!)) {
                        viewModel.mCheckOutDate.value =
                            LocalDate(year, month + 1, dayOfMonth).plusDays(2)
                }
                viewModel.calculate()
            },
            viewModel.mCheckInDate.value!!.year,
            viewModel.mCheckInDate.value!!.monthOfYear - 1,
            viewModel.mCheckInDate.value!!.dayOfMonth
        )
        datePickerDialog.datePicker.minDate = LocalDate.now().plusDays(2).toDate().time
        datePickerDialog.datePicker.maxDate =
            LocalDate.now().plusDays(2).plusMonths(2).toDate().time
        datePickerDialog.show()
    }


    private fun showCheckOutDateDialog() {

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                viewModel.mCheckOutDate.value = LocalDate(year, month + 1, dayOfMonth)
                if (viewModel.mCheckInDate.value!!.isAfter(viewModel.mCheckOutDate.value!!)) {
                    viewModel.mCheckInDate.value =
                        LocalDate(year, month + 1, dayOfMonth).minusDays(2)
                }
                viewModel.calculate()
            },
            viewModel.mCheckOutDate.value!!.year,
            viewModel.mCheckOutDate.value!!.monthOfYear - 1,
            viewModel.mCheckOutDate.value!!.dayOfMonth
        )
        datePickerDialog.datePicker.minDate = LocalDate.now().plusDays(3).toDate().time
        datePickerDialog.datePicker.maxDate =
            LocalDate.now().plusDays(3).plusMonths(2).toDate().time
        datePickerDialog.show()
    }


}