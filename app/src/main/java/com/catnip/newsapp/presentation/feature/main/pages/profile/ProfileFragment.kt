package com.catnip.newsapp.presentation.feature.main.pages.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import com.catnip.newsapp.databinding.FragmentProfileBinding
import com.catnip.newsapp.presentation.feature.login.LoginActivity
import com.catnip.newsapp.presentation.feature.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonClick()
        loadImageProfile()
    }

    private fun loadImageProfile() {
        binding.ivProfile.load("https://avatars.githubusercontent.com/u/21256595?v=4"){
            crossfade(true)
        }
    }

    private fun setButtonClick() {
        binding.btnLogout.setOnClickListener {
            doLogout()
        }
    }

    private fun doLogout() {
        mainViewModel.doLogout().observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                startActivity(Intent(requireContext(), LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            } else {
                Toast.makeText(requireContext(), "Failed to logout", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}