package com.example.myapplication.edit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.myapplication.Data.API.UserInfo
import com.example.myapplication.Data.UserInfoRepo
import com.example.myapplication.R
import com.example.myapplication.databinding.EditFragmentBinding
import com.example.myapplication.databinding.FragmentUserDetailsBinding
import com.example.myapplication.firstFragment.MyViewModelFactory
import com.example.myapplication.firstFragment.UserListViewModel

class EditFragment : Fragment() {
    private var _binding : EditFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = EditFragment()
    }

    private lateinit var viewModel: EditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EditFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = arguments?.getParcelable<UserInfo>("key")
        if (user != null) {
            binding.textView9.setText(user.firstName)
            binding.textView10.setText(user.lastName)
            binding.textView11.setText(user.email)
        }
        var viewModel = ViewModelProvider(this, MyViewModelFactory(UserInfoRepo(requireContext()))).get(
            EditViewModel::class.java)
        binding.button2.setOnClickListener {
            if (user != null) {

                viewModel.updateUserInfo(UserInfo(user.id, binding.textView11.text.toString()
                    , binding.textView9.text.toString(), binding.textView10.text.toString(),user.avatar))
                binding.root.findNavController().navigate(R.id.userList)
            }
        }
        binding.button4.setOnClickListener{
            if (user != null) {
                viewModel.deleteUserInfo(user)
                binding.root.findNavController().navigate(R.id.userList)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditViewModel::class.java)
        // TODO: Use the ViewModel
    }

}