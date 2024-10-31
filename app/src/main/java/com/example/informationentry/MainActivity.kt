package com.example.informationentry

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var idEditText: EditText
    private lateinit var fullNameEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var emailEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var dateOfBirthButton: Button
    private lateinit var dateOfBirthCalendarView: CalendarView
    private lateinit var currentResidenceWardSpinner: Spinner
    private lateinit var currentResidenceDistrictSpinner: Spinner
    private lateinit var currentResidenceProvinceSpinner: Spinner
    private lateinit var hobbiesSportsCheckBox: CheckBox
    private lateinit var hobbiesMoviesCheckBox: CheckBox
    private lateinit var hobbiesMusicCheckBox: CheckBox
    private lateinit var agreeTermsCheckBox: CheckBox
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_entry_form)

        idEditText = findViewById(R.id.id_edit_text)
        fullNameEditText = findViewById(R.id.full_name_edit_text)
        genderRadioGroup = findViewById(R.id.gender_radio_group)
        emailEditText = findViewById(R.id.email_edit_text)
        phoneNumberEditText = findViewById(R.id.phone_number_edit_text)
        dateOfBirthButton = findViewById(R.id.date_of_birth_button)
        currentResidenceWardSpinner = findViewById(R.id.current_residence_ward_spinner)
        currentResidenceDistrictSpinner = findViewById(R.id.current_residence_district_spinner)
        currentResidenceProvinceSpinner = findViewById(R.id.current_residence_province_spinner)
        hobbiesSportsCheckBox = findViewById(R.id.hobbies_sports_check_box)
        hobbiesMoviesCheckBox = findViewById(R.id.hobbies_movies_check_box)
        hobbiesMusicCheckBox = findViewById(R.id.hobbies_music_check_box)
        agreeTermsCheckBox = findViewById(R.id.agree_terms_check_box)
        submitButton = findViewById(R.id.submit_button)

        // Set up spinners
        val wardAdapter = ArrayAdapter.createFromResource(this, R.array.wards, android.R.layout.simple_spinner_item)
        wardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        currentResidenceWardSpinner.adapter = wardAdapter

        val districtAdapter = ArrayAdapter.createFromResource(this, R.array.districts, android.R.layout.simple_spinner_item)
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        currentResidenceDistrictSpinner.adapter = districtAdapter

        val provinceAdapter = ArrayAdapter.createFromResource(this, R.array.provinces, android.R.layout.simple_spinner_item)
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        currentResidenceProvinceSpinner.adapter = provinceAdapter

        dateOfBirthButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val dateOfBirth = "$year-$month-$dayOfMonth"
                dateOfBirthButton.text = dateOfBirth
            }, year, month, day)

            datePickerDialog.show()
        }
        submitButton.setOnClickListener {
            if (validateForm()) {
                Toast.makeText(this, "Form submitted successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateForm(): Boolean {

        if (idEditText.text.trim().isEmpty() ||
            fullNameEditText.text.trim().isEmpty() ||
            emailEditText.text.trim().isEmpty() ||
            phoneNumberEditText.text.trim().isEmpty() ||
            dateOfBirthButton.text == "Date of Birth" ||
            !agreeTermsCheckBox.isChecked
        ) {
            return false
        }


        if (genderRadioGroup.checkedRadioButtonId == -1) {
            return false
        }



        if (currentResidenceWardSpinner.selectedItemPosition == 0 ||
            currentResidenceDistrictSpinner.selectedItemPosition == 0 ||
            currentResidenceProvinceSpinner.selectedItemPosition == 0
        ) {
            return false
        }

        return true
    }

}