package cl.awakelab.sprint6.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import cl.awakelab.sprint6.R
import cl.awakelab.sprint6.data.local.entities.PhoneEntity
import cl.awakelab.sprint6.databinding.PhoneItemBinding
import coil.load

class PhoneAdapter: RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder>() {

    private lateinit var binding: PhoneItemBinding
    private val phoneList = mutableListOf<PhoneEntity>()
    class PhoneViewHolder(private val phoneBinding: PhoneItemBinding):
        RecyclerView.ViewHolder(phoneBinding.root) {
        fun bind(phone: PhoneEntity) {
            phoneBinding.imageViewPhone.load(phone.image)
            phoneBinding.textViewPhoneName.text = phone.name
            phoneBinding.textViewPhonePrice.text = phone.price.toString()
            var bundle: Bundle = Bundle()
            bundle.putString("id", phone.id.toString())
            phoneBinding.constraintLayoutPhone.setOnClickListener {
                Navigation.findNavController(phoneBinding.root).navigate(R.id.action_phonesFragment_to_detailsFragment,
                    bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        binding = PhoneItemBinding.inflate(LayoutInflater.from(parent.context), parent,
            false)
        return PhoneViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return phoneList.size
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        val phone = phoneList[position]
        holder.bind(phone)
    }

    fun setData(phones: List<PhoneEntity>) {
        this.phoneList.clear()
        this.phoneList.addAll(phones)
        notifyDataSetChanged()
    }

}