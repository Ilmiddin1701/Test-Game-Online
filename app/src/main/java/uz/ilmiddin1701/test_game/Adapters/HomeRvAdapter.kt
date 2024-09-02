package uz.ilmiddin1701.test_game.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ilmiddin1701.test_game.Models.TestLevelData
import uz.ilmiddin1701.test_game.R
import uz.ilmiddin1701.test_game.databinding.ItemLevelBinding

class HomeRvAdapter(var rvAction: RvAction, private var list: ArrayList<TestLevelData>) : RecyclerView.Adapter<HomeRvAdapter.Vh>() {

    inner class Vh(var itemRvBinding: ItemLevelBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(testLevelData: TestLevelData, position: Int) {
            itemRvBinding.apply {
                if (testLevelData.isChecked) {
                    val level = position + 1
                    itemVisible.visibility = View.GONE
                    tvLevel.text = level.toString()
                    root.setOnClickListener {
                        rvAction.itemClick(testLevelData)
                    }
                    when (testLevelData.stars) {
                        0 -> {
                            start1.setImageResource(R.drawable.ic_star_border)
                            start2.setImageResource(R.drawable.ic_star_border)
                            start3.setImageResource(R.drawable.ic_star_border)
                        }
                        1 -> {
                            start1.setImageResource(R.drawable.ic_star_yellow)
                            start2.setImageResource(R.drawable.ic_star_border)
                            start3.setImageResource(R.drawable.ic_star_border)
                        }
                        2 -> {
                            start1.setImageResource(R.drawable.ic_star_yellow)
                            start2.setImageResource(R.drawable.ic_star_yellow)
                            start3.setImageResource(R.drawable.ic_star_border)
                        }
                        3 -> {
                            start1.setImageResource(R.drawable.ic_star_yellow)
                            start2.setImageResource(R.drawable.ic_star_yellow)
                            start3.setImageResource(R.drawable.ic_star_yellow)
                        }
                    }
                } else {
                    itemVisible.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemLevelBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        return holder.onBind(list[position], position)
    }

    interface RvAction {
        fun itemClick(testLevelData: TestLevelData)
    }
}