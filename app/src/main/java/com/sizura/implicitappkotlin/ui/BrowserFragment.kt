package com.sizura.implicitappkotlin.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.sizura.implicitappkotlin.R
import kotlinx.android.synthetic.main.fragment_browser.*
import kotlinx.android.synthetic.main.fragment_browser.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.browse

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class BrowserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_browser, container, false)
        // Inflate the layout for this fragment
        v.btngoogle.onClick { browse("http://google.com") }
        v.btnima.onClick { browse("http://imastudio.co.id") }
        v.btnsearch.onClick {
            if (edtlink.text.isEmpty()){
                edtlink.error="link tidak boleh kosong"
                edtlink.requestFocus()
            }else{
                webview.settings.javaScriptEnabled= true
                webview.webViewClient = WebViewClient()
                webview.loadUrl("http://${edtlink.text}")
            }
        }
        return v
    }


}
