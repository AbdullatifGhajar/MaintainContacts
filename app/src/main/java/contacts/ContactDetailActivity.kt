package contacts

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maintain.contacts.R

@JvmInline
value class ContactInfo(val phoneNumber: String)

class ContactDetailActivity : AppCompatActivity() {
    // TODO use variable of element instead
    private var contactInfoList: List<ContactInfoModal> = listOf(
        ContactInfoModal("HI, this is a test description"),
        ContactInfoModal("This is also a test one"),
    )
    private var contactInfoRV: RecyclerView? = null
    private var contactInfoRVAdapter: ContactInfoRVAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val contactName = intent.getStringExtra("name").toString()
        val contactInfo = ContactInfo(intent.getStringExtra("contact").toString())

        val nameTV = findViewById<TextView>(R.id.idTVName)
        // TODO set image
        // val profileIV = findViewById<ImageView>(R.id.idIVProfile)
        val phoneTV = findViewById<TextView>(R.id.idTVPhone)
        nameTV.text = contactName
        phoneTV.text = contactInfo.phoneNumber
        val callIV = findViewById<ImageView>(R.id.idIVCall)
        val messageIV = findViewById<ImageView>(R.id.idIVMessage)
        // call function
        callIV.setOnClickListener {
            makeCall(contactInfo)
        }
        // message function
        messageIV.setOnClickListener {
            sendMessage(contactInfo)
        }

        contactInfoRV = findViewById(R.id.idRVContactInfo)
        contactInfoRVAdapter = ContactInfoRVAdapter(this, contactInfoList)
        contactInfoRV!!.layoutManager = LinearLayoutManager(this)
        contactInfoRV!!.adapter = contactInfoRVAdapter
    }

    private fun sendMessage(contactInfo: ContactInfo) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:${contactInfo.phoneNumber}"))
        intent.putExtra("sms_body", "Enter your message")
        startActivity(intent)
    }

    private fun makeCall(contactInfo: ContactInfo) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:${contactInfo.phoneNumber}")
        if (ActivityCompat.checkSelfPermission(
                this@ContactDetailActivity,
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startActivity(callIntent)
        }
    }
}