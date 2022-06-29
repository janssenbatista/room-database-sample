package dev.janssenbatista.roomdatabasesample.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dev.janssenbatista.roomdatabasesample.R
import dev.janssenbatista.roomdatabasesample.data.User
import dev.janssenbatista.roomdatabasesample.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_add, container, false)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        view.btnAdd.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val firstName = edtFirstName.text.toString()
        val lastName = edtLastName.text.toString()
        val age = edtAge.text
        if (inputCheck(firstName, lastName, age)) {
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            userViewModel.addUser(user)
            Toast.makeText(requireContext(), "User added successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }

    }

    // checks that the fields are not empty
    private fun inputCheck(fName: String, lName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(fName) && TextUtils.isEmpty(lName) && age.isEmpty())
    }


}