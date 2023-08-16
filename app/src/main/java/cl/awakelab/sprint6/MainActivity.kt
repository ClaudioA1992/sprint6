package cl.awakelab.sprint6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.awakelab.sprint6.data.Repository
import cl.awakelab.sprint6.data.local.PhoneDatabase
import cl.awakelab.sprint6.data.remote.ApiRetrofit
import cl.awakelab.sprint6.data.remote.PhoneAPI

class MainActivity : AppCompatActivity() {

    val api = ApiRetrofit.getRetrofitInfo()
    val database = PhoneDatabase.getDatabase(application).getPhoneDao()
    val repository = Repository(api, database)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



}