package com.example.webview


import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.webview.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private  lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())
        replaceFragment(HomeFragment())
        binding.bottomNavigationView.setBackground(null)
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            binding.bottomNavigationView.menu.setGroupCheckable(0, true, true);
            when (item.getItemId()) {
                R.id.perfil -> replaceFragment(PerfilFagment())
                R.id.home -> replaceFragment(HomeFragment())
                R.id.talleres -> replaceFragment(TalleresFragment())
                R.id.soporte -> replaceFragment(SoporteFragment())
            }
            true
        }
        binding.fabButton.setOnClickListener {
            replaceFragment(FabFragment())
            binding.bottomNavigationView.menu.setGroupCheckable(0, false, true);
        }

        binding.bottomNavigationView.resizeIcon(3,38f,15,isBottom = true)
        binding.bottomNavigationView.resizeIcon(4,30f,0,isBottom = true)
        binding.bottomAppBar.setBackgroundResource(R.color.menu)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

}

fun BottomNavigationView.resizeIcon(indexIcon:Int, sizeDp:Float,margin : Int, isBottom: Boolean){
    val menuView = getChildAt(0) as BottomNavigationMenuView
    val iconView = menuView.getChildAt(indexIcon).findViewById<View>(com.google.android.material.R.id.navigation_bar_item_icon_view)
    val layoutParams = iconView.layoutParams
    val displayMetrics = resources.displayMetrics
    val newSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeDp, displayMetrics).toInt()
    layoutParams.height =newSize
    layoutParams.width = newSize
    val fl = FrameLayout.LayoutParams(newSize,newSize, Gravity.CENTER)
    if (isBottom){
        fl.setMargins(0,0,0,margin)
    }else{
        fl.setMargins(0,margin,0,0)
    }

    iconView.layoutParams = fl
}