package uz.ilmiddin1701.test_game.Fragments

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import uz.ilmiddin1701.test_game.R
import uz.ilmiddin1701.test_game.Utils.MyData
import uz.ilmiddin1701.test_game.Utils.MySharedPreferences
import uz.ilmiddin1701.test_game.databinding.FragmentProfileBinding
import uz.ilmiddin1701.test_game.databinding.ItemEditDialogBinding
import java.io.File
import java.io.FileOutputStream

class ProfileFragment : Fragment() {
    private val binding by lazy { FragmentProfileBinding.inflate(layoutInflater) }
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var storageReference: StorageReference

    private var firstName = ""
    private var lastName = ""
    private var userName = ""
    private var password = ""
    private var image = ""
    private var uri: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")

        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = firebaseStorage.getReference("images")

        MySharedPreferences.init(requireContext())
        reference.child(MySharedPreferences.userId)
            .addValueEventListener(object : ValueEventListener {
                @SuppressLint("SetTextI18n")
                override fun onDataChange(snapshot: DataSnapshot) {
                    firstName = snapshot.child("firstName").value.toString()
                    lastName = snapshot.child("lastName").value.toString()
                    userName = snapshot.child("userName").value.toString()
                    password = snapshot.child("password").value.toString()
                    binding.apply {
                        tvName.text = "$firstName $lastName"
                        tvUserName.text = "@$userName"
                        if (snapshot.child("userImage").value != "null") {
                            image = snapshot.child("userImage").value.toString()
                            Picasso.get().load(image).into(userImage)
                        } else {
                            userImage.setImageResource(R.drawable.ic_user)
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                }
            })
        binding.apply {
            btnDelete.setOnClickListener {
                val dialog = AlertDialog.Builder(requireContext()).create()
                dialog.setTitle("Delete account")
                dialog.setMessage("Are you sure you want to delete your account?")
                dialog.setCancelable(false)
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Delete") { _, _ ->
                    reference.child(MySharedPreferences.userId).removeValue()
                    MySharedPreferences.userId = "empty"
                    MyData.closeAccount.postValue(true)
                }
                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel") { _, _ ->
                    dialog.cancel()
                }
                dialog.show()
            }
            btnLogout.setOnClickListener {
                val dialog = AlertDialog.Builder(requireContext()).create()
                dialog.setMessage("Are you sure you want to log out?")
                dialog.setCancelable(false)
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Log out") { _, _ ->
                    MySharedPreferences.userId = "empty"
                    MyData.closeAccount.postValue(true)
                }
                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel") { _, _ ->
                    dialog.cancel()
                }
                dialog.show()
            }
            btnEdit.setOnClickListener {
                val dialog = AlertDialog.Builder(requireContext()).create()
                val dialogItem = ItemEditDialogBinding.inflate(layoutInflater)
                dialog.setCancelable(false)
                dialogItem.apply {
                    btnSaveL.observe(viewLifecycleOwner) {
                        if (it) {
                            btnSave.isEnabled = true
                            progressBar.visibility = View.GONE
                            Picasso.get().load(uri).into(dialogItem.userImage)
                        }
                    }
                    edtFirstName.setText(firstName)
                    edtLastName.setText(lastName)
                    edtUserName.setText(userName)
                    edtPassword.setText(password)
                    if (image != "") {
                        Picasso.get().load(image).into(userImage)
                    } else {
                        userImage.setImageResource(R.drawable.ic_user)
                    }
                    dialogItem.userImageCard.setOnClickListener {
                        getImageContent.launch("image/*")
                        btnSave.isEnabled = false
                        btnSaveL.postValue(false)
                        progressBar.visibility = View.VISIBLE
                    }
                    btnSave.setOnClickListener {
                        if (edtFirstName.text.toString().isNotBlank() && edtLastName.text.toString().isNotBlank() && edtUserName.text.toString().isNotBlank() && edtPassword.text.toString().isNotBlank()) {
                            reference.child(MySharedPreferences.userId).child("firstName").setValue(edtFirstName.text.toString())
                            reference.child(MySharedPreferences.userId).child("lastName").setValue(edtLastName.text.toString())
                            reference.child(MySharedPreferences.userId).child("userImage").setValue(uri)
                            reference.child(MySharedPreferences.userId).child("userName").setValue(edtUserName.text.toString())
                            reference.child(MySharedPreferences.userId).child("password").setValue(edtPassword.text.toString())
                        }
                        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                        dialog.cancel()
                    }
                    btnCancel.setOnClickListener {
                        dialog.cancel()
                    }
                }
                dialog.setView(dialogItem.root)
                dialog.show()
            }
        }
        return binding.root
    }

    private val btnSaveL = MutableLiveData<Boolean>()
    private val getImageContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        binding.apply {
            it ?: return@registerForActivityResult
            if (it.toString().isBlank()) {
                btnSaveL.postValue(true)
            }
            val m = System.currentTimeMillis()
            val task = storageReference.child(m.toString()).putFile(it)
            task.addOnSuccessListener {
                if (it.task.isSuccessful) {
                    val downloadURL = it.metadata?.reference?.downloadUrl
                    downloadURL?.addOnSuccessListener { imageURL ->
                        uri = imageURL.toString()
                        btnSaveL.postValue(true)
                    }
                }
            }
            task.addOnFailureListener {
                btnSaveL.postValue(true)
                Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}