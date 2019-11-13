package com.sizura.implicitappkotlin.ui


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.sizura.implicitappkotlin.R
import com.sizura.implicitappkotlin.helper.Helper.Companion.PHONE
import kotlinx.android.synthetic.main.fragment_phone.*
import kotlinx.android.synthetic.main.fragment_sms.*
import kotlinx.android.synthetic.main.fragment_sms.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.sendSMS
import org.jetbrains.anko.support.v4.toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SmsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v =inflater.inflate(R.layout.fragment_sms, container, false)
        callPermission()
        actionClick(v)

        return v
    }

    private fun actionClick(v: View) {
        v.edtnohp.onClick {
            val i = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            i.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(i, PHONE)
        }
        v.btnkirimsms.onClick {
            try {
                val manager = SmsManager.getDefault()
                manager.sendTextMessage(
                    edtnohp.text.toString(), null
                    , edtmessage.text.toString(), null, null
                )
                toast("Pesan Terkirim")
            } catch (e: Exception) {
                toast("pesan tidak terkirim")
            }
        }
        v.btnsmsintent.onClick {
            //cara biasa
//        val sms = Intent(Intent.ACTION_VIEW)
//        sms.type = "vnd.android-dir/mms-sms"
//        sms.putExtra("address", edtnohp.text.toString())
//        sms.putExtra("sms_body", edtmessage.text.toString())
//        startActivity(sms)
            //cara anko
            sendSMS(edtnohp.text.toString(), edtmessage.text.toString())
        }
    }

    private fun callPermission() {
        if (activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.SEND_SMS
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity?.checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.SEND_SMS),
                    10
                )
            }
            return
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== PHONE&&resultCode== Activity.RESULT_OK){
            var cursor: Cursor? = null
            try {
                val uri = data?.data
                cursor = uri?.let {
                    activity?.contentResolver?.query(
                        it,
                        arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER), null, null, null
                    )
                }
                if (cursor != null && cursor.moveToNext()) {
                    val phone = cursor.getString(0)
                    edtnumber.setText(phone)
                }
            } catch (e: Exception) {
                print(e.localizedMessage)
            }
        }
    }


}
