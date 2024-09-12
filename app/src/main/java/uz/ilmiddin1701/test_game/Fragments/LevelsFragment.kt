package uz.ilmiddin1701.test_game.Fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import uz.ilmiddin1701.test_game.Adapters.LevelsAdapter
import uz.ilmiddin1701.test_game.Models.TestLevelData
import uz.ilmiddin1701.test_game.Models.User
import uz.ilmiddin1701.test_game.R
import uz.ilmiddin1701.test_game.Utils.MySharedPreferences
import uz.ilmiddin1701.test_game.databinding.FragmentLevelsBinding
import java.util.logging.Level

class LevelsFragment : Fragment() {
    private val binding by lazy { FragmentLevelsBinding.inflate(layoutInflater) }
    private lateinit var levelsAdapter: LevelsAdapter
    private lateinit var list: ArrayList<User>

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    private val tests = MutableLiveData<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            tvLevel1.isSelected = true
            tvLevel2.isSelected = true
            tvLevel3.isSelected = true
            list = ArrayList()

            firebaseDatabase = FirebaseDatabase.getInstance()
            reference = firebaseDatabase.getReference("users")

            tests.postValue(1)
            tests.observe(viewLifecycleOwner) {
                reference.addValueEventListener(object : ValueEventListener {
                    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
                    override fun onDataChange(snapshot: DataSnapshot) {
                        list.clear()
                        val children = snapshot.children
                        for (child in children) {
                            val user = child.getValue(User::class.java)
                            if (user != null) {
                                list.add(user)
                            }
                        }
                        when (it) {
                            1 -> {
                                val sortedUsers = list.sortedWith(compareByDescending<User> { test -> test.allTestsCompleted20 }
                                    .thenBy { time -> time.allTestsCompletedTime20 })
                                list.clear()
                                list.addAll(sortedUsers)
                                levelsAdapter = LevelsAdapter(20, list)
                                if (list.size == 1) {
                                    Picasso.get().load(list[0].userImage).into(userImage1)
                                    tvLevel1.text = "@" + list[0].userName
                                    level1.visibility = View.VISIBLE
                                } else if (list.size == 2) {
                                    Picasso.get().load(list[0].userImage).into(userImage1)
                                    Picasso.get().load(list[1].userImage).into(userImage2)
                                    tvLevel1.text = "@" + list[0].userName
                                    tvLevel2.text = "@" + list[1].userName
                                    level1.visibility = View.VISIBLE
                                    level2.visibility = View.VISIBLE
                                } else if (list.size > 3) {
                                    Picasso.get().load(list[0].userImage).into(userImage1)
                                    Picasso.get().load(list[1].userImage).into(userImage2)
                                    Picasso.get().load(list[2].userImage).into(userImage3)
                                    tvLevel1.text = "@" + list[0].userName
                                    tvLevel2.text = "@" + list[1].userName
                                    tvLevel3.text = "@" + list[2].userName
                                    level1.visibility = View.VISIBLE
                                    level2.visibility = View.VISIBLE
                                    level3.visibility = View.VISIBLE
                                }
                                rv.adapter = levelsAdapter
                            }
                            2 -> {
                                val sortedUsers = list.sortedWith(compareByDescending<User> { test -> test.allTestsCompleted30 }
                                    .thenBy { time -> time.allTestsCompletedTime30 })
                                list.clear()
                                list.addAll(sortedUsers)
                                levelsAdapter = LevelsAdapter(30, list)
                                if (list.size == 1) {
                                    Picasso.get().load(list[0].userImage).into(userImage1)
                                    tvLevel1.text = "@" + list[0].userName
                                    level1.visibility = View.VISIBLE
                                } else if (list.size == 2) {
                                    Picasso.get().load(list[0].userImage).into(userImage1)
                                    Picasso.get().load(list[1].userImage).into(userImage2)
                                    tvLevel1.text = "@" + list[0].userName
                                    tvLevel2.text = "@" + list[1].userName
                                    level1.visibility = View.VISIBLE
                                    level2.visibility = View.VISIBLE
                                } else if (list.size > 3) {
                                    Picasso.get().load(list[0].userImage).into(userImage1)
                                    Picasso.get().load(list[1].userImage).into(userImage2)
                                    Picasso.get().load(list[2].userImage).into(userImage3)
                                    tvLevel1.text = "@" + list[0].userName
                                    tvLevel2.text = "@" + list[1].userName
                                    tvLevel3.text = "@" + list[2].userName
                                    level1.visibility = View.VISIBLE
                                    level2.visibility = View.VISIBLE
                                    level3.visibility = View.VISIBLE
                                }
                                rv.adapter = levelsAdapter
                            }
                            3 -> {
                                val sortedUsers = list.sortedWith(compareByDescending<User> { test -> test.allTestsCompleted40 }
                                    .thenBy { time -> time.allTestsCompletedTime40 })
                                list.clear()
                                list.addAll(sortedUsers)
                                levelsAdapter = LevelsAdapter(40, list)
                                if (list.size == 1) {
                                    Picasso.get().load(list[0].userImage).into(userImage1)
                                    tvLevel1.text = "@" + list[0].userName
                                    level1.visibility = View.VISIBLE
                                } else if (list.size == 2) {
                                    Picasso.get().load(list[0].userImage).into(userImage1)
                                    Picasso.get().load(list[1].userImage).into(userImage2)
                                    tvLevel1.text = "@" + list[0].userName
                                    tvLevel2.text = "@" + list[1].userName
                                    level1.visibility = View.VISIBLE
                                    level2.visibility = View.VISIBLE
                                } else if (list.size > 3) {
                                    Picasso.get().load(list[0].userImage).into(userImage1)
                                    Picasso.get().load(list[1].userImage).into(userImage2)
                                    Picasso.get().load(list[2].userImage).into(userImage3)
                                    tvLevel1.text = "@" + list[0].userName
                                    tvLevel2.text = "@" + list[1].userName
                                    tvLevel3.text = "@" + list[2].userName
                                    level1.visibility = View.VISIBLE
                                    level2.visibility = View.VISIBLE
                                    level3.visibility = View.VISIBLE
                                }
                                rv.adapter = levelsAdapter
                            }
                            4 -> {
                                val sortedUsers = list.sortedWith(compareByDescending<User> { test -> test.allTestsCompleted50 }
                                    .thenBy { time -> time.allTestsCompletedTime50 })
                                list.clear()
                                list.addAll(sortedUsers)
                                levelsAdapter = LevelsAdapter(50, list)
                                if (list.size == 1) {
                                    Picasso.get().load(list[0].userImage).into(userImage1)
                                    tvLevel1.text = "@" + list[0].userName
                                    level1.visibility = View.VISIBLE
                                } else if (list.size == 2) {
                                    Picasso.get().load(list[0].userImage).into(userImage1)
                                    Picasso.get().load(list[1].userImage).into(userImage2)
                                    tvLevel1.text = "@" + list[0].userName
                                    tvLevel2.text = "@" + list[1].userName
                                    level1.visibility = View.VISIBLE
                                    level2.visibility = View.VISIBLE
                                } else if (list.size > 3) {
                                    Picasso.get().load(list[0].userImage).into(userImage1)
                                    Picasso.get().load(list[1].userImage).into(userImage2)
                                    Picasso.get().load(list[2].userImage).into(userImage3)
                                    tvLevel1.text = "@" + list[0].userName
                                    tvLevel2.text = "@" + list[1].userName
                                    tvLevel3.text = "@" + list[2].userName
                                    level1.visibility = View.VISIBLE
                                    level2.visibility = View.VISIBLE
                                    level3.visibility = View.VISIBLE
                                }
                                rv.adapter = levelsAdapter
                            }
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                    }
                })
            }
            btn20.setOnClickListener {
                tests.postValue(1)
                updateButtonStyles(btn20, btn30, btn40, btn50)
            }
            btn30.setOnClickListener {
                tests.postValue(2)
                updateButtonStyles(btn30, btn20, btn40, btn50)
            }
            btn40.setOnClickListener {
                tests.postValue(3)
                updateButtonStyles(btn40, btn20, btn30, btn50)
            }
            btn50.setOnClickListener {
                tests.postValue(4)
                updateButtonStyles(btn50, btn20, btn30, btn40)
            }
        }
        return binding.root
    }

    //Change button style
    private fun updateButtonStyles(selectedButton: TextView, vararg otherButtons: TextView) {
        selectedButton.setTextColor(Color.parseColor("#314C70"))
        selectedButton.setBackgroundResource(R.drawable.selected1_style)

        for (button in otherButtons) {
            button.setTextColor(Color.WHITE)
            button.setBackgroundResource(R.drawable.unselected1_style)
        }
    }
}