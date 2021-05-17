package com.example.oilmessenger.presentation.messengerActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import com.example.oilmessenger.R
import com.example.oilmessenger.databinding.ActivityMessengerBinding
import com.example.oilmessenger.domain.User
import com.example.oilmessenger.presentation.AccountActivity
import com.example.oilmessenger.utilits.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


const val TAG = "MyTag"

lateinit var user: User

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMessengerBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Binding
        binding = ActivityMessengerBinding.inflate(layoutInflater)
        val view = binding.root
        val menu = binding.navView.menu
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        val drawer = binding.drawerLayout
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        val headerView = binding.navView.getHeaderView(0)
        val headerFirstName = headerView.findViewById<TextView>(R.id.editTextHeaderFirstName)
        val headerLastName = headerView.findViewById<TextView>(R.id.editTextHeaderLastName)
        val headerUserPhoto = headerView.findViewById<ImageView>(R.id.imageUser)

        //Context
        MESSENGER_ACTIVITY = this


        //Firebase
        initFirebase()
        val ref = FirebaseDatabase.getInstance().getReference(NODE_USERS).child(CURRENT_UID)

        Log.i(TAG,"Messenger activity onCreate")
        val UserInfListener = object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                 user = dataSnapshot.getValue(User::class.java)!!

                headerFirstName.text = user?.getFirstName()
                headerLastName.text = user?.getLastName()

                if (user?.photoUrl!!.isNotEmpty()){

                    headerUserPhoto.downloadAndSetImage(user?.photoUrl)
                }

            }
        }

        ref.addListenerForSingleValueEvent(UserInfListener)

        binding.navView.getHeaderView(0).findViewById<ImageView>(R.id.imageUser).setOnClickListener {
            Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.changeUserInformationFragment)
            drawer.closeDrawer(GravityCompat.START)
        }



        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.navView.setNavigationItemSelectedListener {

            when(it.itemId) {

                R.id.entry -> Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.accountActivity)

                R.id.exit -> {
                    AUTH.signOut()
                    menu.clear()

                    val intent = Intent(this, AccountActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    this.finish()
                }

                R.id.messages -> {
                    Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.messengerFragment)
                    drawer.closeDrawer(GravityCompat.START)
                }

                R.id.friends -> {

                    Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.friendsFragment)
                    drawer.closeDrawer(GravityCompat.START)
                }

            }

            true
        }

        setContentView(view)
    }


    override fun onStart() {
        super.onStart()
        AppStates.updateState(AppStates.ONLINE)
    }

    override fun onStop() {
        super.onStop()
        AppStates.updateState(AppStates.OFFLINE)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppStates.updateState(AppStates.OFFLINE)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun updateUserPhoto(url: String){
        binding.navView.getHeaderView(0).findViewById<ImageView>(R.id.imageUser).downloadAndSetImage(url)
    }
}

