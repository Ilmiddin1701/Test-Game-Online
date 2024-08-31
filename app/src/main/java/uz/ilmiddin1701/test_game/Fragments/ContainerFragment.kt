package uz.ilmiddin1701.test_game.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import uz.ilmiddin1701.test_game.R
import uz.ilmiddin1701.test_game.databinding.FragmentContainerBinding

class ContainerFragment : Fragment() {
    private val binding by lazy { FragmentContainerBinding.inflate(layoutInflater) }
    private lateinit var navHostFragment: NavHostFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            // BottomNavigationView connect Fragments
            navHostFragment = childFragmentManager.findFragmentById(R.id.my_navigation_host_1) as NavHostFragment
            val navController = navHostFragment.navController
            bottomNavigationView.setupWithNavController(navController)
        }
        return binding.root
    }
}