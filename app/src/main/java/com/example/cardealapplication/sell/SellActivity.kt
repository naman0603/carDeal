package com.example.cardealapplication.sell

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.R
import com.example.cardealapplication.databinding.ActivitySellBinding
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SellActivity : AppCompatActivity() {
    private var db = Firebase.firestore
    var carName: MutableList<String> = mutableListOf()


    lateinit var binding: ActivitySellBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySellBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        setModel()
        setData()
        checkData()
    }

    private fun checkData() {


        binding.btnContinue.setOnClickListener {
            val txtCarName =binding.txtCarName.text.toString()
            val txtManufactureYear =binding.txtManufactureYear.text.toString()
            val txtFuelType =binding.txtFuelType.text.toString()
            val txtRegisteredState =binding.txtRegisteredState.text.toString()
            val txtCity =binding.txtCity.text.toString()
            val txtTransmission =binding.txtTransmission.text.toString()
            val txtOwners =binding.txtOwners.text.toString()
            val txtInsurance =binding.txtInsurance.text.toString()
            if (txtCarName.isEmpty())
            {
                binding.txtCarName.error="Cannot be empty"
            } else if (txtManufactureYear.isEmpty())
            {
                binding.txtManufactureYear.error="Cannot be empty"
            } else if (txtFuelType.isEmpty())
            {
                binding.txtFuelType.error="Cannot be empty"
            } else if (txtRegisteredState.isEmpty())
            {
                binding.txtRegisteredState.error="Cannot be empty"
            } else if (txtCity.isEmpty())
            {
                binding.txtCity.error="Cannot be empty"
            } else if (txtTransmission.isEmpty())
            {
                binding.txtTransmission.error="Cannot be empty"
            } else if (txtOwners.isEmpty())
            {
                binding.txtOwners.error="Cannot be empty"
            } else if (txtInsurance.isEmpty())
            {
                binding.txtInsurance.error="Cannot be empty"
            }else{
                val intent = Intent(this,SellActivity2::class.java)

                intent.putExtra("txtCarName",txtCarName)
                intent.putExtra("txtManufactureYear",txtManufactureYear)
                intent.putExtra("txtFuelType",txtFuelType)
                intent.putExtra("txtRegisteredState",txtRegisteredState)
                intent.putExtra("txtCity",txtCity)
                intent.putExtra("txtTransmission",txtTransmission)
                intent.putExtra("txtOwners",txtOwners)
                intent.putExtra("txtInsurance",txtInsurance)


                startActivity(intent)

            }
        }

    }

    private fun setModel() {
        val company : String = intent.extras?.getString("Company Name").toString()
        db.collection("Display Info Cars").whereEqualTo("txtCompanyName",company).
        addSnapshotListener(object : EventListener<QuerySnapshot> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error!=null){
                    Log.v("error",""+error.message.toString())
                }

                for (dc: DocumentChange in value?.documentChanges!!){
                    if(dc.type == DocumentChange.Type.ADDED){
                        carName.add(dc.document.data["txtCarName"].toString())

                    }
                }
            }
        })

    }

    private fun setData() {
        viewCarName()
        viewStateCity()
        viewFuelType()
        viewYears()
        viewOwners()
        viewTransmission()
        viewInsurance()
    }

    private fun viewTransmission() {
        val transmission = listOf("Manual Transmission (MT)","Automatic Transmission (AMT)",
            "Semi-Automatic transmission (SAT)")
        val adapter = ArrayAdapter(this, R.layout.list_item, transmission)
        binding.txtTransmission.setAdapter(adapter)
    }

    private fun viewYears() {
        val years = listOf<String>("2023","2022","2021","2020","2019","2018","2017","2016","2015","2014","2013","2012","2011","2010","2009","2008","2007","2006","2005","2004","2003","2002","2001","2000")
        val adapter = ArrayAdapter(this, R.layout.list_item, years)
        binding.txtManufactureYear.setAdapter(adapter)
    }

    private fun viewFuelType() {
        val fuelType = listOf<String>("Petrol","Diesel","CNG","Electric")
        val adapter = ArrayAdapter(this, R.layout.list_item, fuelType)
        binding.txtFuelType.setAdapter(adapter)
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

    private fun viewOwners() {
        val owners = listOf<String>("1","2","3","4","5","More than 5")
        val adapter = ArrayAdapter(this, R.layout.list_item, owners)
        binding.txtOwners.setAdapter(adapter)
    }

    private fun viewInsurance() {
        val insurance = listOf<String>("Yes","No")
        val adapter = ArrayAdapter(this, R.layout.list_item, insurance)
        binding.txtInsurance.setAdapter(adapter)
    }

    private fun viewCarName() {

        val adapter = ArrayAdapter(this, R.layout.list_item, carName)
        binding.txtCarName.setAdapter(adapter)

    }
}