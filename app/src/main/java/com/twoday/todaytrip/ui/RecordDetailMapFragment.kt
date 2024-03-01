package com.twoday.todaytrip.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import com.twoday.todaytrip.R
import com.twoday.todaytrip.databinding.FragmentRecordDetailMapBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecordDetailMapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecordDetailMapFragment : Fragment(), OnMapReadyCallback {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

    private var _binding: FragmentRecordDetailMapBinding? = null
    private val binding get() = _binding!!

//    private val LOCATION_PERMISSION_REQUEST_CODE = 500
    private lateinit var map: NaverMap
    private lateinit var locationSource: FusedLocationSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentRecordDetailMapBinding.inflate(layoutInflater)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_record_detail_map, container, false)
        return binding.root
    }

//    private fun initMap() {
//        mapFragment.getMapAsync(this)
//        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
//    }

    override fun onMapReady(naverMap: NaverMap) {
        this.map = naverMap
        naverMap.locationSource = locationSource
        naverMap.uiSettings.isLocationButtonEnabled = true
        naverMap.locationTrackingMode = LocationTrackingMode.Face
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecordDetailMapFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecordDetailMapFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}