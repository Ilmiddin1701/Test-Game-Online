package uz.ilmiddin1701.test_game.Fragments.Tests

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.ilmiddin1701.test_game.Models.TestLevelData
import uz.ilmiddin1701.test_game.R
import uz.ilmiddin1701.test_game.Utils.MyData
import uz.ilmiddin1701.test_game.databinding.FragmentTest20Binding
import java.util.Random

class Test20Fragment : Fragment() {
    private val binding by lazy { FragmentTest20Binding.inflate(layoutInflater) }
    private var totalTests = 0
    private var completedTest = 1

    var number1 = 0
    var number2 = 0
    var javob = 0
    var amal = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            MyData.liveData.postValue(false)
            val testLevelData = arguments?.getSerializable("testLevelData") as TestLevelData
            totalTests = testLevelData.tests!!
            random()

            btnNext.setOnClickListener {
                if (completedTest == 20) {
                    completedTest = 0
                    val layoutParams = binding.progressContainer.layoutParams
                    layoutParams.width = completedTest
                    binding.progressContainer.layoutParams = layoutParams
                } else {
                    updateProgress()
                    random()
                }
            }
        }
        return binding.root
    }

    private fun random() {
        number1 = Random().nextInt(20)
        number2 = Random().nextInt(20)
        amal = Random().nextInt(4)
        showDisplay()
    }

    @SuppressLint("SetTextI18n")
    private fun showDisplay() {
        when (amal) {
            0 -> {
                javob = number1 + number2
                binding.tvQuestion.text = "$number1 + $number2 ="
            }
            1 -> {
                javob = number1 - number2
                binding.tvQuestion.text = "$number1 - $number2 ="
            }
            2 -> {
                javob = number1 * number2
                binding.tvQuestion.text = "$number1 * $number2 ="
            }
            3 -> {
                if (number1 % number2 == 0) {
                    javob = number1 / number2
                    binding.tvQuestion.text = "$number1 / $number2 ="
                } else {
                    random()
                }
            }
        }
        binding.apply {
            val son = Random().nextInt(4)
            var javob1 = javob - 5
            var javob2 = javob + 12
            var javob3 = javob + 9
            when (son) {
                0 -> {
                    tvAnswerA.text = javob.toString()
                    tvAnswerB.text = javob1.toString()
                    tvAnswerC.text = javob2.toString()
                    tvAnswerD.text = javob3.toString()
                }
                1 -> {
                    tvAnswerB.text = javob.toString()
                    tvAnswerA.text = javob1.toString()
                    tvAnswerC.text = javob2.toString()
                    tvAnswerD.text = javob3.toString()
                }
                2 -> {
                    tvAnswerC.text = javob.toString()
                    tvAnswerB.text = javob1.toString()
                    tvAnswerA.text = javob2.toString()
                    tvAnswerD.text = javob3.toString()
                }
                3 -> {
                    tvAnswerD.text = javob.toString()
                    tvAnswerA.text = javob3.toString()
                    tvAnswerB.text = javob1.toString()
                    tvAnswerC.text = javob2.toString()
                }
            }
        }
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