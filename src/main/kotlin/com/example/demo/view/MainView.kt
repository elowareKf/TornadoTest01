package com.example.demo.view

import io.github.rybalkinsd.kohttp.ext.httpGet
import javafx.beans.property.SimpleIntegerProperty
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import tornadofx.*
import java.sql.DriverManager
import java.util.*

class MainView : View("Hello TornadoFX") {
    override val root : BorderPane by fxml(hasControllerAttribute = true)

    val counter = SimpleIntegerProperty()
    val counterLabel : Label by fxid()

    init {
        counterLabel.bind(counter)
    }

    fun increment(){
        counter.value += 1
        println("Button clicked")
    }

    fun connectToDb(){
        val conProps = Properties()
        conProps["user"] = "root"
        conProps["password"] = "password"

        Class.forName("com.mysql.jdbc.Driver").newInstance()
        val conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", conProps)

        val stmt = conn!!.createStatement()
        if (stmt.execute("select * from customers"))
        {
            val resultset = stmt.resultSet
            while(resultset!!.next())
                println(resultset.getString("name"))
        }
        conn.close()
    }

    fun getRestData(){
        val response = "http://172.16.20.12:8082/staff/2".httpGet()
        println(response.body()!!.string())

    }
}
