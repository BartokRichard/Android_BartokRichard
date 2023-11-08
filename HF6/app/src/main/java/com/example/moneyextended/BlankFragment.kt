package com.example.moneyextended

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_blank, container, false)

        val listView : ListView = view.findViewById(R.id.listView)

        val images = listOf(
            R.drawable.europeanunion,
            R.drawable.unitedstates,
            R.drawable.unitedkingdom,
            R.drawable.australia,
            R.drawable.canada,
            R.drawable.switzerland,
            R.drawable.denmark,
            R.drawable.hungary
        )

        val currencies = listOf("EUR", "USD", "GBP", "AUD", "CAD", "CHF", "DKK", "HUF")
        val countries = listOf("Euro", "Dolar american", "Lira sterlina", "Dolar australian", "Dolar canadian", "Franc elvetian", "Coroana daneza", "Forint maghiar")
        val buyNumbers = listOf("4.4100 RON", "3.9750 RON", "6.1250 RON", "2.9600 RON", "3.0950 RON", "4.2300 RON", "0.5850 RON", "0.0136 RON")
        val sellNumbers = listOf("4.5500 RON", "4.1450 RON", "6.3550 RON", "3.0600 RON", "3.2650 RON", "4.3300 RON", "0.6150 RON", "0.0146 RON")

        val adapter = MyListAdapter(requireActivity(), images, currencies, countries, buyNumbers, sellNumbers)
        listView.adapter = adapter


        listView.setOnItemClickListener { _, _, position, _ ->
            val clickedItemData = Bundle()
            clickedItemData.putInt("image", images[position])
            clickedItemData.putString("currency", currencies[position])
            clickedItemData.putString("country", countries[position])
            clickedItemData.putString("buyNumber", buyNumbers[position])
            clickedItemData.putString("sellNumber", sellNumbers[position])

            val fragment = BlankFragment2()
            fragment.arguments = clickedItemData

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
            *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}