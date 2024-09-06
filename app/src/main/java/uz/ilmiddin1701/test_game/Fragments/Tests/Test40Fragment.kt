package uz.ilmiddin1701.test_game.Fragments.Tests

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import uz.ilmiddin1701.test_game.Models.TestLevelData
import uz.ilmiddin1701.test_game.R
import uz.ilmiddin1701.test_game.Utils.MyData
import uz.ilmiddin1701.test_game.databinding.FragmentTest40Binding
import java.util.Random

class Test40Fragment : Fragment() {
    private val binding by lazy { FragmentTest40Binding.inflate(layoutInflater) }
    private var totalTests = 0
    private var completedTest = 1
    private var level = 0
    private var isChecked = false

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private var key = ""

    var number1 = 0
    var number2 = 0
    var javob = 0
    var amal = 0
    private var userInputAnswer = 0
    private var answerTrue = 0

    private lateinit var layoutParams: ViewGroup.LayoutParams
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            MyData.liveData.postValue(false)
            val testLevelData = arguments?.getSerializable("testLevelData") as TestLevelData
            totalTests = testLevelData.tests!!
            tvMaxTest.text = testLevelData.tests!!.toString()
            random()
            layoutParams = binding.progressContainer.layoutParams
            level = arguments?.getInt("keyPosition", 0)!!

            btnNext.setOnClickListener {

                if (completedTest == 40) {
                    completedTest = 0
                    updateProgress()
                    random()
                } else {
                    updateProgress()
                    random()
                }
            }
            answerA.setOnClickListener {
                if (!isChecked && tvAnswerA.text.toString().toInt() == javob){
                    userInputAnswer = tvAnswerA.text.toString().toInt()
                    isChecked = true
                    answerTrue++
                    forStyleLinear1.setBackgroundResource(R.drawable.progress_style)
                    forStyleTv1.setBackgroundResource(R.drawable.style_3)
                } else if (!isChecked && tvAnswerA.text.toString().toInt() != javob){
                    userInputAnswer = tvAnswerA.text.toString().toInt()
                    isChecked = true
                    forStyleLinear1.setBackgroundResource(R.drawable.red_style)
                    forStyleTv1.setBackgroundResource(R.drawable.style_3)
                }
            }
            answerB.setOnClickListener {
                if (!isChecked && tvAnswerB.text.toString().toInt() == javob){
                    userInputAnswer = tvAnswerB.text.toString().toInt()
                    isChecked = true
                    answerTrue++
                    forStyleLinear2.setBackgroundResource(R.drawable.progress_style)
                    forStyleTv2.setBackgroundResource(R.drawable.style_3)
                } else if (!isChecked && tvAnswerB.text.toString().toInt() != javob){
                    userInputAnswer = tvAnswerB.text.toString().toInt()
                    isChecked = true
                    forStyleLinear2.setBackgroundResource(R.drawable.red_style)
                    forStyleTv2.setBackgroundResource(R.drawable.style_3)
                }
            }
            answerC.setOnClickListener {
                if (!isChecked && tvAnswerC.text.toString().toInt() == javob){
                    userInputAnswer = tvAnswerC.text.toString().toInt()
                    isChecked = true
                    answerTrue++
                    forStyleLinear3.setBackgroundResource(R.drawable.progress_style)
                    forStyleTv3.setBackgroundResource(R.drawable.style_3)
                } else if (!isChecked && tvAnswerC.text.toString().toInt() != javob){
                    userInputAnswer = tvAnswerC.text.toString().toInt()
                    isChecked = true
                    forStyleLinear3.setBackgroundResource(R.drawable.red_style)
                    forStyleTv3.setBackgroundResource(R.drawable.style_3)
                }
            }
            answerD.setOnClickListener {
                if (!isChecked && tvAnswerD.text.toString().toInt() == javob){
                    userInputAnswer = tvAnswerD.text.toString().toInt()
                    isChecked = true
                    answerTrue++
                    forStyleLinear4.setBackgroundResource(R.drawable.progress_style)
                    forStyleTv4.setBackgroundResource(R.drawable.style_3)
                } else if (!isChecked && tvAnswerD.text.toString().toInt() != javob){
                    userInputAnswer = tvAnswerD.text.toString().toInt()
                    isChecked = true
                    forStyleLinear4.setBackgroundResource(R.drawable.red_style)
                    forStyleTv4.setBackgroundResource(R.drawable.style_3)
                }
            }
        }
        return binding.root
    }

    private fun random() {
        var s = 0
        when (level) {
            in 0..6 -> s = 30
            in 5..11 -> s = 60
            in 10..16 -> s = 90
            in 15..20 -> s = 120
        }
        number1 = Random().nextInt(s)
        number2 = Random().nextInt(s)
        amal = Random().nextInt(4)
        showDisplay()
    }

    @SuppressLint("SetTextI18n")
    private fun showDisplay() {
        binding.apply {
            if (number1 != 0 && number2 != 0) {
                when (amal) {
                    0 -> {
                        javob = number1 + number2
                        tvQuestion.text = "$number1 + $number2 ="
                    }
                    1 -> {
                        javob = number1 - number2
                        tvQuestion.text = "$number1 - $number2 ="
                    }
                    2 -> {
                        javob = number1 * number2
                        tvQuestion.text = "$number1 * $number2 ="
                    }
                    3 -> {
                        if (number1 % number2 == 0) {
                            javob = number1 / number2
                            tvQuestion.text = "$number1 / $number2 ="
                        } else {
                            random()
                        }
                    }
                }
            } else {
                random()
            }

            val son = Random().nextInt(4)
            val javob1 = javob - 5
            val javob2 = javob + 12
            val javob3 = javob + 9
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