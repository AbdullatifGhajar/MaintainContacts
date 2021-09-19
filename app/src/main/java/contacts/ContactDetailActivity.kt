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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.maintain.contacts.R

class ContactDetailActivity : AppCompatActivity() {
    private var contactInfoRV: RecyclerView = findViewById(R.id.idRVContactInfo)
    private var contactInfoRVAdapter: ContactInfoRVAdapter? = null
    private var contactInfoList: List<ContactInfoModal> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val contactName = intent.getStringExtra("name").toString()
        val phoneNumber = intent.getStringExtra("phone").toString()

        val nameTV = findViewById<TextView>(R.id.idTVName)
        // TODO set image
        // val profileIV = findViewById<ImageView>(R.id.idIVProfile)
        val phoneTV = findViewById<TextView>(R.id.idTVPhone)
        nameTV.text = contactName
        phoneTV.text = phoneNumber
        val callIV = findViewById<ImageView>(R.id.idIVCall)
        val messageIV = findViewById<ImageView>(R.id.idIVMessage)
        // call function
        callIV.setOnClickListener {
            makeCall(phoneNumber)
        }
        // message function
        messageIV.setOnClickListener {
            sendMessage(phoneNumber)
        }

        contactInfoRVAdapter = ContactInfoRVAdapter(this, contactInfoList)
        contactInfoRV!!.layoutManager = LinearLayoutManager(this)
        contactInfoRV!!.adapter = contactInfoRVAdapter

        val addNewContactInfoFAB = findViewById<FloatingActionButton>(R.id.idFABAddContactInfo)
        addNewContactInfoFAB.setOnClickListener { // add new contact info

        }
    }

    private fun sendMessage(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:${phoneNumber}"))
        intent.putExtra("sms_body", "Enter your message")
        startActivity(intent)
    }

    private fun makeCall(phoneNumber: String) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:${phoneNumber}")
        if (ActivityCompat.checkSelfPermission(
                this@ContactDetailActivity,
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startActivity(callIntent)
        }
    }
}