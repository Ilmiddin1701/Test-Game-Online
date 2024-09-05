package uz.ilmiddin1701.test_game.Fragments.Tests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.ilmiddin1701.test_game.R
import uz.ilmiddin1701.test_game.Utils.MyData
import uz.ilmiddin1701.test_game.databinding.FragmentTest30Binding

class Test30Fragment : Fragment() {
    private val binding by lazy { FragmentTest30Binding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            MyData.liveData.postValue(false)

        }
        return binding.root
    }
}