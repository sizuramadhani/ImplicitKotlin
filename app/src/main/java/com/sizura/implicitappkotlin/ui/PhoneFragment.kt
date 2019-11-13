package com.sizura.implicitappkotlin.ui


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.sizura.implicitappkotlin.R
import com.sizura.implicitappkotlin.helper.Helper.Companion.PHONE
import kotlinx.android.synthetic.main.fragment_phone.*
import kotlinx.android.synthetic.main.fragment_phone.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.makeCall

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PhoneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_phone, container, false)
        // Inflate the layout for this fragment
        callPermission()
        actionClick(v)
        return v
    }

    private fun actionClick(v: View) {
        v.btncall.onClick {
            makeCall(edtnumber.text.toString())
        }
        v.btntampilcall.onClick { startActivity(Intent(Intent.ACTION_DIAL,Uri.parse("tel:${edtnumber.text.toString()}"))) }
        v.btnlistcontact.onClick {
            val i = Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI)
            i.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(i,PHONE)
        }
    }

    private fun callPermission() {
        if (activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.CALL_PHONE
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity?.checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.CALL_PHONE),
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
