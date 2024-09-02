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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.ilmiddin1701.test_game.Models.User
import uz.ilmiddin1701.test_game.R
import uz.ilmiddin1701.test_game.Utils.MySharedPreferences
import uz.ilmiddin1701.test_game.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    private lateinit var onBackPressedCallBack: OnBackPressedCallback

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            MySharedPreferences.init(requireContext())
            database = FirebaseDatabase.getInstance()
            reference = database.getReference("users")

            val openAnim = AnimationUtils.loadAnimation(context, R.anim.open_anim)
            val exitAnim = AnimationUtils.loadAnimation(context, R.anim.exit_anim)
            val showLogoAnim = AnimationUtils.loadAnimation(context, R.anim.show_logo_anim)
            val hideLogoAnim = AnimationUtils.loadAnimation(context, R.anim.hide_logo_anim)
            constraint1.startAnimation(openAnim)
            appLogo.startAnimation(showLogoAnim)

            onBackPressedCallBack = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    binding.constraint1.startAnimation(exitAnim)
                    binding.appLogo.startAnimation(hideLogoAnim)
                    exitAnim.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(p0: Animation?) {}
                        override fun onAnimationEnd(p0: Animation?) {
                            findNavController().popBackStack()
                            findNavController().navigate(R.id.registerFragment)
                            onBackPressedCallBack.remove()
                        }

                        override fun onAnimationRepeat(p0: Animation?) {}
                    })
                }
            }
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                onBackPressedCallBack
            )

            btnLogin.setOnClickListener {
                if (edtUserName.text!!.isNotBlank() && edtPassword.text!!.isNotBlank()) {
                    val userName = edtUserName.text.toString()
                    val password = edtPassword.text.toString()
                    reference.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val children = snapshot.children
                            for (child in children) {
                                val user = child.getValue(User::class.java)
                                if (user != null && user.userName == userName && user.password == password) {
                                    MySharedPreferences.userId = user.id!!
                                    constraint1.startAnimation(exitAnim)
                                    appLogo.startAnimation(hideLogoAnim)
                                    exitAnim.setAnimationListener(object : Animation.AnimationListener {
                                        override fun onAnimationStart(p0: Animation?) {}
                                        override fun onAnimationEnd(p0: Animation?) {
                                            findNavController().popBackStack()
                                            findNavController().navigate(R.id.containerFragment)
                                        }
                                        override fun onAnimationRepeat(p0: Animation?) {}
                                    })
                                }
                            }
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                        }
                    })
                } else {
                    Toast.makeText(context, "Data is not fully entered!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return binding.root
    }
}