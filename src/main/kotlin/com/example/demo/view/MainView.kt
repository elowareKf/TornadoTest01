package com.example.demo.view

import javafx.beans.property.SimpleIntegerProperty
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import tornadofx.*

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
}
