package com.example.myapplication4

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication4.databinding.ActivityAddNoteBinding

import com.example.myapplication4.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db:NoteDatabaseHelper
    private  var noteId:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityUpdateNoteBinding.inflate(layoutInflater)
      /*  enableEdgeToEdge()*/
        setContentView(binding.root)
        db = NoteDatabaseHelper(this)


        noteId=intent.getIntExtra("note_id",-1)
        if(noteId == -1){
            finish()
            return
        }

        val note = db.getNoteByID(noteId)
        binding.UpdatetitleEdittext.setText(note.title)
        binding.updatecontentEditText.setText(note.content)

        binding.updateSaveButton.setOnClickListener{
            val newTitle = binding.UpdatetitleEdittext.text.toString()
            val newContent = binding.updatecontentEditText.text.toString()
            val updateNote = Note(noteId,newTitle,newContent)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this,"Changes Saved",Toast.LENGTH_SHORT).show()
        }
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }
}