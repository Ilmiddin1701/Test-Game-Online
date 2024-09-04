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
    private var totalTests = 0
    private var completedTest = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            val testLevelData = arguments?.getSerializable("testLevelData") as TestLevelData
            totalTests = testLevelData.tests!!

            btnNext.setOnClickListener {
                if (completedTest == 20) {
                    completedTest = 0
                    updateProgress()
//                    val layoutParams = binding.progressContainer.layoutParams
//                    layoutParams.width = completedTest
//                    binding.progressContainer.layoutParams = layoutParams
                } else {
                    updateProgress()
                }
            }
        }
        return binding.root
    }

    private fun updateProgress() {
        completedTest++
        val progressPercentage = completedTest.toFloat() / totalTests.toFloat()
        val newWidth = (binding.progressContainer.parent as View).width * progressPercentage
        val layoutParams = binding.progressContainer.layoutParams
        layoutParams.width = newWidth.toInt()
        binding.progressContainer.layoutParams = layoutParams
    }
}