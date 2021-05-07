package com.cahstudio.tesapotekk24.ui.addmember

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.cahstudio.tesapotekk24.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class AddMemberActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, View.OnClickListener {

    private val mViewModel by viewModel<AddMemberViewModel>()
    private lateinit var datePicker: DatePickerDialog

    private lateinit var etName: EditText
    private lateinit var etDateBirth: EditText
    private lateinit var btnAdd: AppCompatButton

    private var partnerID: String? = null
    private var partnerCode: String? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_member)

        partnerID = intent.getStringExtra("partnerID")
        partnerCode = intent.getStringExtra("partnerCode")

        val c: Calendar = Calendar.getInstance()
        val year: Int = c.get(Calendar.YEAR)
        val month: Int = c.get(Calendar.MONTH)
        val day: Int = c.get(Calendar.DAY_OF_MONTH)
        datePicker = DatePickerDialog(this, this, year, month, day)

        initView()
    }

    fun initView() {
        etName = findViewById(R.id.etName)
        etDateBirth = findViewById(R.id.etDate)
        btnAdd = findViewById(R.id.btnAdd)

        etDateBirth.setOnClickListener(this)
        btnAdd.setOnClickListener(this)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        var finalMonth = month
        etDateBirth.setText("$year-${++finalMonth}-$dayOfMonth")
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.etDate -> {
                datePicker.show()
            }
            R.id.btnAdd -> {
                addMember()
            }
        }
    }

    fun addMember() {
        val name = etName.text.toString()
        val date = etDateBirth.text.toString()

        if (name.trim().isNotEmpty() && date.trim().isNotEmpty() ) {
            partnerID?.let { partnerCode?.let { it1 -> mViewModel.addMember(it, it1, name, date)
                .observe(this, {
                    if (it.status == "success") {
                        setResult(RESULT_OK)
                        finish()
                    } else {
                        Toast.makeText(this, "Add member failed", Toast.LENGTH_SHORT).show()
                    }
                }) } }
        } else {
            Toast.makeText(this, "Name and date must be fill", Toast.LENGTH_SHORT).show()
        }
    }
}