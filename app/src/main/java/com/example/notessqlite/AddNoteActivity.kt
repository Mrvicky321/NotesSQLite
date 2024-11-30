package com.example.notessqlite

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notessqlite.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NoteDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)

        setContentView(binding.root)
        db= NoteDatabaseHelper(this)


        binding.updatesaveButton.setOnClickListener {
            val title = binding.updateTitleEditText.text.toString()
            val content = binding.updatecontentEditText.text.toString()
            val note = Note(
                0, title, content
            )
            db.insertNote(note)
            finish()
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
        }



    }


}