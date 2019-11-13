package com.sizura.implicitappkotlin.ui


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.seljabali.library.intents.Intents
import com.seljabali.library.intents.getPlayYouTubeQuery
import com.sizura.implicitappkotlin.R
import kotlinx.android.synthetic.main.activity_main.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.act

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {
    var fragment : Fragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        // Inflate the layout for this fragment

        v.btnaudiomanager.onClick {
            fragment = AudioManagerFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as AudioManagerFragment)?.commit()
        }

        v.btnaudiorecorder.onClick {
            fragment = AudioRecorderFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as AudioRecorderFragment)?.commit()
        }

        v.btnbrowser.onClick {
            fragment = BrowserFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as BrowserFragment)?.commit()
        }

        v.btncamera.onClick {
            fragment = CameraFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as CameraFragment)?.commit()
        }
        v.btnemail.onClick {
            fragment = EmailFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as EmailFragment)?.commit()
        }
        v.btnphone.onClick {
            fragment = PhoneFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as PhoneFragment)?.commit()
        }
        v.btnsms.onClick {
            fragment = SmsFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as SmsFragment)?.commit()
        }
        v.btntts.onClick {
            fragment = TTSFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as TTSFragment)?.commit()
        }
        v.btnvideo.onClick {
            fragment = VideoFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as VideoFragment)?.commit()
        }
        v.btnwa.onClick {
            val wa = act.packageManager.getLaunchIntentForPackage("com.whatsapp")
            if (wa!=null){
                startActivity(wa)
            }else{
                val i = Intent()
                i.action = Intent.ACTION_VIEW
                i.data= Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
                startActivity(i)
            }
        }
        v.btnwifi.onClick {
            fragment = WifiFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment as WifiFragment)?.commit()
        }
        v.btnyoutube.onClick {
            startActivity(activity?.let { it1 -> Intents.getPlayYouTubeQuery(it1,"indonesia") })
        }
        return v
    }


}
