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
import uz.ilmiddin1701.test_game.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private val binding by lazy { FragmentRegisterBinding.inflate(layoutInflater) }
    private lateinit var openAnim: Animation
    private lateinit var exitAnim: Animation
    private lateinit var showLogoAnim: Animation
    private lateinit var hideLogoAnim: Animation
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            openAnim = AnimationUtils.loadAnimation(context, R.anim.open_anim)
            exitAnim = AnimationUtils.loadAnimation(context, R.anim.exit_anim)
            showLogoAnim = AnimationUtils.loadAnimation(context, R.anim.show_logo_anim1)
            hideLogoAnim = AnimationUtils.loadAnimation(context, R.anim.hide_logo_anim1)
            constraint1.startAnimation(openAnim)
            appLogo.startAnimation(showLogoAnim)
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallBack)
        }
        return binding.root
    }

    private val onBackPressedCallBack = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            binding.constraint1.startAnimation(exitAnim)
            binding.appLogo.startAnimation(hideLogoAnim)
            exitAnim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) { }
                override fun onAnimationEnd(p0: Animation?) {
                    findNavController().popBackStack()
                }
                override fun onAnimationRepeat(p0: Animation?) { }
            })
        }
    }
}