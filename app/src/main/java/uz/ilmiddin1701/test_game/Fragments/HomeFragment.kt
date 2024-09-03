package uz.ilmiddin1701.test_game.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.ilmiddin1701.test_game.Adapters.HomeRvAdapter
import uz.ilmiddin1701.test_game.Models.TestLevelData
import uz.ilmiddin1701.test_game.Models.User
import uz.ilmiddin1701.test_game.R
import uz.ilmiddin1701.test_game.Utils.MySharedPreferences
import uz.ilmiddin1701.test_game.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), HomeRvAdapter.RvAction {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var homeRvAdapter: HomeRvAdapter
    private lateinit var list: ArrayList<TestLevelData>
    var n = 1
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        MySharedPreferences.init(requireContext())
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("users")
        list = ArrayList()
        binding.apply {
            val tests = MutableLiveData<Int>()
            tests.postValue(1)
            homeRvAdapter = HomeRvAdapter(this@HomeFragment, list)

            //Get User
            reference.addValueEventListener(object : ValueEventListener {
                @SuppressLint("SetTextI18n")
                override fun onDataChange(snapshot: DataSnapshot) {
                    val children = snapshot.children
                    for (child in children) {
                        val user = child.getValue(User::class.java)
                        if (user != null && user.id == MySharedPreferences.userId) {
                            tvName.text = "${user.firstName} ${user.lastName}"
                            tvUserName.text = "@${user.userName}"
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                }
            })

            //Get Tests
            tests.observe(viewLifecycleOwner) {
                n = it
                reference.child(MySharedPreferences.userId).child("tests")
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            list.clear()
                            val children = snapshot.children
                            for (child in children) {
                                val testLevelData = child.getValue(TestLevelData::class.java)
                                if (testLevelData != null) {
                                    if (it == 1 && testLevelData.tests == 20) {
                                        list.add(testLevelData)
                                        imgSelected1.visibility = View.VISIBLE
                                        relative11.setBackgroundResource(R.drawable.selected_style)
                                        tv20.setTextColor(Color.parseColor("#314C70"))
                                        tvTest1.setTextColor(Color.parseColor("#314C70"))

                                        imgSelected2.visibility = View.INVISIBLE
                                        tv30.setTextColor(Color.parseColor("#767676"))
                                        tvTest2.setTextColor(Color.parseColor("#767676"))
                                        relative22.setBackgroundResource(R.drawable.unselected_style)

                                        imgSelected3.visibility = View.INVISIBLE
                                        tv40.setTextColor(Color.parseColor("#767676"))
                                        tvTest3.setTextColor(Color.parseColor("#767676"))
                                        relative33.setBackgroundResource(R.drawable.unselected_style)

                                        imgSelected4.visibility = View.INVISIBLE
                                        tv50.setTextColor(Color.parseColor("#767676"))
                                        tvTest4.setTextColor(Color.parseColor("#767676"))
                                        relative44.setBackgroundResource(R.drawable.unselected_style)
                                    }
                                    if (it == 2 && testLevelData.tests == 30) {
                                        list.add(testLevelData)
                                    }
                                    if (it == 3 && testLevelData.tests == 40) {
                                        list.add(testLevelData)
                                    }
                                    if (it == 4 && testLevelData.tests == 50) {
                                        list.add(testLevelData)
                                    }
                                }
                            }
                            homeRvAdapter.notifyDataSetChanged()
                            rv.adapter = homeRvAdapter
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                        }
                    })
            }

            /*  CardView dynamic change colors
            val strokeWidth3 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, resources.displayMetrics).toInt()
            val strokeWidth1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, resources.displayMetrics).toInt()
            val elevation10 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, resources.displayMetrics)
            val elevation0 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, resources.displayMetrics)   */

            relative11.setOnClickListener {
                tests.postValue(1)
                imgSelected1.visibility = View.VISIBLE
                relative11.setBackgroundResource(R.drawable.selected_style)
                tv20.setTextColor(Color.parseColor("#314C70"))
                tvTest1.setTextColor(Color.parseColor("#314C70"))

                imgSelected2.visibility = View.INVISIBLE
                tv30.setTextColor(Color.parseColor("#767676"))
                tvTest2.setTextColor(Color.parseColor("#767676"))
                relative22.setBackgroundResource(R.drawable.unselected_style)

                imgSelected3.visibility = View.INVISIBLE
                tv40.setTextColor(Color.parseColor("#767676"))
                tvTest3.setTextColor(Color.parseColor("#767676"))
                relative33.setBackgroundResource(R.drawable.unselected_style)

                imgSelected4.visibility = View.INVISIBLE
                tv50.setTextColor(Color.parseColor("#767676"))
                tvTest4.setTextColor(Color.parseColor("#767676"))
                relative44.setBackgroundResource(R.drawable.unselected_style)
            }
            relative22.setOnClickListener {
                tests.postValue(2)
                imgSelected1.visibility = View.INVISIBLE
                tv20.setTextColor(Color.parseColor("#767676"))
                tvTest1.setTextColor(Color.parseColor("#767676"))
                relative11.setBackgroundResource(R.drawable.unselected_style)

                imgSelected2.visibility = View.VISIBLE
                tv30.setTextColor(Color.parseColor("#314C70"))
                relative22.setBackgroundResource(R.drawable.selected_style)
                tvTest2.setTextColor(Color.parseColor("#314C70"))

                imgSelected3.visibility = View.INVISIBLE
                tv40.setTextColor(Color.parseColor("#767676"))
                tvTest3.setTextColor(Color.parseColor("#767676"))
                relative33.setBackgroundResource(R.drawable.unselected_style)

                imgSelected4.visibility = View.INVISIBLE
                tv50.setTextColor(Color.parseColor("#767676"))
                tvTest4.setTextColor(Color.parseColor("#767676"))
                relative44.setBackgroundResource(R.drawable.unselected_style)
            }
            relative33.setOnClickListener {
                tests.postValue(3)
                imgSelected1.visibility = View.INVISIBLE
                tv20.setTextColor(Color.parseColor("#767676"))
                tvTest1.setTextColor(Color.parseColor("#767676"))
                relative11.setBackgroundResource(R.drawable.unselected_style)

                imgSelected2.visibility = View.INVISIBLE
                tv30.setTextColor(Color.parseColor("#767676"))
                tvTest2.setTextColor(Color.parseColor("#767676"))
                relative22.setBackgroundResource(R.drawable.unselected_style)

                imgSelected3.visibility = View.VISIBLE
                relative33.setBackgroundResource(R.drawable.selected_style)
                tv40.setTextColor(Color.parseColor("#314C70"))
                tvTest3.setTextColor(Color.parseColor("#314C70"))

                imgSelected4.visibility = View.INVISIBLE
                tv50.setTextColor(Color.parseColor("#767676"))
                tvTest4.setTextColor(Color.parseColor("#767676"))
                relative44.setBackgroundResource(R.drawable.unselected_style)
            }
            relative44.setOnClickListener {
                tests.postValue(4)
                imgSelected1.visibility = View.INVISIBLE
                tv20.setTextColor(Color.parseColor("#767676"))
                tvTest1.setTextColor(Color.parseColor("#767676"))
                relative11.setBackgroundResource(R.drawable.unselected_style)

                imgSelected2.visibility = View.INVISIBLE
                tv30.setTextColor(Color.parseColor("#767676"))
                tvTest2.setTextColor(Color.parseColor("#767676"))
                relative22.setBackgroundResource(R.drawable.unselected_style)

                imgSelected3.visibility = View.INVISIBLE
                tv40.setTextColor(Color.parseColor("#767676"))
                tvTest3.setTextColor(Color.parseColor("#767676"))
                relative33.setBackgroundResource(R.drawable.unselected_style)

                imgSelected4.visibility = View.VISIBLE
                tv50.setTextColor(Color.parseColor("#314C70"))
                relative44.setBackgroundResource(R.drawable.selected_style)
                tvTest4.setTextColor(Color.parseColor("#314C70"))
            }
        }
        return binding.root
    }

    override fun itemClick(testLevelData: TestLevelData) {
        when (n) {
            1 -> findNavController().navigate(R.id.test20Fragment, bundleOf("testLevelData" to testLevelData))
            2 -> findNavController().navigate(R.id.test30Fragment, bundleOf("testLevelData" to testLevelData))
            3 -> findNavController().navigate(R.id.test40Fragment, bundleOf("testLevelData" to testLevelData))
            4 -> findNavController().navigate(R.id.test50Fragment, bundleOf("testLevelData" to testLevelData))
        }
    }
}