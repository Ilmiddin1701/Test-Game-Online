package uz.ilmiddin1701.test_game.Fragments.Tests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.ilmiddin1701.test_game.R
import uz.ilmiddin1701.test_game.databinding.FragmentTest20Binding

class Test20Fragment : Fragment() {
    private val binding by lazy { FragmentTest20Binding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {

        }
        return binding.root
    }
}