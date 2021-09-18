package contacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maintain.contacts.R

class ContactInfoRVAdapter
    (
    private val context: Context,
    private var contactInfoList: List<ContactInfoModal>?
) : RecyclerView.Adapter<ContactInfoRVAdapter.CIViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CIViewHolder {
        // pass layout file for displaying card item
        return CIViewHolder(
            LayoutInflater.from(context).inflate(R.layout.contact_info_item, parent, false)
        )
    }

    fun filterList(filterList: List<ContactInfoModal>?) {
        contactInfoList = filterList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CIViewHolder, position: Int) {
        val modal = contactInfoList!![position]
        holder.contactInfoDescriptionTV.text = modal.description
    }

    inner class CIViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contactInfoDescriptionTV: TextView =
            itemView.findViewById(R.id.idTVContactInfoDescription)
    }

    override fun getItemCount(): Int {
        return contactInfoList!!.size
    }
}