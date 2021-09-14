package contacts

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.maintain.contacts.R
import contacts.CreateNewContactActivity

class CreateNewContactActivity : AppCompatActivity() {
    private var nameEdt: EditText? = null
    private var phoneEdt: EditText? = null
    private var emailEdt: EditText? = null
    private var addContactEdt: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_contact)
        nameEdt = findViewById(R.id.idEdtName)
        phoneEdt = findViewById(R.id.idEdtPhoneNumber)
        emailEdt = findViewById(R.id.idEdtEmail)
        addContactEdt = findViewById(R.id.idBtnAddContact)
        addContactEdt?.setOnClickListener {
            val name = nameEdt?.text.toString()
            val phone = phoneEdt?.text.toString()
            val email = emailEdt?.text.toString()
            // TODO parse profile image

            //validate input
            if (TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && TextUtils.isEmpty(phone)) {
                Toast.makeText(
                    this@CreateNewContactActivity,
                    "Please enter the data in all fields. ",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                addContact(name, email, phone)
            }
        }
    }

    private fun addContact(name: String, email: String, phone: String) {
        // TODO save profile image
        val contactIntent = Intent(ContactsContract.Intents.Insert.ACTION)
        contactIntent.type = ContactsContract.RawContacts.CONTENT_TYPE
        contactIntent
            .putExtra(ContactsContract.Intents.Insert.NAME, name)
            .putExtra(ContactsContract.Intents.Insert.PHONE, phone)
            .putExtra(ContactsContract.Intents.Insert.EMAIL, email)
        startActivityForResult(contactIntent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Contact Has Been Added.", Toast.LENGTH_SHORT).show()
                val i = Intent(this@CreateNewContactActivity, MainActivity::class.java)
                startActivity(i)
            }
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(
                    this, "Added Contact Cancelled",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}