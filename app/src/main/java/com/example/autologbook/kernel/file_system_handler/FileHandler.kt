package com.example.autologbook.kernel.file_system_handler

import android.content.Context.MODE_PRIVATE
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter


class FileHandler {


    private fun getExternalPath(): File? {
        return File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), "FILE_NAME")
    }

    // сохранение файла

    fun f(applicationContext: AppCompatActivity){
        var rf = Environment.getExternalStorageDirectory()
        val path: File = applicationContext.filesDir

        val fileOutputStream: FileOutputStream
        val outputStreamWriter: OutputStreamWriter
        fileOutputStream = applicationContext.openFileOutput("mFileName", MODE_PRIVATE)
        outputStreamWriter = OutputStreamWriter(
            applicationContext.openFileOutput("ff.path" + "/gg.txt", MODE_PRIVATE))
        outputStreamWriter.write("xxxx\n".toString())
        outputStreamWriter.write("sdsd".toString())
        outputStreamWriter.close()
        fileOutputStream.close()


        val fileInputStream: FileInputStream
        val inputStreamWriter: InputStreamReader
        fileInputStream = applicationContext.openFileInput("mFileName")
        inputStreamWriter = InputStreamReader(fileInputStream)
        var r = inputStreamWriter.readLines()
        inputStreamWriter.close()
        fileInputStream.close()
    }

    fun CreateFile(applicationContext: AppCompatActivity) {

        var ff = File(applicationContext.getExternalFilesDir(null), "d")
        ff.createNewFile()
        // После чего создаем поток для записи
        val outputStream = FileOutputStream(ff)
        // Производим непосредственно запись
        outputStream.write("text".toByteArray())
        // Закрываем поток
        outputStream.close()

        //val downloadDir = Environment.getExternalStorageDirectory().getAbsolutePath()
    }


}