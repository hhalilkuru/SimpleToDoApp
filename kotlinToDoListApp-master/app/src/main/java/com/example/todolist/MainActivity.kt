package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter : TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu : Menu?): Boolean {
        menuInflater.inflate(R.menu.todo_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add -> {
               val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_todo, null) //alert dialogu inflater ile bağlamak
                AlertDialog.Builder(this).setView(mDialogView).setTitle("ADD TODO").setPositiveButton("Save"){
                        dialogInterface, i ->
                       Toast.makeText(applicationContext, "Saved", Toast.LENGTH_LONG).show()
                    //val todoTitle = R.id.et_dialog_add.toString()
                    //
                    val todoTitleView = mDialogView.findViewById<EditText>(R.id.et_dialog_add)
                    val todoTitle = todoTitleView.text
                    //
                    if(todoTitle.isNotEmpty()) {
                        val todo = Todo(todoTitle)
                        todoAdapter.addTodo(todo)
                    }
                }.setNegativeButton("Cancel"){
                        dialogInterface, i ->   Toast.makeText(applicationContext, "Cancel", Toast.LENGTH_LONG).show()
                }.show()
                return true
            }
            R.id.remove -> {
                todoAdapter.deleteDoneTodos()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}