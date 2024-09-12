package uz.ilmiddin1701.test_game.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.ilmiddin1701.test_game.Models.TestLevelData
import uz.ilmiddin1701.test_game.Models.User
import uz.ilmiddin1701.test_game.R
import uz.ilmiddin1701.test_game.databinding.ItemLevelBinding
import uz.ilmiddin1701.test_game.databinding.ItemUsersBinding

class LevelsAdapter(var tests: Int, private var list: ArrayList<User>) : RecyclerView.Adapter<LevelsAdapter.Vh>() {

    inner class Vh(var itemRvBinding: ItemUsersBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {
        @SuppressLint("SetTextI18n", "DefaultLocale")
        fun onBind(user: User, position: Int) {
            itemRvBinding.apply {
                if (user.userImage != "null") {
                    Picasso.get().load(user.userImage).into(userImage)
                } else {
                    userImage.setImageResource(R.drawable.ic_user)
                }
                if (position == 1) {
                    topView.visibility = View.VISIBLE
                } else {
                    topView.visibility = View.GONE
                }
                when (tests) {
                    20 -> tvLevel.text = String.format("%02d", position)
                    30 -> tvLevel.text = String.format("%02d", position)
                    40 -> tvLevel.text = String.format("%02d", position)
                    50 -> tvLevel.text = String.format("%02d", position)
                }
                tvUserName.text = "@" + user.userName
                tvName.text = "${user.firstName} ${user.lastName}"

                tvUserName.isSelected = true
                tvLevel.isSelected = true
                tvName.isSelected = true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        return holder.onBind(list[position], position+1)
    }
}