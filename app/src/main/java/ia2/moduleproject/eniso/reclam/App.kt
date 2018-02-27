package ia2.moduleproject.eniso.reclam

/**
 * Created by hamza on 26/02/18.
 */
import android.app.Application
import ia2.moduleproject.eniso.reclam.Services.VolleyService


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        VolleyService.initialize(this)
    }

}