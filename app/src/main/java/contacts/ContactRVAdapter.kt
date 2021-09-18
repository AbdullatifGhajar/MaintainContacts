package contacts

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.maintain.contacts.R

class ContactRVAdapter
    (
    private val context: Context,
    private var contactModalArrayList: List<ContactModal>?
) : RecyclerView.Adapter<ContactRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // pass layout file for displaying card item
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false)
        )
    }

    fun filterList(filterList: List<ContactModal>?) {
        contactModalArrayList = filterList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modal = contactModalArrayList!![position]
        holder.contactTV.text = modal.userName
        // TODO don't continuously generate new color
        // TODO use profile image if possible
        val color = ColorGenerator.MATERIAL.randomColor
        val drawable = TextDrawable.builder().beginConfig()
            .width(100)
            .height(100)
            .endConfig()
            .buildRound(modal.userName.substring(0, 1), color)
        holder.contactIV.setImageDrawable(drawable)

        holder.itemView.setOnClickListener {
            val i = Intent(context, ContactDetailActivity::class.java)
            // TODO don't put parameters separately, use objects instead
            i.putExtra("name", modal.userName)
            i.putExtra("contact", modal.contactNumber)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return contactModalArrayList!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contactIV: ImageView = itemView.findViewById(R.id.idIVProfile)
        val contactTV: TextView = itemView.findViewById(R.id.idTVContactName)
    }
}