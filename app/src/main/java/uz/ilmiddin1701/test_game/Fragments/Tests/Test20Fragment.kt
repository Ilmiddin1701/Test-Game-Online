package uz.ilmiddin1701.test_game.Fragments.Tests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.ilmiddin1701.test_game.Models.TestLevelData
import uz.ilmiddin1701.test_game.R
import uz.ilmiddin1701.test_game.databinding.FragmentTest20Binding

class Test20Fragment : Fragment() {
    private val binding by lazy { FragmentTest20Binding.inflate(layoutInflater) }
    private var totalQuestion = 0
    private var currentProgress = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            val testLevelData = arguments?.getSerializable("testLevelData") as TestLevelData
            totalQuestion = testLevelData.tests!!
            progressBar.max = totalQuestion
            updateProgress()
        }
        return binding.root
    }
    private fun updateProgress() {
        binding.progressBar.progress = currentProgress
    }
}