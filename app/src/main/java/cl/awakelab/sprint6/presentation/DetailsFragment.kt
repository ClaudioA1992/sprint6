package cl.awakelab.sprint6.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import cl.awakelab.sprint6.R
import cl.awakelab.sprint6.data.local.entities.PhoneDetailEntity
import cl.awakelab.sprint6.databinding.FragmentDetailsBinding
import coil.load


class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    private val storeViewModel: StoreViewModel by activityViewModels()

    // TODO: Rename and change types of parameters
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString("id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(LayoutInflater.from(activity))
        initData()
        return binding.root
    }

    private fun initData() {
        storeViewModel.getPhoneDetail(param1!!.toInt(), requireContext())
        Log.d("Init data", "Initializing data in details fragment")
        Log.d("Param1:", param1.toString())
        lateinit var detail: PhoneDetailEntity
        storeViewModel.detailLiveData(param1!!.toInt()).observe(viewLifecycleOwner) {
            if(it!=null) {
                detail = it
                Log.d("Into live data", "Objeto: ${it}")
                binding.textViewDetailName.text = it.name
                binding.textViewDetailDescription.text = it.description
                binding.textViewDetailLastPrice.text = getString(R.string.last_price)+ it.lastPrice.toString()
                binding.textViewDetailPrice.text = getString(R.string.actual_price) + it.price.toString()
                binding.imageViewPhoneDetail.load(it.image)
                if (it.credit) {
                    binding.textViewDetailCredit.text = getString(R.string.accepts_credit)
                } else {
                    binding.textViewDetailCredit.text = getString(R.string.only_cash)
                }
            }

        }
        binding.buttonContact.setOnClickListener{
            Log.d("Button contact", "Button pressed")
            Log.d("Button contact", detail.name)
            val intent = Intent(Intent.ACTION_SENDTO)
            val mail = arrayOf("info@novaera.cl")
            intent.data = Uri.parse("mailto:") // Apps de email manejan este intent
            intent.putExtra(Intent.EXTRA_EMAIL, mail)
            intent.putExtra(Intent.EXTRA_SUBJECT, "Consulta ${detail.name} id ${detail.id}")
            intent.putExtra(Intent.EXTRA_TEXT, "Hola\n" +
                    "Vi el celular ${detail.name} de código ${detail.id} y me gustaría\n" +
                    "que me contactaran a este correo o al siguiente número: 12312321\n" +
                    "Quedo atento.")
            startActivity(intent)
        }
    }

}

