package com.sizura.implicitappkotlin.ui


import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.sizura.implicitappkotlin.helper.Helper.Companion.CAMERA
import com.sizura.implicitappkotlin.helper.Helper.Companion.GALERY
import com.sizura.implicitappkotlin.helper.Helper.Companion.currentDate
import kotlinx.android.synthetic.main.fragment_camera.*
import kotlinx.android.synthetic.main.fragment_camera.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.toast
import java.io.File
import java.io.InputStream

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CameraFragment : Fragment() {

    var lokasiFile: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var v = inflater.inflate(com.sizura.implicitappkotlin.R.layout.fragment_camera, container, false)
        callPermission()
        actionClick(v)
        return v
    }
    private fun actionClick(v: View) {
        v.btncamera.onClick {
            var builder = StrictMode.VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())

            val namaFolder = "my Pict"
            val destPath = context?.getExternalFilesDir(null)?.absolutePath
            val file = File(destPath, namaFolder)
            if (!file.exists()) {
                file.mkdir()
            }
            val fileGambar = File(destPath + "/" + namaFolder + "/PIC" + currentDate() + ".jpg")

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            lokasiFile = Uri.fromFile(fileGambar)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.putExtra(MediaStore.EXTRA_OUTPUT,lokasiFile)
            startActivityForResult(intent,CAMERA)

        }
        v.btnshow.onClick {
            val galery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galery,GALERY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== CAMERA && resultCode==RESULT_OK){
            toast("lokasi file : ${lokasiFile.toString()}")

            //over here(my ever error code tonight)
            var lokasiGambar = lokasiFile
            var inputStream :InputStream?=null
            try {
                inputStream= lokasiGambar?.let { activity?.contentResolver?.openInputStream(it) }
            }catch (e:Exception){
                e.localizedMessage
            }
            var bitmap = BitmapFactory.decodeStream(inputStream)
            imgshow.setImageBitmap(bitmap)

        }else if (requestCode== GALERY &&resultCode==RESULT_OK){
            var lokasiGambar = data?.data
            var inputStream :InputStream?=null
            try {
                inputStream= lokasiGambar?.let { activity?.contentResolver?.openInputStream(it) }
            }catch (e:Exception){
                e.localizedMessage
            }
            var bitmap = BitmapFactory.decodeStream(inputStream)
            imgshow.setImageBitmap(bitmap)
        }
    }


    private fun callPermission() {

        if (activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.CAMERA
                )
            } != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity?.checkSelfPermission(
                    Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    10
                )
            }
            return
        }
    }


}