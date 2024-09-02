package uz.ilmiddin1701.test_game.Fragments.Register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import uz.ilmiddin1701.test_game.Models.TestLevelData
import uz.ilmiddin1701.test_game.Models.User
import uz.ilmiddin1701.test_game.R
import uz.ilmiddin1701.test_game.Utils.MySharedPreferences
import uz.ilmiddin1701.test_game.databinding.FragmentRegisterBinding
import java.util.UUID

class RegisterFragment : Fragment() {
    private val binding by lazy { FragmentRegisterBinding.inflate(layoutInflater) }
    private lateinit var openAnim: Animation
    private lateinit var exitAnim: Animation
    private lateinit var showLogoAnim: Animation
    private lateinit var hideLogoAnim: Animation

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            MySharedPreferences.init(requireContext())
            if (MySharedPreferences.userId == "empty") {
                database = FirebaseDatabase.getInstance()
                reference = database.getReference("users")

                openAnim = AnimationUtils.loadAnimation(context, R.anim.open_anim)
                exitAnim = AnimationUtils.loadAnimation(context, R.anim.exit_anim)
                showLogoAnim = AnimationUtils.loadAnimation(context, R.anim.show_logo_anim1)
                hideLogoAnim = AnimationUtils.loadAnimation(context, R.anim.hide_logo_anim1)
                constraint1.startAnimation(openAnim)
                appLogo.startAnimation(showLogoAnim)

                btnRegister.setOnClickListener {
                    if (edtFirstName.text.toString().isNotBlank() && edtLastName.text.toString().isNotBlank() && edtUserName.text.toString().isNotBlank() && edtPassword.text.toString().isNotBlank()) {
                        val userId = reference.push().key!!
                        val user = User(userId, edtFirstName.text.toString(), edtLastName.text.toString(), edtUserName.text.toString(), edtPassword.text.toString(), null)
                        reference.child(userId).setValue(user)
                        MySharedPreferences.userId = userId

                        addTestLevelData(userId)

                        findNavController().popBackStack()
                        findNavController().navigate(R.id.containerFragment)
                    } else {
                        Toast.makeText(context, "Data is not fully entered!", Toast.LENGTH_SHORT).show()
                    }
                }

                tvLogin.setOnClickListener {
                    constraint1.startAnimation(exitAnim)
                    appLogo.startAnimation(hideLogoAnim)
                    exitAnim.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(p0: Animation?) {}
                        override fun onAnimationEnd(p0: Animation?) {
                            findNavController().popBackStack()
                            findNavController().navigate(R.id.loginFragment)
                        }
                        override fun onAnimationRepeat(p0: Animation?) {}
                    })
                }
            } else {
                findNavController().popBackStack()
                findNavController().navigate(R.id.containerFragment)
            }
        }
        return binding.root
    }

    private fun addTestLevelData(userId: String) {
        //Tests 20
        val tests201 = TestLevelData(1, 0, 0, 20, 0, "0")
        val tests202 = TestLevelData(2, 0, 0, 20, 0, "0")
        val tests203 = TestLevelData(3, 0, 0, 20, 0, "0")
        val tests204 = TestLevelData(4, 0, 0, 20, 0, "0")
        val tests205 = TestLevelData(5, 0, 0, 20, 0, "0")
        val tests206 = TestLevelData(6, 0, 0, 20, 0, "0")
        val tests207 = TestLevelData(7, 0, 0, 20, 0, "0")
        val tests208 = TestLevelData(8, 0, 0, 20, 0, "0")
        val tests209 = TestLevelData(9, 0, 0, 20, 0, "0")
        val tests2010 = TestLevelData(10, 0, 0, 20, 0, "0")
        val tests2011 = TestLevelData(11, 0, 0, 20, 0, "0")
        val tests2012 = TestLevelData(12, 0, 0, 20, 0, "0")
        val tests2013 = TestLevelData(13, 0, 0, 20, 0, "0")
        val tests2014 = TestLevelData(14, 0, 0, 20, 0, "0")
        val tests2015 = TestLevelData(15, 0, 0, 20, 0, "0")
        val tests2016 = TestLevelData(16, 0, 0, 20, 0, "0")
        val tests2017 = TestLevelData(17, 0, 0, 20, 0, "0")
        val tests2018 = TestLevelData(18, 0, 0, 20, 0, "0")
        val tests2019 = TestLevelData(19, 0, 0, 20, 0, "0")
        val tests2020 = TestLevelData(20, 0, 0, 20, 0, "0")

        reference.child(userId).child("tests20").child("test1").setValue(tests201)
        reference.child(userId).child("tests20").child("test2").setValue(tests202)
        reference.child(userId).child("tests20").child("test3").setValue(tests203)
        reference.child(userId).child("tests20").child("test4").setValue(tests204)
        reference.child(userId).child("tests20").child("test5").setValue(tests205)
        reference.child(userId).child("tests20").child("test6").setValue(tests206)
        reference.child(userId).child("tests20").child("test7").setValue(tests207)
        reference.child(userId).child("tests20").child("test8").setValue(tests208)
        reference.child(userId).child("tests20").child("test9").setValue(tests209)
        reference.child(userId).child("tests20").child("test10").setValue(tests2010)
        reference.child(userId).child("tests20").child("test11").setValue(tests2011)
        reference.child(userId).child("tests20").child("test12").setValue(tests2012)
        reference.child(userId).child("tests20").child("test13").setValue(tests2013)
        reference.child(userId).child("tests20").child("test14").setValue(tests2014)
        reference.child(userId).child("tests20").child("test15").setValue(tests2015)
        reference.child(userId).child("tests20").child("test16").setValue(tests2016)
        reference.child(userId).child("tests20").child("test17").setValue(tests2017)
        reference.child(userId).child("tests20").child("test18").setValue(tests2018)
        reference.child(userId).child("tests20").child("test19").setValue(tests2019)
        reference.child(userId).child("tests20").child("test20").setValue(tests2020)

        //Tests 30
        val tests301 = TestLevelData(1, 0, 0, 30, 0, "0")
        val tests302 = TestLevelData(2, 0, 0, 30, 0, "0")
        val tests303 = TestLevelData(3, 0, 0, 30, 0, "0")
        val tests304 = TestLevelData(4, 0, 0, 30, 0, "0")
        val tests305 = TestLevelData(5, 0, 0, 30, 0, "0")
        val tests306 = TestLevelData(6, 0, 0, 30, 0, "0")
        val tests307 = TestLevelData(7, 0, 0, 30, 0, "0")
        val tests308 = TestLevelData(8, 0, 0, 30, 0, "0")
        val tests309 = TestLevelData(9, 0, 0, 30, 0, "0")
        val tests3010 = TestLevelData(10, 0, 0, 30, 0, "0")
        val tests3011 = TestLevelData(11, 0, 0, 30, 0, "0")
        val tests3012 = TestLevelData(12, 0, 0, 30, 0, "0")
        val tests3013 = TestLevelData(13, 0, 0, 30, 0, "0")
        val tests3014 = TestLevelData(14, 0, 0, 30, 0, "0")
        val tests3015 = TestLevelData(15, 0, 0, 30, 0, "0")
        val tests3016 = TestLevelData(16, 0, 0, 30, 0, "0")
        val tests3017 = TestLevelData(17, 0, 0, 30, 0, "0")
        val tests3018 = TestLevelData(18, 0, 0, 30, 0, "0")
        val tests3019 = TestLevelData(19, 0, 0, 30, 0, "0")
        val tests3020 = TestLevelData(20, 0, 0, 30, 0, "0")

        reference.child(userId).child("tests30").child("test1").setValue(tests301)
        reference.child(userId).child("tests30").child("test2").setValue(tests302)
        reference.child(userId).child("tests30").child("test3").setValue(tests303)
        reference.child(userId).child("tests30").child("test4").setValue(tests304)
        reference.child(userId).child("tests30").child("test5").setValue(tests305)
        reference.child(userId).child("tests30").child("test6").setValue(tests306)
        reference.child(userId).child("tests30").child("test7").setValue(tests307)
        reference.child(userId).child("tests30").child("test8").setValue(tests308)
        reference.child(userId).child("tests30").child("test9").setValue(tests309)
        reference.child(userId).child("tests30").child("test10").setValue(tests3010)
        reference.child(userId).child("tests30").child("test11").setValue(tests3011)
        reference.child(userId).child("tests30").child("test12").setValue(tests3012)
        reference.child(userId).child("tests30").child("test13").setValue(tests3013)
        reference.child(userId).child("tests30").child("test14").setValue(tests3014)
        reference.child(userId).child("tests30").child("test15").setValue(tests3015)
        reference.child(userId).child("tests30").child("test16").setValue(tests3016)
        reference.child(userId).child("tests30").child("test17").setValue(tests3017)
        reference.child(userId).child("tests30").child("test18").setValue(tests3018)
        reference.child(userId).child("tests30").child("test19").setValue(tests3019)
        reference.child(userId).child("tests30").child("test20").setValue(tests3020)

        //Tests 40
        val tests401 = TestLevelData(1, 0, 0, 40, 0, "0")
        val tests402 = TestLevelData(2, 0, 0, 40, 0, "0")
        val tests403 = TestLevelData(3, 0, 0, 40, 0, "0")
        val tests404 = TestLevelData(4, 0, 0, 40, 0, "0")
        val tests405 = TestLevelData(5, 0, 0, 40, 0, "0")
        val tests406 = TestLevelData(6, 0, 0, 40, 0, "0")
        val tests407 = TestLevelData(7, 0, 0, 40, 0, "0")
        val tests408 = TestLevelData(8, 0, 0, 40, 0, "0")
        val tests409 = TestLevelData(9, 0, 0, 40, 0, "0")
        val tests4010 = TestLevelData(10, 0, 0, 40, 0, "0")
        val tests4011 = TestLevelData(11, 0, 0, 40, 0, "0")
        val tests4012 = TestLevelData(12, 0, 0, 40, 0, "0")
        val tests4013 = TestLevelData(13, 0, 0, 40, 0, "0")
        val tests4014 = TestLevelData(14, 0, 0, 40, 0, "0")
        val tests4015 = TestLevelData(15, 0, 0, 40, 0, "0")
        val tests4016 = TestLevelData(16, 0, 0, 40, 0, "0")
        val tests4017 = TestLevelData(17, 0, 0, 40, 0, "0")
        val tests4018 = TestLevelData(18, 0, 0, 40, 0, "0")
        val tests4019 = TestLevelData(19, 0, 0, 40, 0, "0")
        val tests4020 = TestLevelData(20, 0, 0, 40, 0, "0")

        reference.child(userId).child("tests40").child("test1").setValue(tests401)
        reference.child(userId).child("tests40").child("test2").setValue(tests402)
        reference.child(userId).child("tests40").child("test3").setValue(tests403)
        reference.child(userId).child("tests40").child("test4").setValue(tests404)
        reference.child(userId).child("tests40").child("test5").setValue(tests405)
        reference.child(userId).child("tests40").child("test6").setValue(tests406)
        reference.child(userId).child("tests40").child("test7").setValue(tests407)
        reference.child(userId).child("tests40").child("test8").setValue(tests408)
        reference.child(userId).child("tests40").child("test9").setValue(tests409)
        reference.child(userId).child("tests40").child("test10").setValue(tests4010)
        reference.child(userId).child("tests40").child("test11").setValue(tests4011)
        reference.child(userId).child("tests40").child("test12").setValue(tests4012)
        reference.child(userId).child("tests40").child("test13").setValue(tests4013)
        reference.child(userId).child("tests40").child("test14").setValue(tests4014)
        reference.child(userId).child("tests40").child("test15").setValue(tests4015)
        reference.child(userId).child("tests40").child("test16").setValue(tests4016)
        reference.child(userId).child("tests40").child("test17").setValue(tests4017)
        reference.child(userId).child("tests40").child("test18").setValue(tests4018)
        reference.child(userId).child("tests40").child("test19").setValue(tests4019)
        reference.child(userId).child("tests40").child("test20").setValue(tests4020)

        //Tests 50
        val tests501 = TestLevelData(1, 0, 0, 50, 0, "0")
        val tests502 = TestLevelData(2, 0, 0, 50, 0, "0")
        val tests503 = TestLevelData(3, 0, 0, 50, 0, "0")
        val tests504 = TestLevelData(4, 0, 0, 50, 0, "0")
        val tests505 = TestLevelData(5, 0, 0, 50, 0, "0")
        val tests506 = TestLevelData(6, 0, 0, 50, 0, "0")
        val tests507 = TestLevelData(7, 0, 0, 50, 0, "0")
        val tests508 = TestLevelData(8, 0, 0, 50, 0, "0")
        val tests509 = TestLevelData(9, 0, 0, 50, 0, "0")
        val tests5010 = TestLevelData(10, 0, 0, 50, 0, "0")
        val tests5011 = TestLevelData(11, 0, 0, 50, 0, "0")
        val tests5012 = TestLevelData(12, 0, 0, 50, 0, "0")
        val tests5013 = TestLevelData(13, 0, 0, 50, 0, "0")
        val tests5014 = TestLevelData(14, 0, 0, 50, 0, "0")
        val tests5015 = TestLevelData(15, 0, 0, 50, 0, "0")
        val tests5016 = TestLevelData(16, 0, 0, 50, 0, "0")
        val tests5017 = TestLevelData(17, 0, 0, 50, 0, "0")
        val tests5018 = TestLevelData(18, 0, 0, 50, 0, "0")
        val tests5019 = TestLevelData(19, 0, 0, 50, 0, "0")
        val tests5020 = TestLevelData(20, 0, 0, 60, 0, "0")

        reference.child(userId).child("tests50").child("test1").setValue(tests501)
        reference.child(userId).child("tests50").child("test2").setValue(tests502)
        reference.child(userId).child("tests50").child("test3").setValue(tests503)
        reference.child(userId).child("tests50").child("test4").setValue(tests504)
        reference.child(userId).child("tests50").child("test5").setValue(tests505)
        reference.child(userId).child("tests50").child("test6").setValue(tests506)
        reference.child(userId).child("tests50").child("test7").setValue(tests507)
        reference.child(userId).child("tests50").child("test8").setValue(tests508)
        reference.child(userId).child("tests50").child("test9").setValue(tests509)
        reference.child(userId).child("tests50").child("test10").setValue(tests5010)
        reference.child(userId).child("tests50").child("test11").setValue(tests5011)
        reference.child(userId).child("tests50").child("test12").setValue(tests5012)
        reference.child(userId).child("tests50").child("test13").setValue(tests5013)
        reference.child(userId).child("tests50").child("test14").setValue(tests5014)
        reference.child(userId).child("tests50").child("test15").setValue(tests5015)
        reference.child(userId).child("tests50").child("test16").setValue(tests5016)
        reference.child(userId).child("tests50").child("test17").setValue(tests5017)
        reference.child(userId).child("tests50").child("test18").setValue(tests5018)
        reference.child(userId).child("tests50").child("test19").setValue(tests5019)
        reference.child(userId).child("tests50").child("test20").setValue(tests5020)
    }
}