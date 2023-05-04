package com.example.cardealapplication.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.R
import com.example.cardealapplication.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var binding: ActivitySignUpBinding
    private lateinit var uid :String
    private val db = Firebase.firestore

    private val emailPattern= Pattern.compile( "[a-zA-Z\\d._-]+@[a-z]+\\.+[a-z]+")
    private val passwordPattern= Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$")
    private val phonePattern=Pattern.compile("^[6-9]\\d{9}\$")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=Firebase.auth
        initView()
    }
    private fun initView() {
        viewStateCity()
        binding.txt1.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
       binding.btnSignUp.setOnClickListener {
            performValidation()
        }
    }
    private fun viewStateCity() {
        val stateItems = listOf(
            "Andaman and Nicobar Islands",
            "Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chandigarh","Chhattisgarh",
            "Dadra and Nagar Haveli and Daman and Diu","Delhi","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir",
            "Jharkhand","Karnataka","Kerala","Ladakh","Madhya Pradesh","Maharashtra",
            "Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Puducherry",
            "Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana",
            "Tripura","Uttar Pradesh","Uttarakhand","West Bengal")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtRegisteredState.setAdapter(adapter)

        binding.txtRegisteredState.onItemClickListener=
            AdapterView.OnItemClickListener { _, _, position, _ ->
                when(position){
                    0 -> {
                        andamanNicobarItemView()
                    }
                    1 -> {
                        andhraItemView()
                    }
                    2 -> {
                        arunachalItemView()
                    }
                    3 -> {
                        assamItemView()
                    }
                    4 -> {
                        biharItemView()
                    }
                    5 -> {
                        chandigarhItemView()
                    }
                    6 -> {
                        chhattisgarhItemView()
                    }
                    7 -> {
                        danddItemView()
                    }
                    8 -> {
                        delhiItemView()
                    }
                    9 -> {
                        goaItemView()
                    }
                    10 -> {
                        gujaratItemView()
                    }
                    11 -> {
                        haryanaItemView()
                    }
                    12 -> {
                        himmachalItemView()
                    }
                    13 -> {
                        jandkItemView()
                    }
                    14 -> {
                        jharkhandItemView()
                    }
                    15 -> {
                        karnatakaItemView()
                    }
                    16 -> {
                        keralaItemView()
                    }
                    17 -> {
                        ladakhItemView()
                    }
                    18 -> {
                        madhyaItemView()
                    }
                    19 -> {
                        maharashtraItemView()
                    }
                    20 -> {
                        manipurItemView()
                    }
                    21 -> {
                        meghalayaItemView()
                    }
                    22 -> {
                        mizoramItemView()
                    }
                    23 -> {
                        nagalandItemView()
                    }
                    24 -> {
                        odishaItemView()
                    }
                    25 -> {
                        puducherryItemView()
                    }
                    26 -> {
                        punjabItemView()
                    }
                    27 -> {
                        rajashtanItemView()
                    }
                    28 -> {
                        sikkimItemView()
                    }
                    29 -> {
                        tamilItemView()
                    }
                    30 -> {
                        telaganaItemView()
                    }
                    31 -> {
                        tripuraItemView()
                    }
                    32 -> {
                        upItemView()
                    }

                    33 -> {
                        uttrakhandItemView()
                    }

                    34 -> {
                        wbItemView()
                    }
                }
            }
    }
    private fun wbItemView() {
        val stateItems = listOf("Alipore", "Alipur Duar","Asansol","Baharampur","Bally","Balurghat","Bankura",
            "Baranagar","Barasat","Barrackpore","Basirhat","Bhatpara","Bishnupur","Budge Budge",
            "Burdwan","Chandernagore","Darjeeling","Diamond Harbour","Dum Dum","Durgapur","Halisahar","Haora",
            "Hugli","Ingraj Bazar","Jalpaiguri","Kalimpong","Kamarhati","Kanchrapara","Kharagpur","Cooch Behar","Kolkata","Krishnanagar",
            "Malda","Midnapore","Murshidabad","Nabadwip", "Palashi","Panihati","Purulia","Raiganj","Santipur",
            "Shantiniketan","Shrirampur","Siliguri","Siuri","Tamluk","Titagarh")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun upItemView() {
        val stateItems = listOf("Agra", "Aligarh","Amroha","Ayodhya","Azamgarh","Bahraich","Ballia",
            "Banda","Bara Banki","Bareilly","Basti","Bijnor","Bithur","Budaun",
            "Bulandshahr","Deoria","Etah","Etawah","Faizabad","Farrukhabad/Fatehgarh","Fatehpur","Ghaziabad",
            "Ghazipur","Gonda","Gorakhpur","Hamirpur","Hardoi","Hathras","Jalaun","Jaunpur","Jhansi","Kannauj",
            "Kanpur","Lakhimpur","Lalitpur","Lucknow", "Mainpuri","Mathura","Meerut","Mirzapur-Vindhyachal","Moradabad",
            "Muzaffarnagar","Partapgarh","Pilibhit","Prayagraj","Rae Bareli","Rampur","Saharanpur","Sambhal","Shahjahanpur",
            "Sitapur","Sultanpur","Tehri","Varanasi")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun tamilItemView() {
        val stateItems = listOf("Arcot", "Chengalpattu","Chennai","Chidambaram","Coimbatore","Cuddalore","Dharmapuri",
            "Dindigul","Erode","Kanchipuram","Kanniyakumari","Kodaikanal","Kumbakonam","Madurai",
            "Mamallapuram","Nagappattinam","Nagercoil","Palayamkottai","Pudukkottai","Rajapalayam","Ramanathapuram","Salem",
            "Thanjavur","Tiruchchirappalli","Tirunelveli","Tiruppur","Thoothukudi","Udhagamandalam","Vellore")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun rajashtanItemView() {
        val stateItems = listOf("Abu", "Ajmer","Alwar","Amer","Barmer","Beawar","Bharatpur",
            "Bhilwara","Bikaner","Bundi","Chittaurgarh","Churu","Dhaulpur","Dungarpur",
            "Ganganagar","Hanumangarh","Jaipur","Jaisalmer","Jalor","Jhalawar","Jhunjhunu","Jodhpur",
            "Kishangarh","Kota","Merta","Nagaur","Nathdwara","Pali","Phalodi","Pushkar","Sawai Madhopur","Shahpura",
            "Sikar","Sirohi","Tonk","Udaipur")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun maharashtraItemView() {
        val stateItems = listOf("Ahmadnagar", "Akola","Amravati","Aurangabad","Bhandara","Bhusawal","Bid",
            "Buldhana","Chandrapur","Daulatabad","Dhule","Jalgaon","Kalyan","Karli",
            "Kolhapur","Mahabaleshwar","Malegaon","Matheran","Mumbai","Nagpur","Nanded","Nashik",
            "Osmanabad","Pandharpur","Parbhani","Pune","Ratnagiri","Sangli","Satara","Sevagram","Solapur","Thane",
            "Ulhasnagar","Vasai-Virar","Wardha","Yavatmal")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun uttrakhandItemView() {
        val stateItems = listOf("Almora","DehraDun","Haridwar","Mussoorie","Nainital","Pithoragarh","Rishikesh")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun tripuraItemView() {
        val stateItems = listOf("Agartala")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun telaganaItemView() {
        val stateItems = listOf("Hyderabad", "Karimnagar","Khammam","Mahbubnagar","Nizamabad","Sangareddi","Warangal")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun sikkimItemView() {
        val stateItems = listOf("Gangtok","Gyalshing","Lachung","Mangan")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun punjabItemView() {
        val stateItems = listOf("Amritsar", "Batala","Faridkot","Firozpur","Gurdaspur","Hoshiarpur","Jalandhar",
            "Kapurthala","Ludhiana","Nabha","Patiala","Rupnagar","Sangrur")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun puducherryItemView() {
        val stateItems = listOf("Karaikal","Mahe","Puducherry","Yanam")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun odishaItemView() {
        val stateItems = listOf("Balangir", "Baleshwar","Baripada","Bhubaneshwar","Brahmapur","Cuttack","Dhenkanal",
            "Kendujhar","Konark","Koraput","Paradip","Phulabani","Puri","Sambalpur","Udayagiri")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun nagalandItemView() {
        val stateItems = listOf("Kohima","Mon","Phek","Wokha","Zunheboto")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun mizoramItemView() {
        val stateItems = listOf("Aizawl","Lunglei")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun meghalayaItemView() {
        val stateItems = listOf("Cherrapunji","Shillong")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun manipurItemView() {
        val stateItems = listOf("Imphal")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun madhyaItemView() {
        val stateItems = listOf("Balaghat", "Barwani","Betul","Bharhut","Bhind","Bhojpur","Bhopal",
            "Burhanpur","Chhatarpur","Chhindwara","Damoh","Datia","Dewas","Dhar",
            "Dr. Ambedkar Nagar (Mhow)","Guna","Gwalior","Hoshangabad","Indore","Itarsi","Jabalpur","Jhabua",
            "Khajuraho","Khandwa","Khargone","Maheshwar","Mandla","Mandsaur","Morena","Murwara","Narsimhapur","Narsinghgarh",
            "Narwar","Neemuch","Nowgong","Orchha","Panna","Raisen","Rajgarh","Ratlam","Rewa","Sagar","Sarangpur","Satna",
            "Sehore","Seoni","Shahdol","Shajapur","Sheopur","Shivpuri","Ujjain","Vidisha")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun ladakhItemView() {
        val stateItems = listOf("Kargil", "Leh")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun keralaItemView() {
        val stateItems = listOf("Alappuzha", "Vatakara","Idukki","Kannur","Kochi","Kollam","Kottayam",
            "Kozhikode","Mattancheri","Palakkad","Thalassery","Thiruvananthapuram","Thrissur")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun karnatakaItemView() {
        val stateItems = listOf("Badami", "Ballari","Bengaluru","Belagavi","Bhadravati","Bidar","Chikkamagaluru",
            "Chitradurga","Davangere","Halebid","Hassan","Hubballi-Dharwad","Kalaburagi","Kolar",
            "Madikeri","Mandya","Mangaluru","Mysuru","Raichur","Shivamogga","Shravanabelagola","Shrirangapattana",
            "Tumakuru","Vijayapura",)
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun jharkhandItemView() {
        val stateItems = listOf("Bokaro", "Chaibasa","Deoghar","Dhanbad","Dumka","Giridih","Hazaribag",
            "Jamshedpur","Jharia","Rajmahal","Ranchi","Saraikela")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun jandkItemView() {
        val stateItems = listOf("Anantnag", "Baramula","Doda","Gulmarg","Jammu","Kathua","Punch",
            "Rajouri","Srinagar","Udhampur")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun himmachalItemView() {
        val stateItems = listOf("Bilaspur", "Chamba","Dalhousie","Dharmshala","Hamirpur","Kangra","Kullu",
            "Mandi","Nahan","Shimla","Una")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun haryanaItemView() {
        val stateItems = listOf("Ambala", "Bhiwani","Faridabad","Firozpur Jhirka","Gurugram","Hansi","Hisar",
            "Jind","Kaithal","Karnal","Kurukshetra","Panipat","Pehowa","Rewari",
            "Rohtak","Sirsa","Sonipat")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun gujaratItemView() {
        val stateItems = listOf("Ahmedabad", "Amreli","Bharuch","Bhavnagar","Bhuj","Dwarka","Gandhinagar",
            "Godhra","Jamnagar","Junagadh","Kandla","Khambhat","Kheda","Mahesana",
            "Morbi","Nadiad","Navsari","Okha","Palanpur","Patan","Porbandar",
            "Rajkot","Surat","Surendranagar","Valsad","Veraval")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun goaItemView() {
        val stateItems = listOf("Madgaon", "Panaji")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun delhiItemView() {
        val stateItems = listOf("Delhi", "New Delhi")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun danddItemView() {
        val stateItems = listOf("Daman", "Diu","Silvassa")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun chhattisgarhItemView() {
        val stateItems = listOf("Ambikapur", "Ambikapur","Bilaspur","Dhamtari","Durg","Jagdalpur","Raipur ",
            "Rajnandgaon")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun chandigarhItemView() {
        val stateItems = listOf("chandigarh")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun biharItemView() {
        val stateItems = listOf("Ara", "Barauni","Begusarai","Bettiah","Bhagalpur","Bihar Sharif","Bodh Gaya",
            "Buxar","Chapra","Darbhanga","Dehri","Dinapur Nizamat","Gaya","Hajipur",
            "Jamalpur","Katihar","Madhubani","Motihari","Munger","Muzaffarpur","Patna",
            "Purnia","Pusa","Saharsa","Samastipur","Sasaram","Sitamarhi","Siwan")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun assamItemView() {
        val stateItems = listOf("Dhuburi", "Dibrugarh","Dispur","Guwahati","Jorhat","Nagaon","Sivasagar ",
            "Silchar","Tezpur","Tinsukia")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun arunachalItemView() {
        val stateItems = listOf("Itanagar")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun andhraItemView() {
        val stateItems = listOf("Adoni", "Amaravati","Anantapur","Chandragiri","Chittoor","Dowlaiswaram","Eluru",
            "Guntur","Kadapa","Kakinada","Kurnool","Machilipatnam","Nagarjunakoṇḍa","Rajahmundry",
            "Srikakulam","Tirupati","Vijayawada","Ladakh","Visakhapatnam","Vizianagaram","Yemmiganur")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun andamanNicobarItemView() {
        val stateItems = listOf("Port Blair")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)
    }
    private fun performValidation() {
        val name=binding.name.text.toString()
        val email=binding.emailAddress.text.toString()
        val password=binding.password.text.toString()
        val phone=binding.phoneNumber.text.toString()
        val pass = binding.confirmPassword.text.toString()
        val txtRegisteredState =binding.txtRegisteredState.text.toString()
        val txtCity =binding.txtCity.text.toString()

        if(name.isEmpty())
        {
            binding.name.error="Cannot be Empty"
        } else if(txtRegisteredState.isEmpty()||!isValidPhoneNumber(phone))
        {
            binding.txtRegisteredState.error="Invalid Phone Number"
        } else if(txtCity.isEmpty()||!isValidPhoneNumber(phone))
        {
            binding.txtCity.error="Invalid Phone Number"
        } else if(phone.isEmpty()||!isValidPhoneNumber(phone))
        {
            binding.phoneNumber.error="Invalid Phone Nuber"
        } else if(email.isEmpty()||!isValidEmail(email))
        {
            binding.emailAddress.error="Invalid Email"
        } else if(password.isEmpty()||!isValidPassword(password))
        {
            binding.confirmPassword.error="Minimum eight characters, at least one uppercase letter,\n"+
                    "one lowercase letter, one number and one special character Required"
        }else if(pass != password)
        {
            binding.password.error="Password needs to be similar"
        } else
        {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
                if(it.isSuccessful)
                {
                   uid= auth.uid.toString()
                    addData(name,email,phone,uid,txtRegisteredState,txtCity)
                    auth.currentUser!!.sendEmailVerification().addOnCompleteListener {

                            auth.signOut()
                            startActivity(Intent(this,LoginActivity::class.java))
                            finish()
                            Toast.makeText(this, "SignUp Successful\n" +
                                    "Verification Mail has been Sent", Toast.LENGTH_SHORT).show()

                    }.addOnFailureListener{
                        Toast.makeText(this, " Error Occured ", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "User with this Email already exist", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun addData(
        Name: String,
        Email: String,
        Phone: String,
        Uid: String,
        txtRegisteredState: String,
        txtCity: String
    ) {

        db.collection("Users").document(Uid).set(
            hashMapOf(
                "Name" to Name,
                "Email" to Email,
                "Phone" to Phone,
                "User Id" to Uid,
                "txtRegisteredState" to txtRegisteredState,
                "txtCity" to txtCity
            )
        )
    }
    private fun isValidPhoneNumber(Phone: String): Boolean {
        val matcher:Matcher= phonePattern.matcher(Phone)
        return matcher.matches()
    }
    private fun isValidPassword(Password: String): Boolean {
        val matcher:Matcher=  passwordPattern.matcher(Password)
        return matcher.matches()
    }
    private fun isValidEmail(Email: String): Boolean {
        val matcher:Matcher= emailPattern.matcher(Email)
        return matcher.matches()
    }
}