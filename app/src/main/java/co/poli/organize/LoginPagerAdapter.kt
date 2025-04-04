package co.poli.organize


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import co.poli.organize.fragments.LoginFragment
import co.poli.organize.fragments.RegisterFragment

class LoginPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    // Definimos el número de páginas que tendrá nuestro ViewPager
    override fun getItemCount(): Int = 2

    // Devuelve el fragmento correspondiente a cada posición
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LoginFragment()
            1 -> RegisterFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}