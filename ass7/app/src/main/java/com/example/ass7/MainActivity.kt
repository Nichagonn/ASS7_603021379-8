package com.example.ass7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_dialog_layout.*
import kotlinx.android.synthetic.main.add_dialog_layout.view.*
import kotlinx.android.synthetic.main.emp_item_layout.view.*


class MainActivity : AppCompatActivity() {
    val employeeList = arrayListOf<Employee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testEmployeeData()
        recycler_view.adapter = EmployeeAdapter(this.employeeList, applicationContext)
        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
        recycler_view.itemAnimator = DefaultItemAnimator()

    }

    fun addEmployee(v: View) {

        val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_dialog_layout, null)
        val mBuilder = AlertDialog.Builder(this)

        mBuilder.setView(mDialogView)
        val mAlertDialog = mBuilder.show()

        mDialogView.btnAdd.setOnClickListener {

            var radioGroup: RadioGroup = mDialogView.radioGroup
            var selectedId:Int = radioGroup.checkedRadioButtonId
            var radioButton: RadioButton = mDialogView.findViewById(selectedId)

            employeeList.add(Employee(
                mDialogView.edit_name.text.toString(),
                radioButton.text.toString(),
                mDialogView.edit_email.text.toString(),
                mDialogView.edit_salary.text.toString().toInt()
            )
            )
            recycler_view.adapter?.notifyDataSetChanged()
            Toast.makeText(applicationContext, "The Employee is added successfully", Toast.LENGTH_SHORT).show()
            mAlertDialog.dismiss()
        }
        mDialogView.btnCancel.setOnClickListener() {
            mAlertDialog.dismiss()
        }
    }
    fun testEmployeeData() {
        employeeList.add(Employee("Danny","Male","Danny@kku.ac.th",30000))
        employeeList.add(Employee("Sara","Female","Sara@kku.ac.th",34000))
    }
}