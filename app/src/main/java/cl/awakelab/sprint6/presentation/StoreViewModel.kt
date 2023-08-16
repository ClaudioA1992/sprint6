package cl.awakelab.sprint6.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cl.awakelab.sprint6.data.Repository
import cl.awakelab.sprint6.data.local.PhoneDatabase
import cl.awakelab.sprint6.data.remote.ApiRetrofit
import kotlinx.coroutines.launch

class StoreViewModel(application: Application): AndroidViewModel(application) {

    private val repository: Repository

    fun phoneLiveData() = repository.getPhonesEntity()

    fun detailLiveData(id: Int) = repository.getPhoneDetail(id)

    init {
        val api = ApiRetrofit.getRetrofitInfo()
        val phoneDatabase = PhoneDatabase.getDatabase(application).getPhoneDao()
        repository = Repository(api, phoneDatabase)
    }

    fun getAllPhones() = viewModelScope.launch {
        repository.getPhones()
    }

    fun getPhoneDetail(id: Int) = viewModelScope.launch {
        repository.getDetail(id)
    }

}

