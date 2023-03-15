package com.example.cardealapplication.sell

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.cardealapplication.R
import com.example.cardealapplication.databinding.ActivitySellBinding

class SellActivity : AppCompatActivity() {

    lateinit var binding: ActivitySellBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySellBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initView()


    }

    private fun initView() {

        brandItemView()
        variantItemView()
        yearItemView()
        stateItemView()

        binding.btnContinue.setOnClickListener {
            performValidation()
        }

    }

    private fun performValidation() {
        val brand = binding.txtBrand.text.toString()
        val model = binding.txtModel.text.toString()
        val variant = binding.txtVariant.text.toString()
        val year = binding.txtYear.text.toString()
        val state = binding.txtState.text.toString()
        val city = binding.txtCity.text.toString()

        if (brand.isEmpty()) {
            binding.txtBrand.error = "Cannot Be Empty"
        } else if (model.isEmpty()) {
            binding.txtModel.error = "Cannot Be Empty"
        } else if (variant.isEmpty()) {
            binding.txtVariant.error = "Cannot Be Empty"
        } else if (year.isEmpty()) {
            binding.txtYear.error = "Cannot Be Empty"
        } else if (state.isEmpty()) {
            binding.txtState.error = "Cannot Be Empty"
        } else if (city.isEmpty()) {
            binding.txtCity.error = "Cannot Be Empty"
        } else {
            addData(brand,model,variant,year,state,city)
        }
    }

    private fun addData(
        brand: String,
        model: String,
        variant: String,
        year: String,
        state: String,
        city: String
    ) {
        Log.v("Data",""+brand+"\n"+model+"\n"+variant+"\n"+year+"\n"+state+"\n"+city)


        val intent = Intent(this,SellActivity2::class.java)
        intent.putExtra("brand",brand)
        intent.putExtra("model",model)
        intent.putExtra("variant",variant)
        intent.putExtra("year",year)
        intent.putExtra("state",state)
        intent.putExtra("city",city)
        startActivity(intent)
    }

    private fun yearItemView() {
        val yearItems = listOf(
            "2000","2001","2002","2003","2004","2005",
            "2006","2007","2008","2009","2010",
            "2011","2012","2013","2014","2015",
            "2016","2017","2018","2019","2020",
            "2021","2022","2023")
        val adapter = ArrayAdapter(this, R.layout.list_item, yearItems.reversed())
        binding.txtYear.setAdapter(adapter)
    }

    private fun stateItemView() {
        val stateItems = listOf(
            "Andaman and Nicobar Islands",
            "Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chandigarh","Chhattisgarh",
            "Dadra and Nagar Haveli and Daman and Diu","Delhi","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir",
            "Jharkhand","Karnataka","Kerala","Ladakh","Madhya Pradesh","Maharashtra",
            "Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Puducherry",
            "Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana",
            "Tripura","Uttar Pradesh","Uttarakhand","West Bengal")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtState.setAdapter(adapter)

        binding.txtState.onItemClickListener=
            AdapterView.OnItemClickListener { _, _, position, _ ->
                when(position){
                    0 -> {
                       andmanNicobarItemView()
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
                        gujratItemView()
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
        binding.txtCity.setAdapter(adapter)    }

    private fun telaganaItemView() {
        val stateItems = listOf("Hyderabad", "Karimnagar","Khammam","Mahbubnagar","Nizamabad","Sangareddi","Warangal")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)    }

    private fun sikkimItemView() {
        val stateItems = listOf("Gangtok","Gyalshing","Lachung","Mangan")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)    }

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
        binding.txtCity.setAdapter(adapter)    }

    private fun mizoramItemView() {
        val stateItems = listOf("Aizawl","Lunglei")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)    }

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

    private fun gujratItemView() {
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

    private fun andmanNicobarItemView() {
        val stateItems = listOf("Port Blair")
        val adapter = ArrayAdapter(this, R.layout.list_item, stateItems)
        binding.txtCity.setAdapter(adapter)

    }

    private fun variantItemView() {
        val variantItems = listOf("Petrol","Diesel","CNG","Electric")
        val adapter = ArrayAdapter(this, R.layout.list_item, variantItems)
        binding.txtVariant.setAdapter(adapter)
    }


    private fun brandItemView() {
        val brandItems =  listOf("Maruti","Hyundai","Tata")
        val adapter = ArrayAdapter(this, R.layout.list_item,brandItems )
        binding.txtBrand.setAdapter(adapter)

        binding.txtBrand.onItemClickListener=
            AdapterView.OnItemClickListener { _, _, position, _ ->
                when (position) {
                    0 -> {
                        marutiItemView()
                    }
                    1 -> {
                        hyundaiItemView()
                    }
                    2 -> {
                        tataItemView()
                    }
                }

            }

    }

    private fun tataItemView() {
        val modelItems = listOf("Altroz", "Punch", "Nexon", "Harrier","Safari")
        val adapter = ArrayAdapter(this, R.layout.list_item, modelItems)
        binding.txtModel.setAdapter(adapter)
    }

    private fun hyundaiItemView() {
        val modelItems = listOf("Creta", "Venue", "i10", "i20","Verna")
        val adapter = ArrayAdapter(this, R.layout.list_item, modelItems)
        binding.txtModel.setAdapter(adapter)
    }

    private fun marutiItemView() {
        val modelItems = listOf("Ertiga", "Wagon R", "Grand Vitara", "Swift","Brezza")
        val adapter = ArrayAdapter(this, R.layout.list_item, modelItems)
        binding.txtModel.setAdapter(adapter)
    }
}