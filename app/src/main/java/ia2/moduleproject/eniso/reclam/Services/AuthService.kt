package ia2.moduleproject.eniso.reclam.Services

import android.app.ProgressDialog
import android.content.Context
import ia2.moduleproject.eniso.reclam.Constant.MyUserLoginAndPassword
import ia2.moduleproject.eniso.reclam.Common.Callback
import ia2.moduleproject.eniso.reclam.Dal.EnisoInfoDAO
import ia2.moduleproject.eniso.reclam.Models.NavigablePeriods
import ia2.moduleproject.eniso.reclam.Models.UserInfo
import ia2.moduleproject.eniso.reclam.Models.UserInformationSerialized

/**
 * Created by hamza on 02/03/18.
 */

class AuthService {
    var context: Context;
    var enisoInfoDAO: EnisoInfoDAO

    constructor(context: Context) {
        this.context = context;
        this.enisoInfoDAO = EnisoInfoDAO(context)
    }
    lateinit var progressDialog: ProgressDialog

    fun showProgress() {

        enisoInfoDAO.showProgress()
    }
    fun progressDismiss(){
        enisoInfoDAO.progressDismiss()
    }



    fun login(user: String, password: String, listener: Callback<UserInformationSerialized?>) {
        enisoInfoDAO.login(user, password, listener)
    }

    fun saveLoginUser(user:String , pass : String , sessionId: String) {
        val infofile = context.getSharedPreferences(MyUserLoginAndPassword, Context.MODE_PRIVATE)
        val editor = infofile.edit()
        editor.putString("username", user!!)
        editor.putString("pass", pass!!)
        editor.putString("sessionId", sessionId)
        editor.apply()

    }

    fun getUserInfo(): UserInfo {

        val infofile = context.getSharedPreferences(MyUserLoginAndPassword, Context.MODE_PRIVATE)
        val name = infofile.getString("username", "")
        val passwword = infofile.getString("pass", "")
        return UserInfo(name, passwword)
    }

    fun getSessionId ():String {
        val infofile = context.getSharedPreferences(MyUserLoginAndPassword, Context.MODE_PRIVATE)
        val sessionId = infofile.getString("sessionId", "")
        return sessionId
    }

    fun postTest(sessionId: String, listener : Callback <Array<NavigablePeriods>?>){
        enisoInfoDAO.post(sessionId,listener)

    }


}
