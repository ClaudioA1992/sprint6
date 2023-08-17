package cl.awakelab.sprint6.presentation

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.awakelab.sprint6.R
import cl.awakelab.sprint6.databinding.FragmentPhonesBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PhonesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PhonesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentPhonesBinding
    val storeViewModel: StoreViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhonesBinding.inflate(layoutInflater, container, false)
        storeViewModel.getAllPhones()
        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        val adapter = PhoneAdapter()
        binding.recyclerViewPhones.adapter = adapter
        storeViewModel.phoneLiveData().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }
}

