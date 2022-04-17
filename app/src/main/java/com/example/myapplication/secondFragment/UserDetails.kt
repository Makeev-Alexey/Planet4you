package com.example.myapplication.secondFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapplication.Data.API.UserInfo
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentUserDetailsBinding
import com.example.myapplication.databinding.FragmentUserListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var _binding : FragmentUserDetailsBinding? = null
private val binding get() = _binding!!

/**
 * A simple [Fragment] subclass.
 * Use the [UserDetails.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserDetails : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = arguments?.getParcelable<UserInfo>("key")
        if (user != null) {
            binding.textView5.text = user.firstName
            binding.textView6.text = user.lastName
            binding.textView7.text = user.id.toString()
            binding.textView8.text = user.email
            Glide.with(binding.imageView.context)
                .load(user.avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(60, 60)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.imageView)
            binding.button.setOnClickListener {
                var bundle = Bundle()
                bundle.putParcelable("key", user)
                binding.root.findNavController().navigate(R.id.editFragment, bundle)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserDetails.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserDetails().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}