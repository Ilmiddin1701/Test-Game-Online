package uz.ilmiddin1701.test_game.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import uz.ilmiddin1701.test_game.R
import uz.ilmiddin1701.test_game.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private val binding by lazy { FragmentProfileBinding.inflate(layoutInflater) }
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")
        binding.apply {

        }
        return binding.root
    }
}