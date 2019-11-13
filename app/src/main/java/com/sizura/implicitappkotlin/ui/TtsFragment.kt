package com.sizura.implicitappkotlin.ui


import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sizura.implicitappkotlin.R
import kotlinx.android.synthetic.main.fragment_tts.*
import kotlinx.android.synthetic.main.fragment_tts.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.toast
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TTSFragment : Fragment(), TextToSpeech.OnInitListener {


    var tts: TextToSpeech?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =inflater.inflate(R.layout.fragment_tts, container, false)

        tts= TextToSpeech(activity,this)
        v.btnSpeech.onClick { speech() }
        return v
    }

    override fun onInit(p0: Int) {
        if (p0 == TextToSpeech.SUCCESS) {
            val indo = Locale("id","ID")
            val setBahasa = tts?.setLanguage(indo)
            if (setBahasa == TextToSpeech.LANG_MISSING_DATA || setBahasa == TextToSpeech.LANG_NOT_SUPPORTED) {
                toast("bahasa tidak mendukung")
            } else {
                speech()
            }
        }
    }

    private fun speech() {
        val text = editText.text.toString()
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }

}
