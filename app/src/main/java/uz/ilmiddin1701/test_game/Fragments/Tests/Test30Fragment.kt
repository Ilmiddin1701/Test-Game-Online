package uz.ilmiddin1701.test_game.Fragments.Tests

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.ilmiddin1701.test_game.Models.TestLevelData
import uz.ilmiddin1701.test_game.R
import uz.ilmiddin1701.test_game.Utils.MyData
import uz.ilmiddin1701.test_game.Utils.MySharedPreferences
import uz.ilmiddin1701.test_game.databinding.FragmentTest30Binding
import uz.ilmiddin1701.test_game.databinding.ItemEndGameDialogBinding
import java.util.Random

@Suppress("DEPRECATION")
class Test30Fragment : Fragment() {
    private val binding by lazy { FragmentTest30Binding.inflate(layoutInflater) }
    private var totalTests = 0
    private var completedTest = 1
    private var level = 0
    private var isChecked = false

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private var key = ""
    private var key1 = ""

    private var number1 = 0
    private var number2 = 0
    private var javob = 0
    private var amal = 0
    private var userInputAnswer = 0
    private var answerTrue = 0

    private var startTime = 0L
    private var timeBuffer = 0L
    private var updateTime = 0L
    private val handler = Handler()

    private lateinit var layoutParams: ViewGroup.LayoutParams
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            MyData.liveData.postValue(false)
            val testLevelData = arguments?.getSerializable("testLevelData") as TestLevelData
            val pos = arguments?.getInt("keyPosition")

            startTime = SystemClock.uptimeMillis()
            handler.post(runnable)

            var completedTest30 = 0
            var completedTestTime30 = 0

            MySharedPreferences.init(requireContext())
            firebaseDatabase = FirebaseDatabase.getInstance()
            reference = firebaseDatabase.getReference("users")
            val list = ArrayList<String>()
            reference.child(MySharedPreferences.userId).child("tests")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        list.clear()
                        snapshot.children.forEach { child ->
                            val value = child.getValue(TestLevelData::class.java)
                            if (value != null && value.tests == 30) {
                                list.add(child.key!!)
                            }
                        }
                        key = list[pos!!]
                        key1 = list[pos + 1]
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                    }
                })
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            totalTests = testLevelData.tests!!
            tvMaxTest.text = testLevelData.tests!!.toString()
            random()
            layoutParams = binding.progressContainer.layoutParams
            level = arguments?.getInt("keyPosition", 0)!!

            btnNext.setOnClickListener {
                if (isChecked) {
                    isChecked = false
                    if (completedTest == 30) {
                        tvCountTest.text = answerTrue.toString()
                        reference.child(MySharedPreferences.userId).child("tests")
                            .child(key).child("testCompleted").setValue(answerTrue)

                        reference.child(MySharedPreferences.userId).child("tests")
                            .child(key).child("testCompletedTime")
                            .setValue(updateTime.toString().toInt())

                        val dialog = AlertDialog.Builder(requireContext()).create()
                        val dialogItem = ItemEndGameDialogBinding.inflate(layoutInflater)
                        if (updateTime.toString().toInt() < 150000 && answerTrue == 30) {
                            reference.child(MySharedPreferences.userId).child("tests")
                                .child(key).child("stars").setValue(3)

                            reference.child(MySharedPreferences.userId).child("tests")
                                .child(key1).child("checked").setValue(true)
                            dialog.setCancelable(false)
                            dialogItem.tvContinue.setOnClickListener {
                                reference.child(MySharedPreferences.userId).child("tests")
                                    .addValueEventListener(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            list.clear()
                                            snapshot.children.forEach { child ->
                                                val value = child.getValue(TestLevelData::class.java)
                                                if (value != null && value.tests == 30) {
                                                    completedTest30 += value.testCompleted!!
                                                    completedTestTime30 += value.testCompletedTime!!.toInt()
                                                }
                                            }
                                            reference.child(MySharedPreferences.userId)
                                                .child("allTestsCompleted30").setValue(completedTest30)
                                            reference.child(MySharedPreferences.userId)
                                                .child("allTestsCompletedTime30").setValue(completedTestTime30)
                                        }
                                        override fun onCancelled(error: DatabaseError) {
                                            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                                        }
                                    })
                                findNavController().popBackStack()
                                dialog.cancel()
                            }
                            dialogItem.apply {
                                star1.setImageResource(R.drawable.ic_star_yellow)
                                star2.setImageResource(R.drawable.ic_star_yellow)
                                star3.setImageResource(R.drawable.ic_star_yellow)
                                tvCompletedTestsTime.text = tvTimer.text
                                tvCompletedTests.text = answerTrue.toString()
                            }
                            dialog.setView(dialogItem.root)
                            dialog.show()
                        } else if (updateTime in 150000..180000 && answerTrue == 30) {
                            reference.child(MySharedPreferences.userId).child("tests")
                                .child(key).child("stars").setValue(2)

                            reference.child(MySharedPreferences.userId).child("tests")
                                .child(key1).child("checked").setValue(true)
                            dialog.setCancelable(false)
                            dialogItem.tvContinue.setOnClickListener {
                                reference.child(MySharedPreferences.userId).child("tests")
                                    .addValueEventListener(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            list.clear()
                                            snapshot.children.forEach { child ->
                                                val value = child.getValue(TestLevelData::class.java)
                                                if (value != null && value.tests == 30) {
                                                    completedTest30 += value.testCompleted!!
                                                    completedTestTime30 += value.testCompletedTime!!.toInt()
                                                }
                                            }
                                            reference.child(MySharedPreferences.userId)
                                                .child("allTestsCompleted30").setValue(completedTest30)
                                            reference.child(MySharedPreferences.userId)
                                                .child("allTestsCompletedTime30").setValue(completedTestTime30)
                                        }
                                        override fun onCancelled(error: DatabaseError) {
                                            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                                        }
                                    })
                                findNavController().popBackStack()
                                dialog.cancel()
                            }
                            dialogItem.apply {
                                star1.setImageResource(R.drawable.ic_star_yellow)
                                star2.setImageResource(R.drawable.ic_star_yellow)
                                star3.setImageResource(R.drawable.ic_star_border)
                                tvCompletedTestsTime.text = tvTimer.text
                                tvCompletedTests.text = answerTrue.toString()
                            }
                            dialog.setView(dialogItem.root)
                            dialog.show()
                        } else if (updateTime in 180000..210000 && answerTrue == 30) {
                            reference.child(MySharedPreferences.userId).child("tests")
                                .child(key).child("stars").setValue(1)

                            reference.child(MySharedPreferences.userId).child("tests")
                                .child(key1).child("checked").setValue(true)
                            dialog.setCancelable(false)
                            dialogItem.tvContinue.setOnClickListener {
                                reference.child(MySharedPreferences.userId).child("tests")
                                    .addValueEventListener(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            list.clear()
                                            snapshot.children.forEach { child ->
                                                val value = child.getValue(TestLevelData::class.java)
                                                if (value != null && value.tests == 30) {
                                                    completedTest30 += value.testCompleted!!
                                                    completedTestTime30 += value.testCompletedTime!!.toInt()
                                                }
                                            }
                                            reference.child(MySharedPreferences.userId)
                                                .child("allTestsCompleted30").setValue(completedTest30)
                                            reference.child(MySharedPreferences.userId)
                                                .child("allTestsCompletedTime30").setValue(completedTestTime30)
                                        }
                                        override fun onCancelled(error: DatabaseError) {
                                            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                                        }
                                    })
                                findNavController().popBackStack()
                                dialog.cancel()
                            }
                            dialogItem.apply {
                                star1.setImageResource(R.drawable.ic_star_yellow)
                                star2.setImageResource(R.drawable.ic_star_border)
                                star3.setImageResource(R.drawable.ic_star_border)
                                tvCompletedTestsTime.text = tvTimer.text
                                tvCompletedTests.text = answerTrue.toString()
                            }
                            dialog.setView(dialogItem.root)
                            dialog.show()
                        } else {
                            reference.child(MySharedPreferences.userId).child("tests")
                                .child(key).child("stars").setValue(0)

                            reference.child(MySharedPreferences.userId).child("tests")
                                .child(key1).child("checked").setValue(false)
                            dialog.setCancelable(false)
                            dialogItem.tvContinue.setOnClickListener {
                                reference.child(MySharedPreferences.userId).child("tests")
                                    .addValueEventListener(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            list.clear()
                                            snapshot.children.forEach { child ->
                                                val value = child.getValue(TestLevelData::class.java)
                                                if (value != null && value.tests == 30) {
                                                    completedTest30 += value.testCompleted!!
                                                    completedTestTime30 += value.testCompletedTime!!.toInt()
                                                }
                                            }
                                            reference.child(MySharedPreferences.userId)
                                                .child("allTestsCompleted30").setValue(completedTest30)
                                            reference.child(MySharedPreferences.userId)
                                                .child("allTestsCompletedTime30").setValue(completedTestTime30)
                                        }
                                        override fun onCancelled(error: DatabaseError) {
                                            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                                        }
                                    })
                                findNavController().popBackStack()
                                dialog.cancel()
                            }
                            dialogItem.apply {
                                star1.setImageResource(R.drawable.ic_star_border)
                                star2.setImageResource(R.drawable.ic_star_border)
                                star3.setImageResource(R.drawable.ic_star_border)
                                tvCompletedTestsTime.text = tvTimer.text
                                tvCompletedTests.text = answerTrue.toString()
                            }
                            dialog.setView(dialogItem.root)
                            dialog.show()
                        }
                        handler.removeCallbacks(runnable)
                        tvTimer.text = "00 : 00 : 00"
                    } else {
                        updateProgress()
                        random()
                        tvCountTest.text = answerTrue.toString()
                        reference.child(MySharedPreferences.userId).child("tests")
                            .child(key).child("testCompleted").setValue(answerTrue)

                        reference.child(MySharedPreferences.userId).child("tests")
                            .child(key).child("testCompletedTime").setValue(updateTime)

                        forStyleLinear1.setBackgroundResource(R.drawable.style_2)
                        forStyleTv1.setBackgroundResource(R.drawable.style_1)
                        forStyleLinear2.setBackgroundResource(R.drawable.style_2)
                        forStyleTv2.setBackgroundResource(R.drawable.style_1)
                        forStyleLinear3.setBackgroundResource(R.drawable.style_2)
                        forStyleTv3.setBackgroundResource(R.drawable.style_1)
                        forStyleLinear4.setBackgroundResource(R.drawable.style_2)
                        forStyleTv4.setBackgroundResource(R.drawable.style_1)
                    }
                }
            }
            answerA.setOnClickListener {
                if (!isChecked && tvAnswerA.text.toString().toInt() == javob) {
                    userInputAnswer = tvAnswerA.text.toString().toInt()
                    isChecked = true
                    answerTrue++
                    forStyleLinear1.setBackgroundResource(R.drawable.progress_style)
                    forStyleTv1.setBackgroundResource(R.drawable.style_3)
                } else if (!isChecked && tvAnswerA.text.toString().toInt() != javob) {
                    userInputAnswer = tvAnswerA.text.toString().toInt()
                    isChecked = true
                    forStyleLinear1.setBackgroundResource(R.drawable.red_style)
                    forStyleTv1.setBackgroundResource(R.drawable.style_3)
                }
            }
            answerB.setOnClickListener {
                if (!isChecked && tvAnswerB.text.toString().toInt() == javob) {
                    userInputAnswer = tvAnswerB.text.toString().toInt()
                    isChecked = true
                    answerTrue++
                    forStyleLinear2.setBackgroundResource(R.drawable.progress_style)
                    forStyleTv2.setBackgroundResource(R.drawable.style_3)
                } else if (!isChecked && tvAnswerB.text.toString().toInt() != javob) {
                    userInputAnswer = tvAnswerB.text.toString().toInt()
                    isChecked = true
                    forStyleLinear2.setBackgroundResource(R.drawable.red_style)
                    forStyleTv2.setBackgroundResource(R.drawable.style_3)
                }
            }
            answerC.setOnClickListener {
                if (!isChecked && tvAnswerC.text.toString().toInt() == javob) {
                    userInputAnswer = tvAnswerC.text.toString().toInt()
                    isChecked = true
                    answerTrue++
                    forStyleLinear3.setBackgroundResource(R.drawable.progress_style)
                    forStyleTv3.setBackgroundResource(R.drawable.style_3)
                } else if (!isChecked && tvAnswerC.text.toString().toInt() != javob) {
                    userInputAnswer = tvAnswerC.text.toString().toInt()
                    isChecked = true
                    forStyleLinear3.setBackgroundResource(R.drawable.red_style)
                    forStyleTv3.setBackgroundResource(R.drawable.style_3)
                }
            }
            answerD.setOnClickListener {
                if (!isChecked && tvAnswerD.text.toString().toInt() == javob) {
                    userInputAnswer = tvAnswerD.text.toString().toInt()
                    isChecked = true
                    answerTrue++
                    forStyleLinear4.setBackgroundResource(R.drawable.progress_style)
                    forStyleTv4.setBackgroundResource(R.drawable.style_3)
                } else if (!isChecked && tvAnswerD.text.toString().toInt() != javob) {
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
            in 0..6 -> s = 125
            in 5..11 -> s = 250
            in 10..16 -> s = 500
            in 15..20 -> s = 1000
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
        layoutParams.width = newWidth.toInt()
        binding.progressContainer.layoutParams = layoutParams
    }

    private val runnable = object : Runnable {
        @SuppressLint("DefaultLocale")
        override fun run() {
            updateTime = SystemClock.uptimeMillis() - startTime
            val updatedTime = timeBuffer + updateTime
            val seconds = (updatedTime / 1000).toInt()
            val minutes = (seconds / 60)
            val hours = (minutes / 60)

            binding.tvTimer.text = String.format("%02d : %02d : %02d", hours, minutes, seconds % 60)
            handler.postDelayed(this, 10)
        }
    }
}