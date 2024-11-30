package com.example.notessqlite
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notessqlite.databinding.ActivityAddNoteBinding


class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NoteDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)

        // Retrieve the note ID passed from the intent
        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1) {
            finish()
            return
        }

        // Fetch the note from the database
        val note = db.getNoteByID(noteId)
        if (note == null) {
            Toast.makeText(this, "Note not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Set existing note data in the input fields
        binding.updateTitleEditText.setText(note.title)
        binding.updatecontentEditText.setText(note.content)

        // Handle save button click
        binding.updatesaveButton.setOnClickListener {
            val newTitle = binding.updateTitleEditText.text.toString().trim()
            val newContent = binding.updatecontentEditText.text.toString().trim()

            // Validate input
            if (newTitle.isEmpty() || newContent.isEmpty()) {
                Toast.makeText(this, "Title and Content cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Update the note and save to the database
            val updateNote = Note(noteId, newTitle, newContent)
            db.updateNote(updateNote)

            // Notify user and finish activity
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
