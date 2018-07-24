package enzapps.com.sqlite_kotlin

import android.database.Cursor
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var myDb: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myDb =  DatabaseHelper(this)
        deleteData()
        updateData()
        addData()
        viewAll()

    }

    public fun deleteData() {
        button_delete.setOnClickListener{
            var deletedRows: Int? = myDb?.deleteData(editText_id.text.toString())
            if (deletedRows!! >0)
            {
                Toast.makeText(this,"Data Deleted", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Data not Deleted", Toast.LENGTH_LONG).show()
            }
        }


    }

    public fun updateData()
    {
        button_update.setOnClickListener {
            var isUpdate: Boolean? = myDb?.updateData(editText_id.text.toString(),editText_name.text.toString(),editText_surname.text.toString(),editText_Marks.text.toString())
            if (isUpdate==true)
            {
                Toast.makeText(this,"Data Update",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Data Not Update",Toast.LENGTH_LONG).show()
            }

        }
    }

    public fun addData()
    {
        button_add.setOnClickListener {
            var isInserted:Boolean?= myDb!!.insertData(editText_name.text.toString(),editText_surname.text.toString(),editText_Marks.text.toString())
            if (isInserted==true)
            {
                Toast.makeText(this,"Data Inserted",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Data not Inserted",Toast.LENGTH_SHORT).show()
            }
        }

    }
    public fun viewAll()
    {
        button_viewAll.setOnClickListener {

            var res:Cursor= myDb!!.allData
            if (res.count==0)
            {
                Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show()
            }
            else{
                var stringBuffer :StringBuffer= StringBuffer();
                while (res.moveToNext())
                {
                    stringBuffer.append("Id :"+ res.getString(0)+"\n");
                    stringBuffer.append("Name :"+ res.getString(1)+"\n");
                    stringBuffer.append("Surname :"+ res.getString(2)+"\n");
                    stringBuffer.append("Marks :"+ res.getString(3)+"\n\n");
                }
                showMessage("Data",stringBuffer.toString())
            }
        }
    }
    public fun showMessage(title:String ,Message:String)
    {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }


}
