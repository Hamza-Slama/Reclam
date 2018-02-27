//package ia2.moduleproject.eniso.reclam.Constant
//
///**
// * Created by hamza on 26/02/18.
// */
//import android.content.Context
//import android.content.Intent
//import android.content.SharedPreferences
//
//
////here for this class we are using a singleton pattern
//
//class SharedPrefManager  {
////    var context: Context? = null
//    constructor (context: Context){
//        var mCtx= context
//    }
//
//    //this method will checker whether user is already logged in or not
//    val isLoggedIn: Boolean
//        get() {
//            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
//            return sharedPreferences.getString(KEY_USERNAME, null) != null
//        }
//
//    //this method will give the logged in user
//    val user: User
//        get() {
//            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
//            return User(
//                    sharedPreferences.getInt(KEY_ID, -1),
//                    sharedPreferences.getString(KEY_USERNAME, null),
//                    sharedPreferences.getString(KEY_EMAIL, null),
//                    sharedPreferences.getString(KEY_GENDER, null)
//            )
//        }
//
//
//
//    //method to let the user login
//    //this method will store the user data in shared preferences
//    fun userLogin(user: User) {
//        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        editor.putInt(KEY_ID, user.getId())
//        editor.putString(KEY_USERNAME, user.getUsername())
//        editor.putString(KEY_EMAIL, user.getEmail())
//        editor.putString(KEY_GENDER, user.getGender())
//        editor.apply()
//    }
//
//    //this method will logout the user
//    fun logout() {
//        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        editor.clear()
//        editor.apply()
//        mCtx.startActivity(Intent(mCtx, LoginActivity::class.java))
//    }
//
//    companion object {
//
//        //the constants
//        private val SHARED_PREF_NAME = "simplifiedcodingsharedpref"
//        private val KEY_USERNAME = "keyusername"
//        private val KEY_EMAIL = "keyemail"
//        private val KEY_GENDER = "keygender"
//        private val KEY_ID = "keyid"
//
//        private var mInstance: SharedPrefManager? = null
//        private var mCtx: Context
//
//        @Synchronized
//        fun getInstance(context: Context): SharedPrefManager {
//            if (mInstance == null) {
//                mInstance = SharedPrefManager(context)
//            }
//            return mInstance
//        }
//    }
//}