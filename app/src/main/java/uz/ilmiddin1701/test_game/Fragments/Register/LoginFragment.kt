package uz.ilmiddin1701.test_game.Fragments.Register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.ilmiddin1701.test_game.R
import uz.ilmiddin1701.test_game.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            val openAnim = AnimationUtils.loadAnimation(context, R.anim.open_anim)
            val exitAnim = AnimationUtils.loadAnimation(context, R.anim.exit_anim)
            val showLogoAnim = AnimationUtils.loadAnimation(context, R.anim.show_logo_anim)
            val hideLogoAnim = AnimationUtils.loadAnimation(context, R.anim.hide_logo_anim)
            constraint1.startAnimation(openAnim)
            appLogo.startAnimation(showLogoAnim)
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
            tvRegister.setOnClickListener {
                constraint1.startAnimation(exitAnim)
                appLogo.startAnimation(hideLogoAnim)
                exitAnim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {}
                    override fun onAnimationEnd(p0: Animation?) {
                        findNavController().popBackStack()
                        findNavController().navigate(R.id.registerFragment)
                    }
                    override fun onAnimationRepeat(p0: Animation?) {}
                })
            }
        }
        return binding.root
    }
}