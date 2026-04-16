package com.example.hospitalmanagementsystem.data

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.hospitalmanagementsystem.models.PatientModels
import com.example.hospitalmanagementsystem.navigation.ROUTE_DASHBOARD
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.InputStream

class PatientViewModel : ViewModel() {

    private val cloudinaryUrl =
        "https://api.cloudinary.com/v1_1/dm8wfbxcldm8wfbxcl/image/upload"

    private val uploadPreset = "image_folder"

    // ✅ Moved to the correct class level
    private val _patients = mutableStateListOf<PatientModels>()
    val patients: List<PatientModels> = _patients

    fun uploadPatient(
        imageUri: Uri?,
        name: String,
        age: String,
        phone: String,
        illness: String,
        gender: String,
        date_of_visit: String,
        context: Context,
        navController: NavController
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val imageUrl = imageUri?.let {
                    uploadToCloudinary(context, it)
                }

                val ref = FirebaseDatabase.getInstance()
                    .getReference("Patients")
                    .push()

                val patientData = mapOf(
                    "id" to ref.key,
                    "name" to name,
                    "age" to age,
                    "phone" to phone,
                    "illness" to illness,
                    "gender" to gender,
                    "date_of_visit" to date_of_visit,
                    "imageUrl" to imageUrl
                )

                ref.setValue(patientData).await()

                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Patient saved successfully", Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_DASHBOARD)
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Failed to save patient", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun uploadToCloudinary(context: Context, uri: Uri): String {
        val inputStream: InputStream = context.contentResolver
            .openInputStream(uri)
            ?: throw Exception("Cannot open image")

        val fileBytes = inputStream.use { it.readBytes() }

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "file",
                "image.jpg",
                fileBytes.toRequestBody("image/*".toMediaTypeOrNull())
            )
            .addFormDataPart("upload_preset", uploadPreset)
            .build()

        val request = Request.Builder()
            .url(cloudinaryUrl)
            .post(requestBody)
            .build()

        val response = OkHttpClient().newCall(request).execute()

        if (!response.isSuccessful) {
            throw Exception("Cloudinary upload failed")
        }

        val responseBody = response.body?.string()

        val secureUrl = Regex("\"secure_url\":\"(.*?)\"")
            .find(responseBody ?: "")
            ?.groupValues?.get(1)

        return secureUrl ?: throw Exception("Image URL not found")
    }

    // ✅ fetchPatients is now at the correct level inside the single class
    fun fetchPatients(context: Context) {
        val ref = FirebaseDatabase.getInstance().getReference("Patients")

        ref.get()
            .addOnSuccessListener { snapshot ->
                _patients.clear()
                for (child in snapshot.children) {
                    val patient = child.getValue(PatientModels::class.java)
                    patient?.let {
                        it.id = child.key
                        _patients.add(it)
                    }
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed to load patients", Toast.LENGTH_LONG).show()
            }
    }
}