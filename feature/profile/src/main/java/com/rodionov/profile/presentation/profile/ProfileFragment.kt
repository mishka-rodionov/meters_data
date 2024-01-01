package com.rodionov.profile.presentation.profile

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.factory.CommonViewModelFactory
import com.rodionov.base.interaction.NavigationExecutor
import com.rodionov.base.platform.BaseFragment
import com.rodionov.domain.models.User
import com.rodionov.profile.R
import com.rodionov.profile.data.di.ProfileComponentViewModel
import com.rodionov.profile.databinding.FragmentProfileBinding
import com.rodionov.utils.extensions.launchWithLifecycleStarted
import dagger.Lazy
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)

    override val toolbarTitle = R.string.toolbar_title_profile

    @Inject
    lateinit var viewModelFactory: Lazy<CommonViewModelFactory>

    override val screenViewModel: ProfileViewModel by viewModels {
        viewModelFactory.get()
    }

    var imagePickerActivityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result != null) {
            val imageUri: Uri? = result.getData()?.getData()
            Log.d("LOG_TAG", "onActivityResult: $imageUri")
            //                    Glide.with(this)
            //                        .load(imageUri)
            //                        .into(R.id.profileImageView)
        }
    }

    val requestAllPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.forEach {
                when (it.key) {
                    Manifest.permission.READ_EXTERNAL_STORAGE -> {
                        Log.d("LOG_TAG", "Manifest.permission.READ_EXTERNAL_STORAGE: ")
                        if (it.value) {
                            Log.d("LOG_TAG", "IS GRANTED: ")
                        } else {
                            Log.d("LOG_TAG", "DENIED: ")
                        }
                    }
                    Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                        Log.d("LOG_TAG", "Manifest.permission.WRITE_EXTERNAL_STORAGE: ")
                        if (it.value) {
                            Log.d("LOG_TAG", "IS GRANTED: ")
                        } else {
                            Log.d("LOG_TAG", "DENIED: ")
                        }
                    }
                    else -> {}
                }
            }
        }

//    val requestGalleryPermission =

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<ProfileComponentViewModel>().profileComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenViewModel.user.onEach(::handleUser)
            .launchWithLifecycleStarted(lifecycleScope, lifecycle)
        screenViewModel.exit.onEach(::handleExit)
            .launchWithLifecycleStarted(lifecycleScope, lifecycle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvPersonalInfo.setOnClickListener { screenViewModel.navigate(R.id.action_profileFragment_to_personalInformationFragment) }
        binding.tvFlatInfo.setOnClickListener { screenViewModel.navigate(R.id.action_profileFragment_to_flatListFragment) }
        binding.tvExit.setOnClickListener { screenViewModel.exit() }
        binding.ivProfileImage.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                Log.d("LOG_TAG", "onViewCreated: read = ${shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)}")
                Log.d("LOG_TAG", "onViewCreated: camera = ${shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)}")
                Log.d("LOG_TAG", "onViewCreated: location = ${shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)}")
//                requestAllPermission.launch(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE))
            }
//            val intent = Intent().apply {
//                action = Intent.ACTION_OPEN_DOCUMENT
//                type = "image/*"
//            }
//            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//            requireActivity().startActivityForResult(
//                Intent.createChooser(intent, "Выбрать фото"),
//                101
//            )
//            val galleryIntent = Intent(Intent.ACTION_PICK_ACTIVITY)
//            imagePickerActivityResult.launch(galleryIntent)
        }
        screenViewModel.getDefaultUser()
    }

    private fun handleUser(user: User) {
        binding.tvUserFirstName.text = user.firstName.replaceFirstChar { it.uppercaseChar() }
        binding.tvUserLastName.text = user.lastName.replaceFirstChar { it.uppercaseChar() }
    }

    private fun handleExit(exit: Boolean) {
        (requireActivity() as NavigationExecutor).startActivity()
    }

}