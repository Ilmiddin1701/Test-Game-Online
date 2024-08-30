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
            val openAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.open_anim)
            val exitAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.exit_anim)
            constraint1.startAnimation(openAnim)
            tvRegister.setOnClickListener {
                constraint1.startAnimation(exitAnim)
                exitAnim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {}
                    override fun onAnimationEnd(p0: Animation?) {
                        findNavController().navigate(R.id.registerFragment)
                    }
                    override fun onAnimationRepeat(p0: Animation?) {}
                })
            }
        }
        return binding.root
    }
}