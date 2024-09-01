package uz.ilmiddin1701.test_game.Fragments.Register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import uz.ilmiddin1701.test_game.Models.User
import uz.ilmiddin1701.test_game.R
import uz.ilmiddin1701.test_game.Utils.MySharedPreferences
import uz.ilmiddin1701.test_game.databinding.FragmentRegisterBinding
import java.util.UUID

class RegisterFragment : Fragment() {
    private val binding by lazy { FragmentRegisterBinding.inflate(layoutInflater) }
    private lateinit var openAnim: Animation
    private lateinit var exitAnim: Animation
    private lateinit var showLogoAnim: Animation
    private lateinit var hideLogoAnim: Animation

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            MySharedPreferences.init(requireContext())
            if (MySharedPreferences.userId == "empty") {
                database = FirebaseDatabase.getInstance()
                reference = database.getReference("users")

                openAnim = AnimationUtils.loadAnimation(context, R.anim.open_anim)
                exitAnim = AnimationUtils.loadAnimation(context, R.anim.exit_anim)
                showLogoAnim = AnimationUtils.loadAnimation(context, R.anim.show_logo_anim1)
                hideLogoAnim = AnimationUtils.loadAnimation(context, R.anim.hide_logo_anim1)
                constraint1.startAnimation(openAnim)
                appLogo.startAnimation(showLogoAnim)

                btnRegister.setOnClickListener {
                    if (edtFirstName.text.toString().isNotBlank() && edtLastName.text.toString().isNotBlank() && edtUserName.text.toString().isNotBlank() && edtPassword.text.toString().isNotBlank()) {
                        val userId = reference.push().key!!
                        val user = User(userId, edtFirstName.text.toString(), edtLastName.text.toString(), edtUserName.text.toString(), edtPassword.text.toString(), null)
                        reference.child(userId).setValue(user)
                        MySharedPreferences.userId = userId
                        findNavController().popBackStack()
                        findNavController().navigate(R.id.containerFragment)
                    } else {
                        Toast.makeText(context, "Data is not fully entered!", Toast.LENGTH_SHORT).show()
                    }
                }

                tvLogin.setOnClickListener {
                    constraint1.startAnimation(exitAnim)
                    appLogo.startAnimation(hideLogoAnim)
                    exitAnim.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(p0: Animation?) {}
                        override fun onAnimationEnd(p0: Animation?) {
                            findNavController().popBackStack()
                            findNavController().navigate(R.id.loginFragment)
                        }
                        override fun onAnimationRepeat(p0: Animation?) {}
                    })
                }
            } else {
                findNavController().popBackStack()
                findNavController().navigate(R.id.containerFragment)
            }
        }
        return binding.root
    }
}