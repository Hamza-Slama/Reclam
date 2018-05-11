package ia2.moduleproject.eniso.reclam.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import ia2.moduleproject.eniso.reclam.R
import kotlinx.android.synthetic.main.test.*
import  android.widget.*
class test : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)

//        var adpater = ArrayAdapter.createFromResource(this,R.array.numbers,android.R.layout.simple_spinner_item)
//        adpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner.adapter=adpater
//        spinner.setOnItemClickListener { parent, view, position, id ->
//var s= parent.getItemAtPosition(position).toString()
//            Toast.makeText(this,s,Toast.LENGTH_SHORT).show()
//        }
//        val bundle = intent.extras
//        val _name =bundle.getString("result")
        val options = arrayOf("Option 1", "Option 2","Option 3")
//        result.text = _name
        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,options)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                result.text = "Please Select an Option"
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                result.text = options.get(position)

            }
        }

    }
}
