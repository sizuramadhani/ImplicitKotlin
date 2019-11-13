package com.sizura.implicitappkotlin.ui


import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sizura.implicitappkotlin.R
import kotlinx.android.synthetic.main.fragment_wifi.view.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class WifiFragment : Fragment() {

    var wifimanager: WifiManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_wifi, container, false)
        wifimanager = act.getApplicationContext().getSystemService(Context.WIFI_SERVICE)
                as WifiManager
        v.wifi.setChecked(status())
        v.wifi.setOnCheckedChangeListener { compoundButton, b ->
            wifiChangeStatus(b)
        }
        return v
    }

    private fun wifiChangeStatus(b: Boolean) {
        if (b == true && !wifimanager!!.isWifiEnabled) {
            wifimanager!!.isWifiEnabled = true
            toast("wifi dihidupkan")
        } else if (b == false && wifimanager!!.isWifiEnabled) {
            wifimanager!!.isWifiEnabled = false
            toast("wifi dimatikan")
        }
    }

    private fun status(): Boolean {
        return wifimanager!!.isWifiEnabled
    }

}
