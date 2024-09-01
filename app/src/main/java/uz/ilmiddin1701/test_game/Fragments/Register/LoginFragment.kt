package uz.ilmiddin1701.test_game.Fragments.Register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.ilmiddin1701.test_game.R
import uz.ilmiddin1701.test_game.Utils.MySharedPreferences
import uz.ilmiddin1701.test_game.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    private lateinit var onBackPressedCallBack: OnBackPressedCallback
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            MySharedPreferences.init(requireContext())
            if (MySharedPreferences.userId == "empty") {
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
                            override fun onAnimationStart(p0: Animation?) { }
                            override fun onAnimationEnd(p0: Animation?) {
                                findNavController().popBackStack()
                                findNavController().navigate(R.id.registerFragment)
                                onBackPressedCallBack.remove()
                            }
                            override fun onAnimationRepeat(p0: Animation?) { }
                        })
                    }
                }
                requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallBack)

                btnLogin.setOnClickListener {
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
            } else {
                findNavController().popBackStack()
                findNavController().navigate(R.id.containerFragment)
            }
        }
        return binding.root
    }
}