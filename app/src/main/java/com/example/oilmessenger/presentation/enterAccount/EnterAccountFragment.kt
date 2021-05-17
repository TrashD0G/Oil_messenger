package com.example.oilmessenger.presentation.enterAccount

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.oilmessenger.R
import com.example.oilmessenger.databinding.FragmentEnterAccountBinding
import com.example.oilmessenger.di.OilMessengerApplication
import com.example.oilmessenger.presentation.enterAccount.viewModelEnterAccount.ViewModelEnterAccount
import com.example.oilmessenger.presentation.enterAccount.viewModelEnterAccount.ViewModelEnterAccountFactory
import com.example.oilmessenger.presentation.messengerActivity.MainActivity
import com.example.oilmessenger.presentation.messengerActivity.TAG
import javax.inject.Inject


class EnterAccountFragment : Fragment() {

    private var fragmentEnterAccountBinding: FragmentEnterAccountBinding? = null

    @Inject
    lateinit var viewModelEnterFactory: ViewModelEnterAccountFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentEnterAccountBinding =
            FragmentEnterAccountBinding.inflate(inflater, container, false)
        return fragmentEnterAccountBinding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Dagger inject
        (requireActivity().applicationContext as OilMessengerApplication)
            .applicationAppComponent.injectEnterAccountFragment(this)

        //Data binding
        val editTextEmailData = fragmentEnterAccountBinding?.editTextAccountLogin
        val editTextPasswordData = fragmentEnterAccountBinding?.editTextAccountPassword

        // ViewModel
        val viewModel = ViewModelProvider(requireActivity(), viewModelEnterFactory).get(ViewModelEnterAccount::class.java)
        viewModel.emailValidate.observe(viewLifecycleOwner,
            { if (!it) editTextEmailData?.error = "Ошибка ввода!" })
        viewModel.passwordValidate.observe(viewLifecycleOwner,
            { if (!it) editTextPasswordData?.error = "Ошибка ввода!" })
        viewModel.enterAccountResult.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            Log.i(TAG,"Enter fragment result: " + it.toString())
            if (it == "Выполнен вход!") {


                val intent = Intent(requireActivity(), MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                requireActivity().finish()
            }
        })

        fragmentEnterAccountBinding?.buttonEnter?.setOnClickListener {

            viewModel.checkInput(
                editTextEmailData?.text.toString(),
                editTextPasswordData?.text.toString()
            )
        }

        fragmentEnterAccountBinding?.buttonRegister?.setOnClickListener {
            view.findNavController().navigate(R.id.action_enterAccountFragment_to_createAccountFragment)
        }

    }

}