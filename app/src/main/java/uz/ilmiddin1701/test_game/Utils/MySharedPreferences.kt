package uz.ilmiddin1701.test_game.Utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MySharedPreferences {
    private const val NAME = "catch_file_name"
    private const val MODE = Context.MODE_PRIVATE

    private lateinit var preferences: SharedPreferences

    fun init(context: Context){
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation:(SharedPreferences.Editor) -> Unit){
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var userId: String
        get() = preferences.getString("keyUser", "empty")!!
        set(value) = preferences.edit {
            it.putString("keyUser", value)
        }

//    var sharedList: ArrayList<ToDo>
//        get() = gsonStringToList(preferences.getString("openList", "[]")!!)
//        set(value) = preferences.edit {
//            it.putString("openList", listToGsonString(value))
//        }
//
//    private fun gsonStringToList(gsonString: String): ArrayList<ToDo>{
//        val list = ArrayList<ToDo>()
//        val type = object : TypeToken<ArrayList<ToDo>>() {}.type
//        list.addAll(Gson().fromJson(gsonString, type))
//        return list
//    }
//
//    private fun listToGsonString(list:ArrayList<ToDo>): String{
//        return Gson().toJson(list)
//    }
}