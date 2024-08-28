package uz.ilmiddin1701.test_game.Fragments.Register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            tvRegister.setOnClickListener {
                findNavController().navigate(R.id.registerFragment)
            }
        }
        return binding.root
    }
}