package com.jatin.listview

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.jatin.listview.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var arrayList = arrayListOf<String>("Jatin","Vishal","Vikas","Harcharan","Akarshak","Puneet","Rahul")
    var pos:Int = 0
    lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,arrayList)
        binding.listView.adapter = adapter
        //arrayList.set(1,"Harish")
        binding.btnFab.setOnClickListener{
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.user_add)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            dialog.show()
            var etName = dialog.findViewById<EditText>(R.id.etName)
            val btnAdd = dialog.findViewById<Button>(R.id.btnAdd)
            val btnNo = dialog.findViewById<Button>(R.id.btnNo)
            btnAdd.setOnClickListener {
                arrayList.add(etName.text.toString())
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            btnNo.setOnClickListener {
                dialog.dismiss()
            }
        }
        binding.listView.setOnItemClickListener { adapterView, view, position, l ->
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.user_edit)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            dialog.show()
            var etName = dialog.findViewById<EditText>(R.id.etEditName)
            val btnEdit = dialog.findViewById<Button>(R.id.btnEdit)
            val btnNo = dialog.findViewById<Button>(R.id.btnNoEdit)

            etName.setText(arrayList[position])
            Toast.makeText(this, "${pos}", Toast.LENGTH_SHORT).show()

            btnEdit.setOnClickListener {
                arrayList[pos] = etName?.text.toString()
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            btnNo.setOnClickListener {
                dialog.dismiss()
            }
        }
    }
}