package com.sizura.implicitappkotlin.ui


import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.sizura.implicitappkotlin.R
import com.sizura.implicitappkotlin.helper.Helper.Companion.currentDate
import kotlinx.android.synthetic.main.fragment_audio_recorder.*
import kotlinx.android.synthetic.main.fragment_audio_recorder.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class AudioRecorderFragment : Fragment() {
    //var nilai datanya bisa berubah
    //val nilainya tetap(immutable)(type datanya is like automatic)

    var recorder :MediaRecorder? = null
    var lokasifile: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_audio_recorder, container, false)
        // Inflate the layout for this fragment
        callPermission()
        v.btnPlay.isEnabled=false
        actionClick(v)
        return v
    }

    private fun actionClick(v: View) {
        v.btnRecordStop.onClick {
            if (btnRecordStop.text.toString().equals("RECORD")){
                recorder = MediaRecorder()
                lokasifile = Environment.getExternalStorageDirectory().absolutePath+"/REC"+currentDate()+
                        ".3gp"
                recorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
                recorder?.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                recorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB)
                recorder?.setOutputFile(lokasifile)
                try {
                    recorder?.prepare()
                    recorder?.start()
                    btnRecordStop.text="STOP"
                }catch (e : IOException){
                    Log.d("error",e.printStackTrace().toString())
                }

            }else if (btnRecordStop.text.toString().equals("STOP")){
                try {
                    recorder?.stop()
                    recorder?.release()
                    btnRecordStop.text="RECORD"
                    btnPlay.isEnabled=true
                }catch (e : IOException){
                    Log.d("error",e.printStackTrace().toString())
                }
            }
        }
        v.btnPlay.onClick {
            val mp = MediaPlayer()
            try {
                mp.setDataSource(lokasifile)
                mp.prepare()
                mp.start()
            }catch (e: IOException){
                Log.d("can't start",e.printStackTrace().toString())

            }
        }
    }

    private fun callPermission() {
        if (activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.RECORD_AUDIO
                )
            } != PackageManager.PERMISSION_GRANTED&& activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            } != PackageManager.PERMISSION_GRANTED&& activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity?.checkSelfPermission(
                    Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED&& activity?.checkSelfPermission(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED&& activity?.checkSelfPermission(
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE),
                    10
                )
            }
            return
        }
    }


}
