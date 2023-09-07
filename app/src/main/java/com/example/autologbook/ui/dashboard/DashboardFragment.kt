package com.example.autologbook.ui.dashboard

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autologbook.databinding.FragmentDashboardBinding
import com.example.autologbook.kernel.file_system_handler.DbHelper
import com.example.autologbook.kernel.types.ItemsViewModel
import java.lang.Exception


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerview: RecyclerView
    private lateinit var activityLauncher:  ActivityResultLauncher<String>
    override fun onAttach(context: Context) {
        super.onAttach(context)

        activityLauncher = registerForActivityResult(MyActivityContract()) { result ->
                updateList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerview = binding.rcView
        recyclerview.layoutManager = LinearLayoutManager(context)

        binding.floatingActionButton3.setOnClickListener {
            activityLauncher.launch("")
        }

        return root
    }


    fun getDbContent(cursor: Cursor?, nameColumn: String) : String {

        if (cursor == null){
             throw Exception("Cursor matters null")
        }

        var indexColumn = cursor.getColumnIndex(nameColumn)
        if (indexColumn  < -1){
            throw Exception("Name $nameColumn is not defind")
        }
        return cursor.getString(indexColumn)

    }

    fun updateList(){
        var db = DbHelper.getInstance()
        var r = db.getAll()

        val data = ArrayList<ItemsViewModel>()
        while (r!!.moveToNext()){

            data.add(ItemsViewModel(
                getDbContent(r, DbHelper.LITRE_COL),
                getDbContent(r, DbHelper.DATE_COL),
                getDbContent(r, DbHelper.PRICE_COL)))
        }
        val adapter = Adapter(data)
        recyclerview.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}